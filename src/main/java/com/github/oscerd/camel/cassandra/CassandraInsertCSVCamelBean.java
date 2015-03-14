package com.github.oscerd.camel.cassandra;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.camel.Exchange;

import com.github.oscerd.camel.cassandra.data.IAirportFields;
import com.github.oscerd.component.cassandra.CassandraConstants;

public class CassandraInsertCSVCamelBean {

	public void prepareForInsert(Exchange exchange) throws Exception {
		List<List<String>> data = (List<List<String>>) exchange.getIn().getBody();
		Iterator it = data.iterator();
		while (it.hasNext()){
			List<String> dataInternal = (List<String>) it.next();	
			HashMap<String, Object> insertObject = new HashMap<String, Object>();
			if (dataInternal.get(0) != null && !"".equals(dataInternal.get(0))){
				insertObject.put(IAirportFields.AIRPORT_ID, Integer.parseInt(dataInternal.get(0)));
			}
			insertObject.put(IAirportFields.AIRPORT_IDENT, dataInternal.get(1));
			insertObject.put(IAirportFields.AIRPORT_TYPE, dataInternal.get(2));
			insertObject.put(IAirportFields.AIRPORT_NAME, dataInternal.get(3));
			if (dataInternal.get(4) != null && !"".equals(dataInternal.get(4))){
				insertObject.put(IAirportFields.AIRPORT_LAT, Float.parseFloat(dataInternal.get(4)));
			}
			if (dataInternal.get(5) != null && !"".equals(dataInternal.get(5))){
				insertObject.put(IAirportFields.AIRPORT_LONG, Float.parseFloat(dataInternal.get(5)));
			}
			if (dataInternal.get(6) != null && !"".equals(dataInternal.get(6))){
				insertObject.put(IAirportFields.AIRPORT_ELEVATION, Integer.parseInt(dataInternal.get(6)));
			}
			insertObject.put(IAirportFields.AIRPORT_CONTINENT, dataInternal.get(7));
			insertObject.put(IAirportFields.AIRPORT_ISO_COUNTRY, dataInternal.get(8));
			insertObject.put(IAirportFields.AIRPORT_ISO_REGION, dataInternal.get(9));
			insertObject.put(IAirportFields.AIRPORT_MUNICIPALITY, dataInternal.get(10));
			insertObject.put(IAirportFields.AIRPORT_SCHEDULED_SERVICE, dataInternal.get(11));
			insertObject.put(IAirportFields.AIRPORT_GPS_CODE, dataInternal.get(12));	
			insertObject.put(IAirportFields.AIRPORT_IATA_CODE, dataInternal.get(13));	
			insertObject.put(IAirportFields.AIRPORT_LOCAL_CODE, dataInternal.get(14));	
			insertObject.put(IAirportFields.AIRPORT_HOME_LINK, dataInternal.get(15));
			insertObject.put(IAirportFields.AIRPORT_WIKIPEDIA_LINK, dataInternal.get(16));
			insertObject.put(IAirportFields.AIRPORT_KEYWORDS, dataInternal.get(17));
			exchange.getOut().setHeader(CassandraConstants.CASSANDRA_INSERT_OBJECT, insertObject);
			
		}
	}

}
