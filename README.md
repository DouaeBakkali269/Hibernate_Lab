# Hibernate Lab - Managing Database Persistence

This repository contains a Java project that demonstrates how to use **Hibernate** for managing database persistence with MySQL. The project includes examples of inserting, retrieving, updating, and deleting data from a database table.

## Project Overview

This project is a hands-on lab to learn Hibernate, a popular Java framework for Object-Relational Mapping (ORM). It includes the following exercises:

1. **Exercise 1**: Inserting data into the `Entreprises` table.
2. **Exercise 2**: Retrieving an `Entreprise` by its ID.
3. **Exercise 3**: Querying the database using HQL (Hibernate Query Language).
4. **Exercise 4**: Updating and deleting data in the `Entreprises` table.



## Technologies Used

- **Java**: The programming language used for the project.
- **Hibernate**: The ORM framework used to manage database persistence.
- **MySQL**: The relational database used to store data.
- **Maven**: The build tool used to manage dependencies.



## Project Structure
```
hibernate-lab/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           ├── Main.java            # Main class to run Hibernate operations
│   │   │           ├── Entreprise.java      # Entity class representing the Entreprises table
│   │   │           └── HibernateUtil.java   # Utility class for managing SessionFactory
│   │   └── resources/
│   │       └── hibernate.cfg.xml           # Hibernate configuration file
│   └── test/                               # (Optional) Test classes
├── pom.xml                                 # Maven configuration file
└── README.md                               # Project documentation
```



## Features

- **Insert Data:** `insertMultipleEntreprises()`
- **Retrieve Data:** `getEntrepriseById(id)`
- **Query with HQL:** `getAllEntreprisesSortedByEmployees()`
- **Update & Delete:** `updateEntrepriseName(id, newName)`, `deleteEntreprise(id)`   
