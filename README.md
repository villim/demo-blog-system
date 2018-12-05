# demo-blog-system

Demo Blog Management System

## API Documents

Please check with Swagger api documents after app running

![Swagger](https://github.com/villim/demo-blog-system/blob/master/documents/swagger-apis-doc.png)

(if you cant see the picture, please click it)

## Technique Brief Info

* DDD design
* Spring Boot
* H2 ( Spring5 do not support SQLite well, change to H2 for saving time ) 
* Gradle
* Hazelcast
* Swagger
* RPM
 
## How to Run

### 1. Prepare configuration folder

```bash
$ mkdir -p /opt/demo/{app,config,logs,h2db}/blog
```

* **/opt/demo/app/blog** is for excutable SpringBoot Jar file while deployment install with RPM ( not need for local testing )
* **/opt/demo/config/blog** is for Blog application configuration files
* **/opt/demo/logs/blog** is for Blog application log files
* **/opt/demo/h2db/blog** is for Blog application H2DB files

### 2. Prepare configuration files

Copy properties files from blog-init/src/main/resources
```bash
$ cd ~/demo-blog-system/
$ copy -R blog-init/src/main/resources /opt/demo/config/blog
```

Copy H2DB data files:
```bash
$ cd ~/demo-blog-system/
$ copy /blog-init/DB-SCRIPTS/*.db /opt/demo/config/blog

```


### 3. Check H2 Database Schema (Optional)

Not necessary, but if you want to check Database schema, you may follow next steps.

This instructions is only for MacOS, Windows please refer to [H2 Quickstart](http://h2database.com/html/quickstart.html)

First, install H2 with

```bash
$ brew install h2
```

Then Run H2 just with:

```bash
$ h2
```

Open Link in Browser:
```bash
http://localhost:8082/
```

Then configure as following picture:

![H2DB Login](https://github.com/villim/demo-blog-system/blob/master/documents/H2DB-login.png)

(if you cant see the picture, please click it)

```text
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:/opt/demo/h2db/blog/blogDb;DB_CLOSE_DELAY=-1
User Name: sa
Password: sa
```

After logged in, you can see Schemas as:

![H2DB Schemas](https://github.com/villim/demo-blog-system/tree/master/documents/H2DB-schemas.png)

(if you cant see the picture, please click it)


If TABLES and SEQUENCE not there, you can re-create with 

```
/blog-init/DB-SCRIPTS/db-revision001.sql
```

Make sure you exit this H2 instance before run blog-rest-app!!

```bash
ctrl + c
```


### 4. Run App

```bash
$ cd ~/demo-blog-system
$ gradle clean build
$ java -jar blog-rest-app/build/libs/blog-rest-app-1.0.0-SNAPSHOT-a908931.jar
``` 

Now you should be able to see Swagger page: 
```
http://localhost:8080/demo-blog-system/swagger-ui.html
```

And Log file is at: 
```
/opt/demo/logs/blog/blog-rest-app/blog-rest-app.log
```
