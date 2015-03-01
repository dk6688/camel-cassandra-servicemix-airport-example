package com.github.oscerd.camel.cassandra;

import java.io.IOException;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.github.oscerd.component.cassandra.CassandraConstants;

public class CassandraInsertCSVBeanTest extends CamelTestSupport{
    
	@Test
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
    	csv.setSkipFirstLine(true);
    	
        return new RouteBuilder() {
            public void configure() {
            	
            	from("file://src/test/resources?noop=true&fileName=airport.csv")
                    .unmarshal(csv).split(body(List.class))
                    .beanRef("csvBean","prepareForInsert")
                    .to("mock:result");
            }
        };
    }
}
