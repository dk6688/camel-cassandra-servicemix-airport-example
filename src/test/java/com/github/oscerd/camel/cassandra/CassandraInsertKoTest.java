package com.github.oscerd.camel.cassandra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.impl.JndiRegistry;
import org.junit.Test;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select.Where;
import com.github.oscerd.camel.cassandra.embedded.CassandraBaseTest;
import com.github.oscerd.component.cassandra.CassandraConstants;

public class CassandraInsertKoTest extends CassandraBaseTest{
    
	@Test
    public void testInsert() throws IOException, InterruptedException {
		String body = "\"id\",\"ident\",\"type\",\"name\",\"latitude_deg\",\"longitude_deg\",\"elevation_ft\",\"continent\",\"iso_country\",\"iso_region\",\"municipality\",\"scheduled_service\",\"gps_code\",\"iata_code\",\"local_code\","
				+ "\"home_link\",\"wikipedia_link\",\"keywords\"\n" + 
				"6523,\"00A\",\"heliport\",\"Total Rf Heliport\",40.07080078125,-74.9336013793945,,\"NA\",\"US\",\"US-PA\",\"Bensalem\",\"no\",\"00A\",,\"00A\",,,\n";
        MockEndpoint mock = getMockEndpoint("mock:result");
        Map<String, Object> headers = new HashMap<String, Object>();
        template.requestBodyAndHeaders("direct:in", body, headers);
        mock.expectedMessageCount(1);
        assertMockEndpointsSatisfied();
        
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        Session session = cluster.connect("simplex");
        Where select = QueryBuilder.select().all().from("airport").where(QueryBuilder.eq("name", "Total Rf Heliport"));
        ResultSet result = session.execute(select);
        session.close();
        cluster.close();
        assertEquals(result.getAvailableWithoutFetching(), 1);
        for (Row row : (ResultSet) result) {
            assertEquals(row.getFloat("latitude_deg"), 40.07080078125, 0.0f);
            assertEquals(row.getString("continent"), "NA");
            assertEquals(row.getInt("elevation_ft"), 0);
        }
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
        String addr = "127.0.0.1";
        final List<String> collAddr = new ArrayList<String>();
        collAddr.add(addr);
        
        return new RouteBuilder() {
            public void configure() {
            	
            	from("direct:in")
                    .unmarshal(csv).split(body(List.class))
                    .beanRef("csvBean","prepareForInsert")
                    .setHeader(CassandraConstants.CASSANDRA_CONTACT_POINTS, constant(collAddr))
                    .to("cassandra:cassandraConnection?keyspace=simplex&table=airport&operation=insert")
                    .to("mock:result");
            }
        };
    }
}
