package com.github.oscerd.camel.cassandra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
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
    	csv.setSkipFirstLine(true);
    	
    	from("file:///tmp/in/?delete=true")
        .unmarshal(csv).split(body(List.class))
        .bean(new CassandraInsertCSVCamelBean(),"prepareForInsert")
        .setHeader(CassandraConstants.CASSANDRA_CONTACT_POINTS, constant(collAddr))
        .to("cassandra:cassandraConnection?keyspace=simplex&table=airport&operation=insert");
    }

}
