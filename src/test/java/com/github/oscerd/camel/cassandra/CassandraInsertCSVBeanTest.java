package com.github.oscerd.camel.cassandra;

import java.io.IOException;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Ignore;
import org.junit.Test;

import com.github.oscerd.camel.cassandra.data.IRouteConstants;
import com.github.oscerd.component.cassandra.CassandraConstants;

public class CassandraInsertCSVBeanTest extends CamelTestSupport{
    
	@Test
	@Ignore
    public void testInsert() throws IOException, InterruptedException {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);
     
        assertMockEndpointsSatisfied();
    }
	
    @Override
    protected JndiRegistry createRegistry() throws Exception {
        JndiRegistry registry = super.createRegistry();
        CassandraInsertCSVCamelBean csvBean = new CassandraInsertCSVCamelBean();
        registry.bind("csvBean", csvBean);
        return registry;
    }    
    
    protected RouteBuilder createRouteBuilder() throws Exception {

    	final CsvDataFormat csv = new CsvDataFormat();
    	csv.setSkipHeaderRecord(true);
    	
        return new RouteBuilder() {
            public void configure() {
            	
            	from("file://src/test/resources?noop=true&fileName=airports_mini.csv")
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
					.bean(new CassandraInsertCSVCamelBean(), "prepareForInsert")
					.to("mock:result")
				// end choice
				.end()

				// end Streaming
				.end()

				// end split
				.end();
	}
        };
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
