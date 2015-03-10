CREATE KEYSPACE IF NOT EXISTS simplex WITH replication = {'class':'SimpleStrategy', 'replication_factor':3};
CREATE TABLE IF NOT EXISTS simplex.airport ( id int PRIMARY KEY,ident text,type text, name text,latitude_deg float,longitude_deg float,elevation_ft int,continent text,iso_country text, iso_region text, municipality text, scheduled_service text, gps_code text, iata_code text, local_code text, home_link text, wikipedia_link text, keywords text);
CREATE INDEX IF NOT EXISTS name_idx ON simplex.airport(name);
CREATE INDEX IF NOT EXISTS continent_idx ON simplex.airport(continent);
