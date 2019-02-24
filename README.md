# Java-RestService-example
Java-RestService-example

## Steps

### Change URL 
FilePath: /Java-RestService-example/src/com/avinash/util/Utils.java

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
Note: It sometime create some problem while getting connection, so restart the server or properly create JNDI connection.

### API call [Post Service]

#### Single Driver

[screenshot](single-driver-example.png)
"ProjectName" : Java-RestService-example
URL: http://localhost:8080/"ProjectName"/helloworld/getcabcount

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

[screenshot](multiple-driver-example.png)

"ProjectName": Java-RestService-example
URL: http://localhost:8080/"ProjectName"/helloworld/getcabscount

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

