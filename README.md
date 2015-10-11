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

The Apache Servicemix 5.5.0 is the correct release.

- Download the Apache Servicemix 5.5.0 package from: http://servicemix.apache.org/downloads/servicemix-5.5.0.html

- Unzip the package in a directory (we denote this folder with $SERVICEMIX_HOME)

- Execute $SERVICEMIX_HOME/bin/servicemix

- Inside Karaf execute the following instructions:

```shell

karaf@root> features:addurl mvn:com.github.oscerd/camel-cassandra/1.3.0/xml/features


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

karaf@root> osgi:install -s file:///$PROJECT_HOME/target/camel-cassandra-servicemix-airport-example-1.0.0-SNAPSHOT.jar

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

2015-10-11 10:23:39,735 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,744 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,744 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,779 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 313994,"ZYFY","medium_airport","Dongji Aiport",48.199494,134.366447,,"AS","CN","CN-23","Fuyuan","yes","ZYFY","FYJ",,,"https://en.wikipedia.org/wiki/Fuyuan_Dongji_Airport",
2015-10-11 10:23:39,779 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,789 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,789 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,793 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 27238,"ZYHB","large_airport","Taiping Airport",45.6234016418457,126.25,457,"AS","CN","CN-23","Harbin","yes","ZYHB","HRB",,,"http://en.wikipedia.org/wiki/Harbin_Taiping_International_Airport",
2015-10-11 10:23:39,793 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,802 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,803 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,806 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 31576,"ZYHE","medium_airport","Heihe Airport",50.1716209371,127.308883667,8530,"AS","CN","CN-23","Heihe","yes","ZYHE","HEK",,,"http://en.wikipedia.org/wiki/Heihe_Airport",
2015-10-11 10:23:39,806 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,815 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,815 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,820 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 31701,"ZYJL","small_airport","Jilin Airport",44.002201080322266,126.39600372314453,,"AS","CN","CN-22","Jilin","no","ZYJL","JIL",,,,
2015-10-11 10:23:39,820 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,829 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,829 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,833 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 27239,"ZYJM","medium_airport","Jiamusi Airport",46.84339904789999,130.464996338,262,"AS","CN","CN-23","Jiamusi","yes","ZYJM","JMU",,,"http://en.wikipedia.org/wiki/Jiamusi_Airport",
2015-10-11 10:23:39,833 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,842 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,842 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,845 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 313337,"ZYJX","medium_airport","Jixi Xingkaihu Airport",45.293,131.193,760,"AS","CN","CN-23","Jixi","yes","ZYJX","JXA",,,"http://en.wikipedia.org/wiki/Jixi_Xingkaihu_Airport",
2015-10-11 10:23:39,845 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,854 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,854 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,858 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 31706,"ZYJZ","medium_airport","Jinzhou Airport",41.10139846801758,121.06199645996094,,"AS","CN","CN-21","Jinzhou","yes","ZYJZ","JNZ",,,"http://en.wikipedia.org/wiki/Jinzhou_Airport","Xiaolingzi Air Base"
2015-10-11 10:23:39,858 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,867 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,867 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,871 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 307012,"ZYLD","medium_airport","Lindu Airport",47.7520555556,129.019125,791,"AS","CN","CN-23","Yichun","yes","ZYLD","LDS",,,"http://en.wikipedia.org/wiki/Yichun_Lindu_Airport","Yichun Shi Airport"
2015-10-11 10:23:39,871 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,880 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,880 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,883 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 300516,"ZYLS","medium_airport","Yushu Batang Airport",32.836388888900004,97.0363888889,12816,"AS","CN","CN-63","Yushu","no","ZYLS","YUS",,,"http://en.wikipedia.org/wiki/Yushu_Batang_Airport",
2015-10-11 10:23:39,883 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,892 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,892 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,896 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 27240,"ZYMD","medium_airport","Mudanjiang Hailang International Airport",44.5241012573,129.569000244,883,"AS","CN","CN-23","Mudanjiang","yes","ZYMD","MDG",,,"http://en.wikipedia.org/wiki/Mudanjiang_Airport","Mudanjiang"
2015-10-11 10:23:39,896 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,905 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,905 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,908 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 301193,"ZYMH","medium_airport","Gu-Lian Airport",52.912777777799995,122.43,1836,"AS","CN","CN-23","Mohe","yes","ZYMH","OHE",,,"http://en.wikipedia.org/wiki/Mohe_Gulian_Airport",
2015-10-11 10:23:39,908 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,918 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,918 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,922 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 27241,"ZYQQ","medium_airport","Qiqihar Sanjiazi Airport",47.239601135253906,123.91799926757812,477,"AS","CN","CN-23","Qiqihar","yes","ZYQQ","NDG",,,"http://en.wikipedia.org/wiki/Qiqihar_Airport",
2015-10-11 10:23:39,922 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,932 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,932 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,935 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 307017,"ZYTH","small_airport","Tahe Airport",52.2244444444,124.720222222,1240,"AS","CN","CN-23","Tahe","no","ZYTH",,,,,
2015-10-11 10:23:39,935 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,944 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,945 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,948 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 27242,"ZYTL","large_airport","Zhoushuizi Airport",38.9656982421875,121.53900146484375,107,"AS","CN","CN-21","Dalian","yes","ZYTL","DLC",,,"http://en.wikipedia.org/wiki/Dalian_Zhoushuizi_International_Airport","Dalian Air Base"
2015-10-11 10:23:39,948 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,957 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,957 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,961 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 307013,"ZYTN","medium_airport","Tonghua Sanyuanpu Airport",42.2538888889,125.703333333,1200,"AS","CN","CN-22","Tonghua","yes","ZYTN","TNH",,,"http://en.wikipedia.org/wiki/Tonghua_Sanyuanpu_Airport","Tonghua Liuhe Airport"
2015-10-11 10:23:39,961 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,970 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,970 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,973 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 27243,"ZYTX","large_airport","Taoxian Airport",41.639801025390625,123.48300170898438,198,"AS","CN","CN-21","Shenyang","yes","ZYTX","SHE",,,"http://en.wikipedia.org/wiki/Shenyang_Taoxian_International_Airport",
2015-10-11 10:23:39,974 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:39,982 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:39,983 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:39,986 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 27244,"ZYYJ","medium_airport","Yanji Chaoyangchuan Airport",42.8828010559,129.451004028,624,"AS","CN","CN-22","Yanji","yes","ZYYJ","YNJ",,,"http://en.wikipedia.org/wiki/Yanji_Chaoyangchuan_Airport",
2015-10-11 10:23:39,986 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:40,034 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:40,034 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:40,038 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 32753,"ZYYY","medium_airport","Shenyang Dongta Airport",41.784400939941406,123.49600219726562,,"AS","CN","CN-21","Shenyang","no","ZYYY",,,,,
2015-10-11 10:23:40,038 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Inserting current row in Apache Cassandra
2015-10-11 10:23:40,047 | INFO  |  file:///tmp/in/ | DCAwareRoundRobinPolicy          | 298 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 10:23:40,047 | INFO  |  file:///tmp/in/ | Cluster                          | 298 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 10:23:40,050 | INFO  |  file:///tmp/in/ | route3                           | 124 - org.apache.camel.camel-core - 2.15.2 | Unmarshalling current row: 46378,"ZZ-0001","heliport","Sealand Helipad",51.894444,1.4825,40,"EU","GB","GB-ENG","Sealand","no",,,,"http://www.sealandgov.org/","http://en.wikipedia.org/wiki/Principality_of_Sealand","Roughs Tower Helipad"

.
.
.

```

# The route

This route executes 46240 inserts on Apache Cassandra airport table on the simplex keyspace.
