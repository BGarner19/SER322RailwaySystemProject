# SER322 Railway System Project - Team 6

##Database and Program Setup

1. You must add postgresql-42.2.2.jar as a necessary library. 
2. This program uses an installation of PostgreSQL 10.3, which can be downloaded as an installer at www.enterprisedb.com/downloads/postgres-postgresql-downloads Note that this program defaults to port 5432, username postgres, and password 322
3. This program was created using an installation of PostgreSQL running on port 5432. Running it on a different port will require changing the call in main.Start.java to use a different port
4. You must create a database in pgAdmin 4. Create a database using the statement CREATE DATABASE Team6RailwayDB or using pgAdmin 4's create database dialog box. 
5. Note that the program's creation of the database method defaults to a database name of Team6RailwayDB. You may create a different named database by changing the call in the RailwaySystem constructor to use your database name. 
6. This program uses the default installation user "postgres" with a password of "322". This can be set in the database constructor parameters. 
7. Run the main method in main.Start.java. The GUI should open and you should find that the database is created and filled with sample data. You may need to refresh the pgAdmin 4 database to see changes. 
8. When writing queries into the "Query Database" box at the bottom of the panel, ensure that you always preface table names with the name of the schema, in this case, "Railway". 
9. Find Tickets... returns ticketID's that match whatever parameters you enter into the text fields. You may fill or leave blank any fields that you wish. 
10. Find Passengers... Returns passenger names that match whatever parameters you enter into the text fields. You may fill out one, both, or none of the fields and hit search to return results. 
11. Find Trains... Returns trainID's that match the parameters you enter into the text fields. You may fill out none, one, or both fields to retrieve results. 
12. Find Routes... Returns route names that match the paramters you enter into the text fields. You may fill out none, one, or both fields to retrieve results. 

##Team 6 Developers

Bailey Garner

Rachel Knoche

Canyon Schubert

Johnathan Sinicrope
 
