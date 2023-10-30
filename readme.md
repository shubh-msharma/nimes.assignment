# Nimesa Assignment 
The Nimesa Assignment application is a Spring Boot project designed to provide a set of REST APIs for managing and discovering EC2 instances, and S3 buckets.   


## Prerequisites
Before running the Nimesa Assignment Java application, ensure that you have the following prerequisites installed and configured on your system:

- Java Development Kit (JDK) 11
- Maven
- PostgreSQL Database

## Database Schema Overview  
The Nimesa Assignment application uses a PostgreSQL database to store and manage data. The database schema consists of the following key tables:

- Buckets: Stores information about S3 buckets, including their names and object counts.
- Instances: Contains data related to EC2 instances, including unique identifiers and creation timestamps.
- Jobs: Records information about various job tasks, including job identifiers, creation timestamps, job statuses, and reasons.
- S3Objects: Stores details about objects in S3 buckets, such as unique identifiers, associated bucket information, object keys, ETags, object sizes, and storage classes.  

Please note that while this high-level overview provides insight into the database structure, it doesn't include specific details of individual table columns, data types, or constraints. 

## Swagger API Documentation  
The Nimesa Assignment application provides interactive API documentation using Swagger. Swagger is a powerful tool that allows you to explore and test the available endpoints. You can access the Swagger documentation by visiting the following URL:

`http://localhost:6060/swagger-ui/index.html`

Use the Swagger documentation to understand the available API endpoints, their input parameters, and expected responses. This is a handy resource for developers and testers to work with the application's APIs.  

Below are the key features and functionalities provided by the application:  

- DiscoverServices: Asynchronously discovers AWS services, such as EC2 and S3. This method initiates separate threads to discover EC2 instances in the Mumbai Region and S3 buckets. It then persists the results in the database and returns a unique JobId for tracking the progress of the discovery process.

- GetJobResult: Retrieves the status of a specific job based on its JobId. The status can be one of the following: "Success," "In Progress," or "Failed."

- GetDiscoveryResult: Obtains information about a specific AWS service by providing its name. For S3, this includes a list of S3 buckets, while for EC2, it offers a list of instance IDs.

- GetS3BucketObjects: Discovers all the file names in a specified S3 bucket, persists the information in the database, and returns a JobId to track the job's progress.

- GetS3BucketObjectCount: Retrieves the count of objects in a specific S3 bucket from the database.

- GetS3BucketObjectlike: Fetches a list of file names from the database that match a given pattern within a particular S3 bucket.  

To access these APIs, the application requires specific environment variables, which are detailed below 

## Environment Variables
This application depends on the following environment variables for proper configuration and operation:

- `AWS_ACCESS_KEY`: The AWS access key for accessing AWS services.

- `AWS_SECRET_KEY`: The AWS secret key associated with the access key.

- `DB_HOST`: The hostname or IP address of the database server.

- `DB_PORT`: The port on which the database server is listening.

- `SPRING_DATASOURCE_USERNAME`: The username for the database connection.

- `SPRING_DATASOURCE_PASSWORD`: The password for the database connection.

- `SPRING_DATASOURCE_DRIVER_CLASS_NAME`: The driver class name for the database connection.  

## Running the Spring Boot Application  

To run the Nimesa Assignment Spring Boot application, please follow these steps:

- Clone the Repository: Begin by cloning the application's repository to your local environment.

`git clone https://github.com/shubh-msharma/nimes.assignment`  

- Build the Application: Use Maven or Gradle to build the application from the root directory.

`cd nimesa-assignment`  
`mvn clean install`  

- Run the Application: Start the Spring Boot application with the following command.

`java -jar target/nimesa-assignment-0.1.jar`  

Ensure that all the required environment variables mentioned above are set before running the application.

## Setting Up PostgreSQL Database  
The Nimesa Assignment application uses a PostgreSQL database for data storage. To set up the PostgreSQL database, follow these steps:

- Install PostgreSQL: If not already installed, install PostgreSQL on your server or local machine.

- Access PostgreSQL: Use the following command to access the PostgreSQL database as an administrative user.

`psql -U postgres`  

- Create a Database: Create a new database for the Nimesa Assignment application. You can name it according to your preferences.


`CREATE DATABASE nimesa_assignment;`  

- Create Database User: Create a user for the application and grant it access to the database.


`CREATE USER nimesa_user WITH PASSWORD 'your_password';`  
`ALTER ROLE nimesa_user SET client_encoding TO 'utf8';`  
`ALTER ROLE nimesa_user SET default_transaction_isolation TO 'read committed';`  
`ALTER ROLE nimesa_user SET timezone TO 'UTC';`  
`GRANT ALL PRIVILEGES ON DATABASE nimesa_assignment TO nimesa_user;`  

- Configure Application: Update the `SPRING_DATASOURCE_USERNAME` and `SPRING_DATASOURCE_PASSWORD` environment variables in the application's configuration to match the database user credentials you created.  

### Flyway Migration  

The Nimesa Assignment application employs **Flyway migration** to manage database schema changes seamlessly. With Flyway, you can version and automate your database schema evolution, ensuring that your database is always up to date. The SQL queries related to schema changes are included in the **migration script** for your reference.