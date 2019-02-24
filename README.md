# Java-RestService-example
Java-RestService-example

## Steps

### Change URL 
FilePath: /DR-RestServices/src/com/avinash/util/Utils.java

line 30 
```
public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/ny_cab_data", "root", "root");
	}
```
### Configure tomcat

### add project to tomcat

### below line in tomcat server.xml to use local sql driver
```
<!-- driverManagerProtection="false"  to avoid JNDI setup and run locally-->
  <Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener" driverManagerProtection="false"/>
```

### API call [Post Service]

#### Single Driver

URL: http://localhost:8080/DR-RestServices/helloworld/getcabcount

input
```
{
	"cabId":"5455D5FF2BD94D10B304A15D4B7F2735",
	"date":"01-12-2013",
	"cache":false
}
```

output
```
{
    "count": 2
}
```


#### Multiple driver

URL: http://localhost:8080/DR-RestServices/helloworld/getcabscount

input

```
{
	"cabIds":["00377E15077848677B32CE184CE7E871","5455D5FF2BD94D10B304A15D4B7F2735"],
	"date":"01-12-2013",
	"cache":true
	
}
```

output
```
{
    "data": [
        {
            "driver": "00377E15077848677B32CE184CE7E871",
            "count": 3
        },
        {
            "driver": "5455D5FF2BD94D10B304A15D4B7F2735",
            "count": 2
        }
    ]
}
```

