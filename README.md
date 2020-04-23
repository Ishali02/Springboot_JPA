# Springboot_JPA
This is a simple Springboot Apache Maven project implementing Hibernate, JPA and rest web services. A simple example of Login Security is used to understand and implement this technologies. The goal is to provide menu access to the users based on their roles. 

## About the Project
The project implements login service in which user is allowed to access the web pages based on their roles. Using this, we can restrict a user to access only the desired pages while blocking the admin pages. The relation between the tables is mapped using hibernate mapping. JPA repository is used to store/modify the data in database.

You can use this sample service to understand the conventions and configurations that allow you to create a RESTful service. Once you understand and get comfortable with the sample app you can add your own services following the same patterns as the sample service.

Here is what this application demonstrates:

* Full integration with the Spring Framework: inversion of control, dependency injection, etc.
* Building RESTful service using annotation: supports JSON request / response.
* Spring Data Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations.
* Automatic CRUD functionality against the data source using Spring Repository pattern

## Pre-requisites
1. Java 8
2. IDE(Intellij)
3. MySQL Workbench
This application is a Maven Project with war packaging and embedded Tomcat 9. Springboot 2.2 version is used.  

## How To Run
The project consists of two different services viz. Login Security and LoginSecurityUI. Login Security service provides the restful web services while LoginSecurityUI service deals with web pages. Both services run on different ports.

### Login Security
This service deals with the rest api's running on port 8080. 

### Login Security UI
This service deals with running responsive UI on port 8081. It consists of tomcat 7 plugin to run web application. For further configurations please refer to this site :-https://www.shortn0tes.com/2017/01/tutorial-intellij-idea-community.html

### Database Schemas
Please refer to the schema.sql file and run the file in mysql workbench to get the desired table structure. After completing the setup, run data.sql script to populate the tables. This will allow you to login with Admin rights using the credentials : 
Username : admin
Password : admin
This is just an example, you can add more roles as per your requirement. 
To generate the new customize ID's in database tables, function get_next_id is used.


