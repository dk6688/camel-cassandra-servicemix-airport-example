# Camel Cassandra Component Servicemix Aiports Example

This is a simple example of a Camel route based on the Camel-cassandra component (https://github.com/oscerd/camel-cassandra) I've developed.
This route will be deployed on Apache Servicemix.

# Setting up the environment and deployment

To run this example you need to follow these instructions.

# Setting up Apache-Cassandra

- Install Apache Cassandra from http://cassandra.apache.org/download/ . In my configuration I've used Apache Cassandra version 2.2.4.

- Run Apache Cassandra by executing 

```shell

$CASSANDRA_HOME/bin/cassandra

```

- Run CQL console by executing 

```shell

$CASSANDRA_HOME/bin/cqlsh

```

- In CQL console run the following command (populate.cql is a cql file stored in `src/main/resources/cql/`)

```shell

SOURCE 'populateAirport.cql'

```

# Setting up Servicemix

Camel-Cassandra component is based on camel-core 2.16.1 release. So we need to use an Apache Servicemix version based on this release.

The Apache Servicemix 6.1.0 is the correct release.

- Download the Apache Servicemix 6.1.0 package from: http://servicemix.apache.org/downloads/servicemix-6.1.0.html

- Unzip the package in a directory (we denote this folder with $SERVICEMIX_HOME)

- Execute $SERVICEMIX_HOME/bin/servicemix

- Inside Karaf execute the following instructions:

```shell

karaf@root> features:repo-add mvn:com.github.oscerd/camel-cassandra/1.4.0/xml/features


```

- __Install camel-csv feature__

```shell

karaf@root> feature:install camel-csv

```

- __Install camel-cassandra feature__

```shell

karaf@root> feature:install camel-cassandra

```

# Deployment on Servicemix

- Clone the project this documentation refers to: https://github.com/oscerd/camel-cassandra-servicemix-airport-example

```shell

git clone https://github.com/oscerd/camel-cassandra-servicemix-airport-example.git

```

- Enter in the project directory and execute the following command:

```shell

mvn clean package

```

- __During the execution of tests in mvn clean package command, be sure your local Apache Cassandra instance is not running__

- Turn back to Servicemix console and executes this instruction:

```shell

karaf@root> bundle:install -s file:///$PROJECT_HOME/target/camel-cassandra-servicemix-airport-example-1.0.0-SNAPSHOT.jar

```

We can also verify everything is ok by looking at the log. Type the following command inside karaf:

```shell

karaf@root> log:tail

```

You should see something like this:

```shell

2015-10-11 10:07:50,738 | INFO  | l Console Thread | ultOsgiApplicationContextCreator | 84 - org.springframework.osgi.extender - 1.2.1 | Discovered configurations {osgibundle:/META-INF/spring/*.xml} in bundle [Camel Cassandra Servicemix Aiport Route Example (com.github.oscerd.camel-cassandra-servicemix-airport-example)]
2015-10-11 10:07:50,740 | INFO  | ExtenderThread-5 | OsgiBundleXmlApplicationContext  | 79 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Refreshing OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-airport-example, config=osgibundle:/META-INF/spring/*.xml): startup date [Sun Oct 11 10:07:50 CEST 2015]; root of context hierarchy
2015-10-11 10:07:50,741 | INFO  | ExtenderThread-5 | OsgiBundleXmlApplicationContext  | 79 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Application Context service already unpublished
2015-10-11 10:07:50,743 | INFO  | ExtenderThread-5 | XmlBeanDefinitionReader          | 77 - org.apache.servicemix.bundles.spring-beans - 3.2.11.RELEASE_1 | Loading XML bean definitions from URL [bundle://300.0:0/META-INF/spring/camel-context.xml]
2015-10-11 10:07:50,812 | INFO  | ExtenderThread-5 | WaiterApplicationContextExecutor | 84 - org.springframework.osgi.extender - 1.2.1 | No outstanding OSGi service dependencies, completing initialization for OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-airport-example, config=osgibundle:/META-INF/spring/*.xml)
2015-10-11 10:07:50,816 | INFO  | ExtenderThread-6 | DefaultListableBeanFactory       | 77 - org.apache.servicemix.bundles.spring-beans - 3.2.11.RELEASE_1 | Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@5a5d40aa: defining beans [camel1:beanPostProcessor,camel1,camelCassandraBuilder,cassandraCsvInsertBean]; root of factory hierarchy
2015-10-11 10:07:50,853 | INFO  | ExtenderThread-6 | OsgiSpringCamelContext           | 124 - org.apache.camel.camel-core - 2.15.2 | Apache Camel 2.15.2 (CamelContext: camel1) is starting
2015-10-11 10:07:50,853 | INFO  | ExtenderThread-6 | ManagedManagementStrategy        | 124 - org.apache.camel.camel-core - 2.15.2 | JMX is enabled
2015-10-11 10:07:50,977 | INFO  | ExtenderThread-6 | OsgiSpringCamelContext           | 124 - org.apache.camel.camel-core - 2.15.2 | AllowUseOriginalMessage is enabled. If access to the original message is not needed, then its recommended to turn this option off as it may improve performance.
2015-10-11 10:07:50,978 | INFO  | ExtenderThread-6 | OsgiSpringCamelContext           | 124 - org.apache.camel.camel-core - 2.15.2 | StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
2015-10-11 10:07:51,029 | INFO  | ExtenderThread-6 | OsgiSpringCamelContext           | 124 - org.apache.camel.camel-core - 2.15.2 | Route: route3 started and consuming from: Endpoint[file:///tmp/in/]
2015-10-11 10:07:51,030 | INFO  | ExtenderThread-6 | OsgiSpringCamelContext           | 124 - org.apache.camel.camel-core - 2.15.2 | Total 1 routes, of which 1 is started.
2015-10-11 10:07:51,030 | INFO  | ExtenderThread-6 | OsgiSpringCamelContext           | 124 - org.apache.camel.camel-core - 2.15.2 | Apache Camel 2.15.2 (CamelContext: camel1) started in 0.177 seconds
2015-10-11 10:07:51,032 | INFO  | ExtenderThread-6 | OsgiBundleXmlApplicationContext  | 79 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Publishing application context as OSGi service with properties {org.springframework.context.service.name=com.github.oscerd.camel-cassandra-servicemix-airport-example, Bundle-SymbolicName=com.github.oscerd.camel-cassandra-servicemix-airport-example, Bundle-Version=1.0.0.SNAPSHOT}
2015-10-11 10:07:51,035 | INFO  | ExtenderThread-6 | ContextLoaderListener            | 84 - org.springframework.osgi.extender - 1.2.1 | Application context successfully refreshed (OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-airport-example, config=osgibundle:/META-INF/spring/*.xml))

```

The route is now deployed correctly. We need to process a csv file. Follow these instructions:

- Enter in /tmp/ folder

```shell

cd /tmp/

```

- Create a in folder

```shell

mkdir in

```

- Download the following csv file: http://ourairports.com/data/airports.csv

- Copy this file into /tmp/in/

```shell

cp airports.csv /tmp/in/

```

- We can verify the file processing by looking at the log

```shell

karaf@root> log:tail

```

You should see something like this:

```shell
.
.
.

2015-12-22 17:44:46,790 | INFO  |  file:///tmp/in/ | route2                           | 199 - org.apache.camel.camel-core - 2.16.1 | Inserting current row in Apache Cassandra
2015-12-22 17:44:46,821 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 247 - com.datastax.driver.core - 2.1.9 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-12-22 17:44:46,822 | INFO  |  file:///tmp/in/ | Cluster                          | 247 - com.datastax.driver.core - 2.1.9 | New Cassandra host /127.0.0.1:9042 added
2015-12-22 17:44:49,036 | INFO  |  file:///tmp/in/ | route2                           | 199 - org.apache.camel.camel-core - 2.16.1 | Unmarshalling current row: 6534,"00GA","small_airport","Lt World Airport",33.76750183105469,-84.06829833984375,700,"NA","US","US-GA","Lithonia","no","00GA",,"00GA",,,
2015-12-22 17:44:49,037 | INFO  |  file:///tmp/in/ | route2                           | 199 - org.apache.camel.camel-core - 2.16.1 | Inserting current row in Apache Cassandra
2015-12-22 17:44:49,067 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 247 - com.datastax.driver.core - 2.1.9 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-12-22 17:44:49,067 | INFO  |  file:///tmp/in/ | Cluster                          | 247 - com.datastax.driver.core - 2.1.9 | New Cassandra host /127.0.0.1:9042 added
2015-12-22 17:44:51,281 | INFO  |  file:///tmp/in/ | route2                           | 199 - org.apache.camel.camel-core - 2.16.1 | Unmarshalling current row: 6535,"00GE","heliport","Caffrey Heliport",33.88420104980469,-84.73390197753906,957,"NA","US","US-GA","Hiram","no","00GE",,"00GE",,,
2015-12-22 17:44:51,282 | INFO  |  file:///tmp/in/ | route2                           | 199 - org.apache.camel.camel-core - 2.16.1 | Inserting current row in Apache Cassandra
2015-12-22 17:44:51,314 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 247 - com.datastax.driver.core - 2.1.9 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-12-22 17:44:51,314 | INFO  |  file:///tmp/in/ | Cluster                          | 247 - com.datastax.driver.core - 2.1.9 | New Cassandra host /127.0.0.1:9042 added
2015-12-22 17:44:53,530 | INFO  |  file:///tmp/in/ | route2                           | 199 - org.apache.camel.camel-core - 2.16.1 | Unmarshalling current row: 6536,"00HI","heliport","Kaupulehu Heliport",19.832500457763672,-155.98199462890625,43,"NA","US","US-HI","Kailua/Kona","no","00HI",,"00HI",,,
2015-12-22 17:44:53,531 | INFO  |  file:///tmp/in/ | route2                           | 199 - org.apache.camel.camel-core - 2.16.1 | Inserting current row in Apache Cassandra
2015-12-22 17:44:53,565 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 247 - com.datastax.driver.core - 2.1.9 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-12-22 17:44:53,565 | INFO  |  file:///tmp/in/ | Cluster                          | 247 - com.datastax.driver.core - 2.1.9 | New Cassandra host /127.0.0.1:9042 added
2015-12-22 17:44:55,781 | INFO  |  file:///tmp/in/ | route2                           | 199 - org.apache.camel.camel-core - 2.16.1 | Unmarshalling current row: 6537,"00ID","small_airport","Delta Shores Airport",48.145301818847656,-116.21399688720703,2064,"NA","US","US-ID","Clark Fork","no","00ID",,"00ID",,,
2015-12-22 17:44:55,781 | INFO  |  file:///tmp/in/ | route2                           | 199 - org.apache.camel.camel-core - 2.16.1 | Inserting current row in Apache Cassandra
2015-12-22 17:44:55,815 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 247 - com.datastax.driver.core - 2.1.9 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-12-22 17:44:55,815 | INFO  |  file:///tmp/in/ | Cluster                          | 247 - com.datastax.driver.core - 2.1.9 | New Cassandra host /127.0.0.1:9042 added
2015-12-22 17:44:58,029 | INFO  |  file:///tmp/in/ | route2                           | 199 - org.apache.camel.camel-core - 2.16.1 | Unmarshalling current row: 6538,"00II","heliport","Bailey Generation Station Heliport",41.644500732421875,-87.122802734375,600,"NA","US","US-IN","Chesterton","no","00II",,"00II",,,
2015-12-22 17:44:58,030 | INFO  |  file:///tmp/in/ | route2                           | 199 - org.apache.camel.camel-core - 2.16.1 | Inserting current row in Apache Cassandra
2015-12-22 17:44:58,058 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 247 - com.datastax.driver.core - 2.1.9 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-12-22 17:44:58,058 | INFO  |  file:///tmp/in/ | Cluster                          | 247 - com.datastax.driver.core - 2.1.9 | New Cassandra host /127.0.0.1:9042 added
2015-12-22 17:45:00,274 | INFO  |  file:///tmp/in/ | route2                           | 199 - org.apache.camel.camel-core - 2.16.1 | Unmarshalling current row: 6539,"00IL","small_airport","Hammer Airport",41.97840118408203,-89.5604019165039,840,"NA","US","US-IL","Polo","no","00IL",,"00IL",,,
2015-12-22 17:45:00,275 | INFO  |  file:///tmp/in/ | route2                           | 199 - org.apache.camel.camel-core - 2.16.1 | Inserting current row in Apache Cassandra
2015-12-22 17:45:00,305 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 247 - com.datastax.driver.core - 2.1.9 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-12-22 17:45:00,305 | INFO  |  file:///tmp/in/ | Cluster                          | 247 - com.datastax.driver.core - 2.1.9 | New Cassandra host /127.0.0.1:9042 added

.
.
.

```

# The route

This route executes 46240 inserts on Apache Cassandra airport table on the simplex keyspace.
