package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Get the SessionFactory from HibernateUtil
        SessionFactory factory = HibernateUtil.getSessionFactory();

        // Insert multiple entreprises (Exercise 1)
        insertMultipleEntreprises(factory);

        // Retrieve an Entreprise by ID (Exercise 2)
        Entreprise entreprise = getEntrepriseById(factory, 1);
        if (entreprise != null) {
            System.out.println("Retrieved Entreprise: " + entreprise.getNomEnt());
        } else {
            System.out.println("Entreprise not found.");
        }

        // Exo3
        // Retrieve all entreprises
        List<Entreprise> entreprises = getAllEntreprises(factory);
        System.out.println("All Entreprises:");
        for (Entreprise e : entreprises) {
            System.out.println(e.getNomEnt());
        }

        // Retrieve entreprises by name
        List<Entreprise> entreprisesByName = getEntrepriseByName(factory, "Tech Corp");
        System.out.println("Entreprises with name 'Tech Corp':");
        for (Entreprise e : entreprisesByName) {
            System.out.println(e.getNomEnt());
        }

        // Retrieve all entreprises sorted by number of employees
        List<Entreprise> entreprisesSorted = getAllEntreprisesSortedByEmployees(factory);
        System.out.println("Entreprises sorted by number of employees:");
        for (Entreprise e : entreprisesSorted) {
            System.out.println(e.getNomEnt() + " - " + e.getNbEmployee());
        }

        // Count the number of entreprises
        long count = getEntrepriseCount(factory);
        System.out.println("Number of entreprises: " + count);

        // Exercise 4
        // Update an Entreprise's name
        updateEntrepriseName(factory, 1, "Tech Corp 2");

        // Delete an Entreprise
        deleteEntreprise(factory, 2);

        // Close the SessionFactory (optional, but recommended)
        HibernateUtil.shutdown();
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

            Entreprise entreprise4 = new Entreprise();
                entreprise4.setNomEnt("Green Energy");
                entreprise4.setListeActivities("Renewable Energy");
                entreprise4.setNbEmployee(50);

            Entreprise entreprise5 = new Entreprise();
                entreprise5.setNomEnt("Foodies");
                entreprise5.setListeActivities("Food Production");
                entreprise5.setNbEmployee(100);

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
            session.beginTransaction();
            Entreprise entreprise = session.get(Entreprise.class, id);
            session.getTransaction().commit();

            return entreprise;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all rows from the table Entreprises (Exercise 3)
    public static List<Entreprise> getAllEntreprises(SessionFactory factory) {
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // Retrieve all Entreprises
            List<Entreprise> entreprises = session.createQuery("FROM Entreprise").list();

            session.getTransaction().commit();

            return entreprises;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get rows by the entreprise name (Exercise 3)
    public static List<Entreprise> getEntrepriseByName(SessionFactory factory, String name) {
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // Retrieve Entreprises by name
            List<Entreprise> entreprises = session.createQuery("FROM Entreprise WHERE nomEnt = :name")
                    .setParameter("name", name)
                    .list();

            session.getTransaction().commit();
            return entreprises;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get the rows by Employees number sorted (Exercise 3)
    public static List<Entreprise> getAllEntreprisesSortedByEmployees(SessionFactory factory) {
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // Retrieve all Entreprises sorted by nbEmployee
            List<Entreprise> entreprises = session.createQuery("FROM Entreprise ORDER BY nbEmployee")
                    .list();

            session.getTransaction().commit();
            return entreprises;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Count the number of rows in the entreprises table (Exercise 3)
    public static long getEntrepriseCount(SessionFactory factory) {
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // Count the number of rows
            long count = (Long) session.createQuery("SELECT COUNT(*) FROM Entreprise")
                    .uniqueResult();

            session.getTransaction().commit();

            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    // Update function (Exercise 4)
    public static void updateEntrepriseName(SessionFactory factory, int id, String newName) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete by ID (Exercise 4)
    public static void deleteEntreprise(SessionFactory factory, int id) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}