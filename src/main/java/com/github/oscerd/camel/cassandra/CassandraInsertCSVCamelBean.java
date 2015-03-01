package com.github.oscerd.camel.cassandra;

import java.util.HashMap;
import java.util.List;

import org.apache.camel.Exchange;

import com.github.oscerd.camel.cassandra.data.IAirportFields;
import com.github.oscerd.component.cassandra.CassandraConstants;

public class CassandraInsertCSVCamelBean {

	public void prepareForInsert(Exchange exchange) throws Exception {
		List<String> data = (List<String>) exchange.getIn().getBody();
		HashMap<String, Object> insertObject = new HashMap<String, Object>();
		insertObject.put(IAirportFields.AIRPORT_ID, Integer.parseInt(data.get(0)));
		insertObject.put(IAirportFields.AIRPORT_IDENT, data.get(1));
		insertObject.put(IAirportFields.AIRPORT_TYPE, data.get(2));
		insertObject.put(IAirportFields.AIRPORT_NAME, data.get(3));
		insertObject.put(IAirportFields.AIRPORT_LAT, Float.parseFloat(data.get(4)));
		insertObject.put(IAirportFields.AIRPORT_LONG, Float.parseFloat(data.get(5)));
		insertObject.put(IAirportFields.AIRPORT_ELEVATION, Integer.parseInt(data.get(6)));
		insertObject.put(IAirportFields.AIRPORT_CONTINENT, data.get(7));
		insertObject.put(IAirportFields.AIRPORT_ISO_COUNTRY, data.get(8));
		insertObject.put(IAirportFields.AIRPORT_ISO_REGION, data.get(9));
		insertObject.put(IAirportFields.AIRPORT_MUNICIPALITY, data.get(10));
		insertObject.put(IAirportFields.AIRPORT_SCHEDULED_SERVICE, data.get(11));
		insertObject.put(IAirportFields.AIRPORT_GPS_CODE, data.get(12));	
		insertObject.put(IAirportFields.AIRPORT_IATA_CODE, data.get(13));	
		insertObject.put(IAirportFields.AIRPORT_LOCAL_CODE, data.get(14));	
		insertObject.put(IAirportFields.AIRPORT_HOME_LINK, data.get(15));
		insertObject.put(IAirportFields.AIRPORT_WIKIPEDIA_LINK, data.get(16));
		insertObject.put(IAirportFields.AIRPORT_KEYWORDS, data.get(17));
		exchange.getOut().setHeader(CassandraConstants.CASSANDRA_INSERT_OBJECT, insertObject);
	}

}
