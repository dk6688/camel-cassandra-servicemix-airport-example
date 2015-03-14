package com.github.oscerd.camel.cassandra;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.processor.aggregate.AggregationStrategy;

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

		from("file:///tmp/in/")
				.setProperty("righeProcessate", constant(0))
				.split(body().tokenize("\n"), new SplitterSizeStrategy())
				.stopOnException()
				.streaming()
				.choice()
					// skip first line
					.when(header("CamelSplitIndex").isEqualTo(0))
					.log(LoggingLevel.INFO, "Si salta la prima riga di intestazione")
					// end choice
				.end()
				.choice()
					.when(header("CamelSplitIndex").isGreaterThan(0))
					.log(LoggingLevel.INFO, "Unmarshal della riga corrente: ${body}")
					.unmarshal(csv)
					.beanRef("cassandraCsvInsertBean", "prepareForInsert")
					.setHeader(CassandraConstants.CASSANDRA_CONTACT_POINTS, constant(collAddr))
					.log(LoggingLevel.INFO, "Inserimento dati riga corrente nel database")
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
				newExchange.setProperty("righeProcessate", 1);
				return newExchange;
			}
			Integer num = (Integer) newExchange.getProperty("CamelSplitSize");
			if (num != null) {
				oldExchange.setProperty("righeProcessate", num);
			}

			return oldExchange;
		}
	}
}
