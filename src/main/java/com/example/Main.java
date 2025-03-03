package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Entreprise.class)
                .buildSessionFactory();

//        // Insert multiple entreprises (Exercise 1)
//        insertMultipleEntreprises(factory);
//
//        // Retrieve an Entreprise by ID (Exercise 2)
//        Entreprise entreprise = getEntrepriseById(factory, 1);
//        if (entreprise != null) {
//            System.out.println("Retrieved Entreprise: " + entreprise.getNomEnt());
//        } else {
//            System.out.println("Entreprise not found.");
//        }

//        // Exo3
//        // Retrieve all entreprises
//        List<Entreprise> entreprises = getAllEntreprises();
//        System.out.println("All Entreprises:");
//        for (Entreprise e : entreprises) {
//            System.out.println(e.getNomEnt());
//        }
//
//        // Retrieve entreprises by name
//        List<Entreprise> entreprisesByName = getEntrepriseByName("Tech Corp");
//        System.out.println("Entreprises with name 'Tech Corp':");
//        for (Entreprise e : entreprisesByName) {
//            System.out.println(e.getNomEnt());
//        }
//
//        // Retrieve all entreprises sorted by number of employees
//        List<Entreprise> entreprisesSorted = getAllEntreprisesSortedByEmployees();
//        System.out.println("Entreprises sorted by number of employees:");
//        for (Entreprise e : entreprisesSorted) {
//            System.out.println(e.getNomEnt() + " - " + e.getNbEmployee());
//        }
//
//        // Count the number of entreprises
//        long count = getEntrepriseCount();
//        System.out.println("Number of entreprises: " + count);
//

        // Update an Entreprise's name
        updateEntrepriseName(1, " Tech Corp 2");

        // Delete an Entreprise
        deleteEntreprise(2);

        // Close the SessionFactory
        factory.close();
    }

    // Method to insert multiple entreprises (Exercise 1)
    public static void insertMultipleEntreprises(SessionFactory factory) {
        Session session = factory.getCurrentSession();

        try {
            // Start a transaction
            session.beginTransaction();

            // Create and save multiple entreprises
            Entreprise entreprise1 = new Entreprise();
            entreprise1.setNomEnt("Tech Corp");
            entreprise1.setListeActivities("Software Development");
            entreprise1.setNbEmployee(100);
            session.save(entreprise1);

            Entreprise entreprise2 = new Entreprise();
            entreprise2.setNomEnt("Health Inc");
            entreprise2.setListeActivities("Healthcare");
            entreprise2.setNbEmployee(200);
            session.save(entreprise2);

            Entreprise entreprise3 = new Entreprise();
            entreprise3.setNomEnt("Edu Solutions");
            entreprise3.setListeActivities("Education");
            entreprise3.setNbEmployee(150);
            session.save(entreprise3);

            // Commit the transaction
            session.getTransaction().commit();

            System.out.println("Multiple entreprises inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve an Entreprise by ID (Exercise 2)
    public static Entreprise getEntrepriseById(SessionFactory factory, int id) {
        Session session = factory.getCurrentSession();

        try {
            // Start a transaction
            session.beginTransaction();

            // Retrieve the Entreprise by ID
            Entreprise entreprise = session.get(Entreprise.class, id);

            // Commit the transaction
            session.getTransaction().commit();

            return entreprise;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // get all rows from the table Entreprises
    public static List<Entreprise> getAllEntreprises() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Entreprise.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // Retrieve all Entreprises
            List<Entreprise> entreprises = session.createQuery("FROM Entreprise").list();

            session.getTransaction().commit();

            return entreprises;
        } finally {
            factory.close();
        }
    }

    // get rows by the entreprise name
    public static List<Entreprise> getEntrepriseByName(String name) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Entreprise.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // Retrieve Entreprises by name
            List<Entreprise> entreprises = session.createQuery("FROM Entreprise WHERE nomEnt = :name")
                    .setParameter("name", name)
                    .list();

            session.getTransaction().commit();
            return entreprises;
        } finally {
            factory.close();
        }
    }

    // get the rows by Employess number sorted
    public static List<Entreprise> getAllEntreprisesSortedByEmployees() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Entreprise.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // Retrieve all Entreprises sorted by nbEmployee
            List<Entreprise> entreprises = session.createQuery("FROM Entreprise ORDER BY nbEmployee")
                    .list();

            session.getTransaction().commit();
            return entreprises;
        } finally {
            factory.close();
        }
    }

    // count the number of rowns in the entreprises table
    public static long getEntrepriseCount() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Entreprise.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            // Start a transaction
            session.beginTransaction();

            // Count the number of rows
            long count = (Long) session.createQuery("SELECT COUNT(*) FROM Entreprise")
                    .uniqueResult();

            // Commit the transaction
            session.getTransaction().commit();

            return count;
        } finally {
            factory.close();
        }
    }

    //Exercise 4

    // update function
    public static void updateEntrepriseName(int id, String newName) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Entreprise.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // Retrieve the Entreprise by ID
            Entreprise entreprise = session.get(Entreprise.class, id);

            // Update the name
            if (entreprise != null) {
                entreprise.setNomEnt(newName);
                session.update(entreprise);
            }

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    // delete by id
    public static void deleteEntreprise(int id) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Entreprise.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // Retrieve the Entreprise by ID
            Entreprise entreprise = session.get(Entreprise.class, id);

            // Delete the Entreprise
            if (entreprise != null) {
                session.delete(entreprise);
            }

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}