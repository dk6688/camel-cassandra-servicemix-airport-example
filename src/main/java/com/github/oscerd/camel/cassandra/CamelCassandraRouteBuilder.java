package com.github.oscerd.camel.cassandra;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.github.oscerd.camel.cassandra.data.IRouteConstants;
import com.github.oscerd.component.cassandra.CassandraConstants;

/**
 * Usage example of Camel-cassandra Component
 */
public class CamelCassandraRouteBuilder extends RouteBuilder {

	@Override
	public void configure() {

		String addr = "127.0.0.1";
		List<String> collAddr = new ArrayList<String>();
		collAddr.add(addr);
		CsvDataFormat csv = new CsvDataFormat();
		csv.setDelimiter(",");
        PropertiesComponent pc = getContext().getComponent("properties", PropertiesComponent.class);
        pc.setLocation("classpath:airport_route.properties");

		from("file://{{airport.in.directory}}")
				.setProperty(IRouteConstants.PROCESSED_ROWS, constant(0))
				.split(body().tokenize("\n"), new SplitterSizeStrategy())
				.stopOnException()
				.streaming()
				.choice()
					// skip first line
					.when(header("CamelSplitIndex").isEqualTo(0))
					.log(LoggingLevel.INFO, "Skipping first line")
					// end choice
				.end()
				.choice()
					.when(header("CamelSplitIndex").isGreaterThan(0))
					.log(LoggingLevel.INFO, "Unmarshalling current row: ${body}")
					.unmarshal(csv)
					.beanRef("cassandraCsvInsertBean", "prepareForInsert")
					.setHeader(CassandraConstants.CASSANDRA_CONTACT_POINTS, constant(collAddr))
					.log(LoggingLevel.INFO, "Inserting current row in Apache Cassandra")
					.to("cassandra:cassandraConnection?keyspace=simplex&table=airport&operation=insert")
				// end choice
				.end()

				// end Streaming
				.end()

				// end split
				.end();
	}
	
	private class SplitterSizeStrategy implements AggregationStrategy {

		@Override
		public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
			if (oldExchange == null) {
				newExchange.setProperty(IRouteConstants.PROCESSED_ROWS, 1);
				return newExchange;
			}
			Integer num = (Integer) newExchange.getProperty("CamelSplitSize");
			if (num != null) {
				oldExchange.setProperty(IRouteConstants.PROCESSED_ROWS, num);
			}

			return oldExchange;
		}
	}
}
