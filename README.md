# Camel Cassandra Component Servicemix Aiports Example

This is a simple example of a Camel route based on the Camel-cassandra component (https://github.com/oscerd/camel-cassandra) I've developed.
This route will be deployed on Apache Servicemix.

# Setting up the environment and deployment

To run this example you need to follow these instructions.

# Setting up Apache-Cassandra

- Install Apache Cassandra from http://cassandra.apache.org/download/ . In my configuration I've used Apache Cassandra version 2.1.2.

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

Camel-Cassandra component is based on camel-core 2.15.2 release. So we need to use an Apache Servicemix version based on this release.

The Apache Servicemix 6.0.0.M2 is the correct release.

- Download the Apache Servicemix 6.0.0.M2 package from: http://servicemix.apache.org/downloads/servicemix-6.0.0.M2.html

- Unzip the package in a directory (we denote this folder with $SERVICEMIX_HOME)

- Execute $SERVICEMIX_HOME/bin/servicemix

- Inside Karaf execute the following instructions:

- __Install Google Guava Bundle__

```shell

karaf@root> install -s mvn:com.google.guava/guava/14.0.1

```

- __Install codahale metrics Bundle__

```shell

karaf@root> install -s mvn:com.codahale.metrics/metrics-core/3.0.2

```

- __Install Netty Common Bundle__

```shell

karaf@root> install -s mvn:io.netty/netty-common/4.0.27.Final

```

- __Install Netty Buffer Bundle__

```shell

karaf@root> install -s mvn:io.netty/netty-buffer/4.0.27.Final

```

- __Install Netty Transport Bundle__

```shell

karaf@root> install -s mvn:io.netty/netty-transport/4.0.27.Final

```

- __Install Netty Codec Bundle__

```shell

karaf@root> install -s mvn:io.netty/netty-codec/4.0.27.Final

```

- __Install Netty Transport Native Epoll Bundle__

```shell

karaf@root> install -s mvn:io.netty/netty-handler/4.0.27.Final

```

- __Install Netty Handler Bundle__

```shell

karaf@root> install -s mvn:io.netty/netty-transport-native-epoll/4.0.27.Final

```

- __Install lz4 Bundle__

```shell

karaf@root> install -s mvn:net.jpountz.lz4/lz4/1.2.0

```

- __Install Snappy Java Bundle__

```shell

karaf@root> install -s mvn:org.xerial.snappy/snappy-java/1.0.4

```

- __Install Datastax Java Driver Bundle__

```shell

karaf@root> install -s mvn:com.datastax.cassandra/cassandra-driver-core/2.1.6

```

- __Install Camel Cassandra Bundle__ 

```shell

karaf@root> install -s mvn:com.github.oscerd/camel-cassandra/1.2.0

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

karaf@root> osgi:install -s file:///$PROJECT_HOME/target/camel-cassandra-servicemix-airport-example-1.0.0-SNAPSHOT.jar

```

- To verify that the bundle is correctly deployed type the following command: 

```shell

karaf@root> osgi:list

```

you should see something like this:

```shell

[ 245] [Active     ] [            ] [       ] [   80] Guava: Google Core Libraries for Java (14.0.1)
[ 246] [Active     ] [            ] [       ] [   80] Metrics Core (3.0.2)
[ 247] [Active     ] [            ] [       ] [   80] The Netty Project (3.9.0.Final)
[ 248] [Active     ] [            ] [       ] [   80] mvn:net.jpountz.lz4/lz4/1.2.0
[ 249] [Active     ] [            ] [       ] [   80] org.xerial.snappy.snappy-java (1.0.4)
[ 250] [Active     ] [            ] [       ] [   80] DataStax Java Driver for Apache Cassandra - Core (2.1.3)
[ 251] [Active     ] [            ] [       ] [   80] Camel :: Cassandra (1.1.0)
[ 267] [Active     ] [            ] [Started] [   80] Camel Cassandra Servicemix Aiport Route Example (1.0.0.SNAPSHOT)
[ 268] [Active     ] [            ] [       ] [   50] Apache ServiceMix :: Bundles :: commons-csv (1.0.0.r706899_5)
[ 269] [Active     ] [            ] [       ] [   50] camel-csv (2.14.1)

```

We can also verify everything is ok by looking at the log. Type the following command inside karaf:

```shell

karaf@root> log:tail

```

You should see something like this:

```shell

2015-06-06 19:45:19,836 | INFO  | l for user karaf | ultOsgiApplicationContextCreator | 106 - org.springframework.osgi.extender - 1.2.1 | Discovered configurations {osgibundle:/META-INF/spring/*.xml} in bundle [Camel Cassandra Servicemix Aiport Route Example (com.github.oscerd.camel-cassandra-servicemix-airport-example)]
2015-06-06 19:45:19,843 | INFO  | ExtenderThread-1 | OsgiBundleXmlApplicationContext  | 101 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Refreshing OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-airport-example, config=osgibundle:/META-INF/spring/*.xml): startup date [Sat Jun 06 19:45:19 CEST 2015]; root of context hierarchy
2015-06-06 19:45:19,845 | INFO  | ExtenderThread-1 | OsgiBundleXmlApplicationContext  | 101 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Application Context service already unpublished
2015-06-06 19:45:19,849 | INFO  | ExtenderThread-1 | XmlBeanDefinitionReader          | 99 - org.apache.servicemix.bundles.spring-beans - 3.2.11.RELEASE_1 | Loading XML bean definitions from URL [bundle://252.0:0/META-INF/spring/camel-context.xml]
2015-06-06 19:45:19,993 | INFO  | ExtenderThread-1 | CamelNamespaceHandler            | 117 - org.apache.camel.camel-spring - 2.15.2 | OSGi environment detected.
2015-06-06 19:45:20,505 | INFO  | ExtenderThread-1 | WaiterApplicationContextExecutor | 106 - org.springframework.osgi.extender - 1.2.1 | No outstanding OSGi service dependencies, completing initialization for OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-airport-example, config=osgibundle:/META-INF/spring/*.xml)
2015-06-06 19:45:20,513 | INFO  | ExtenderThread-2 | DefaultListableBeanFactory       | 99 - org.apache.servicemix.bundles.spring-beans - 3.2.11.RELEASE_1 | Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@19af3bb9: defining beans [template,consumerTemplate,camel1:beanPostProcessor,camel1,camelCassandraBuilder,cassandraCsvInsertBean]; root of factory hierarchy
2015-06-06 19:45:20,767 | INFO  | ExtenderThread-2 | OsgiSpringCamelContext           | 113 - org.apache.camel.camel-core - 2.15.2 | Apache Camel 2.15.2 (CamelContext: camel1) is starting
2015-06-06 19:45:20,768 | INFO  | ExtenderThread-2 | ManagedManagementStrategy        | 113 - org.apache.camel.camel-core - 2.15.2 | JMX is enabled
2015-06-06 19:45:20,943 | INFO  | ExtenderThread-2 | OsgiSpringCamelContext           | 113 - org.apache.camel.camel-core - 2.15.2 | AllowUseOriginalMessage is enabled. If access to the original message is not needed, then its recommended to turn this option off as it may improve performance.
2015-06-06 19:45:20,943 | INFO  | ExtenderThread-2 | OsgiSpringCamelContext           | 113 - org.apache.camel.camel-core - 2.15.2 | StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
2015-06-06 19:45:21,000 | INFO  | ExtenderThread-2 | OsgiSpringCamelContext           | 113 - org.apache.camel.camel-core - 2.15.2 | Route: route1 started and consuming from: Endpoint[file:///tmp/in/]
2015-06-06 19:45:21,000 | INFO  | ExtenderThread-2 | OsgiSpringCamelContext           | 113 - org.apache.camel.camel-core - 2.15.2 | Total 1 routes, of which 1 is started.
2015-06-06 19:45:21,001 | INFO  | ExtenderThread-2 | OsgiSpringCamelContext           | 113 - org.apache.camel.camel-core - 2.15.2 | Apache Camel 2.15.2 (CamelContext: camel1) started in 0.233 seconds
2015-06-06 19:45:21,002 | INFO  | ExtenderThread-2 | OsgiBundleXmlApplicationContext  | 101 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Publishing application context as OSGi service with properties {org.springframework.context.service.name=com.github.oscerd.camel-cassandra-servicemix-airport-example, Bundle-SymbolicName=com.github.oscerd.camel-cassandra-servicemix-airport-example, Bundle-Version=1.0.0.SNAPSHOT}
2015-06-06 19:45:21,009 | INFO  | ExtenderThread-2 | ContextLoaderListener            | 106 - org.springframework.osgi.extender - 1.2.1 | Application context successfully refreshed (OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-airport-example, config=osgibundle:/META-INF/spring/*.xml))

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

2015-03-14 11:54:08,435 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 27238,"ZYHB","large_airport","Taiping Airport",45.6234016418457,126.25,457,"AS","CN","CN-23","Harbin","yes","ZYHB","HRB",,,"http://en.wikipedia.org/wiki/Harbin_Taiping_International_Airport",
2015-03-14 11:54:08,435 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,444 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,444 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,447 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 31576,"ZYHE","medium_airport","Heihe Airport",50.1716209371,127.308883667,8530,"AS","CN","CN-23","Heihe","yes","ZYHE","HEK",,,"http://en.wikipedia.org/wiki/Heihe_Airport",
2015-03-14 11:54:08,448 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,458 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,458 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,462 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 31701,"ZYJL","small_airport","Jilin Airport",44.0022010803223,126.396003723145,,"AS","CN","CN-22","Jilin","no","ZYJL","JIL",,,,
2015-03-14 11:54:08,462 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,471 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,471 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,474 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 27239,"ZYJM","medium_airport","Jiamusi Airport",46.8433990479,130.464996338,262,"AS","CN","CN-23","Jiamusi","yes","ZYJM","JMU",,,"http://en.wikipedia.org/wiki/Jiamusi_Airport",
2015-03-14 11:54:08,474 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,484 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,484 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,488 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 313337,"ZYJX","medium_airport","Jixi Xingkaihu Airport",45.293,131.193,760,"AS","CN","CN-23","Jixi","yes","ZYJX","JXA",,,"http://en.wikipedia.org/wiki/Jixi_Xingkaihu_Airport",
2015-03-14 11:54:08,489 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,501 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,501 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,504 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 31706,"ZYJZ","medium_airport","Jinzhou Airport",41.1013984680176,121.061996459961,,"AS","CN","CN-21","Jinzhou","yes","ZYJZ","JNZ",,,"http://en.wikipedia.org/wiki/Jinzhou_Airport","Xiaolingzi Air Base"
2015-03-14 11:54:08,504 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,514 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,514 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,518 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 307012,"ZYLD","medium_airport","Lindu Airport",47.7520555556,129.019125,791,"AS","CN","CN-23","Yichun","yes","ZYLD","LDS",,,"http://en.wikipedia.org/wiki/Yichun_Lindu_Airport","Yichun Shi Airport"
2015-03-14 11:54:08,518 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,529 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,529 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,532 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 300516,"ZYLS","medium_airport","Yushu Batang Airport",32.8363888889,97.0363888889,12816,"AS","CN","CN-63","Yushu","no","ZYLS","YUS",,,"http://en.wikipedia.org/wiki/Yushu_Batang_Airport",
2015-03-14 11:54:08,532 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,542 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,543 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,545 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 27240,"ZYMD","medium_airport","Mudanjiang Hailang International Airport",44.5241012573,129.569000244,883,"AS","CN","CN-23","Mudanjiang","yes","ZYMD","MDG",,,"http://en.wikipedia.org/wiki/Mudanjiang_Airport","Mudanjiang"
2015-03-14 11:54:08,546 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,556 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,556 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,560 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 301193,"ZYMH","medium_airport","Gu-Lian Airport",52.9127777778,122.43,1836,"AS","CN","CN-23","Mohe","yes","ZYMH","OHE",,,"http://en.wikipedia.org/wiki/Mohe_Gulian_Airport",
2015-03-14 11:54:08,560 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,570 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,570 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,573 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 27241,"ZYQQ","medium_airport","Qiqihar Sanjiazi Airport",47.2396011352539,123.917999267578,477,"AS","CN","CN-23","Qiqihar","yes","ZYQQ","NDG",,,"http://en.wikipedia.org/wiki/Qiqihar_Airport",
2015-03-14 11:54:08,573 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,585 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,586 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,589 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 307017,"ZYTH","small_airport","Tahe Airport",52.2244444444,124.720222222,1240,"AS","CN","CN-23","Tahe","no","ZYTH",,,,,
2015-03-14 11:54:08,589 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,599 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,599 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,602 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 27242,"ZYTL","large_airport","Zhoushuizi Airport",38.9656982421875,121.539001464844,107,"AS","CN","CN-21","Dalian","yes","ZYTL","DLC",,,"http://en.wikipedia.org/wiki/Dalian_Zhoushuizi_International_Airport","Dalian Air Base"
2015-03-14 11:54:08,603 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,612 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,612 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,615 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 307013,"ZYTN","medium_airport","Tonghua Sanyuanpu Airport",42.2538888889,125.703333333,1200,"AS","CN","CN-22","Tonghua","yes","ZYTN","TNH",,,"http://en.wikipedia.org/wiki/Tonghua_Sanyuanpu_Airport","Tonghua Liuhe Airport"
2015-03-14 11:54:08,615 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,626 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,626 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,629 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 27243,"ZYTX","large_airport","Taoxian Airport",41.6398010253906,123.483001708984,198,"AS","CN","CN-21","Shenyang","yes","ZYTX","SHE",,,"http://en.wikipedia.org/wiki/Shenyang_Taoxian_International_Airport",
2015-03-14 11:54:08,629 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,639 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,639 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,642 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 27244,"ZYYJ","medium_airport","Yanji Chaoyangchuan Airport",42.8828010559,129.451004028,624,"AS","CN","CN-22","Yanji","yes","ZYYJ","YNJ",,,"http://en.wikipedia.org/wiki/Yanji_Chaoyangchuan_Airport",
2015-03-14 11:54:08,642 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,651 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,651 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,655 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 32753,"ZYYY","medium_airport","Shenyang Dongta Airport",41.7844009399414,123.496002197266,,"AS","CN","CN-21","Shenyang","no","ZYYY",,,,,
2015-03-14 11:54:08,655 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,665 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,665 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,669 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 46378,"ZZ-0001","heliport","Sealand Helipad",51.894444,1.4825,40,"EU","GB","GB-ENG","Sealand","no",,,,"http://www.sealandgov.org/","http://en.wikipedia.org/wiki/Principality_of_Sealand","Roughs Tower Helipad"
2015-03-14 11:54:08,669 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,678 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,678 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,681 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 307326,"ZZ-0002","small_airport","Glorioso Islands Airstrip",-11.5842777778,47.2963888889,11,"AF","TF","TF-U-A","Grande Glorieuse","no",,,,,,
2015-03-14 11:54:08,681 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database
2015-03-14 11:54:08,691 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-03-14 11:54:08,692 | INFO  |  file:///tmp/in/ | Cluster                          | ?                                   ? | 250 - com.datastax.driver.core - 2.1.3 | New Cassandra host /127.0.0.1:9042 added
2015-03-14 11:54:08,695 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Unmarshal della riga corrente: 313629,"ZZZZ","small_airport","Satsuma Iwo-jima Airport",30.7847222222,130.270555556,338,"AS","JP","JP-46","Mishima-Mura","yes","RJX7",,,,"http://wikimapia.org/6705190/Satsuma-Iwo-jima-Airport","SATSUMA,IWOJIMA,RJX7"
2015-03-14 11:54:08,695 | INFO  |  file:///tmp/in/ | route4                           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Inserimento dati riga corrente nel database

.
.
.

```

# The route

This route executes 46240 inserts on Apache Cassandra airport table on the simplex keyspace.
