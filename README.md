# Simple Spring Boot API
This API deals with a database that contains students and admins, each admin has one or more roles in the database. using this API, you can add or delete admins, roles, and students.

# Getting Started

## Getting Started

#### **Use this Links to use the API**

Authentication and Authorization

* Login User, POST Request 
(http://localhost:8080/api/login)

* Refresh the access token of the logged in user GET Request
(http://localhost:8080/api/v1/token/refresh)

students:
* To Show all students in the database GET Request
(http://localhost:8080/api/v1/students/)

* To add Student in the database, POST Request 
(http://localhost:8080/api/v1/students/) 

* To delete Student in the database, DELETE Request 
(http://localhost:8080/api/v1/students/) 

* To edit Student in the database, PUT Request 
(http://localhost:8080/api/v1/students/) 

Admins:
* To add admin to the database POST Request
(http://localhost:8080/api/v1/admins/save)

* To Show all admins in the database GET Request
(http://localhost:8080/api/v1/admins/)

* To add role to the database POST Request
(http://localhost:8080/api/v1/roles/save)

* To add role to an existing admin to the database POST Request
(http://localhost:8080/api/v1/roles/addtoadmin)

### Built with

* 1- PosgreSQL server
* 2- postman
* 3- Springboot 
