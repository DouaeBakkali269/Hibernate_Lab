package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Create a SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Entreprise.class)
                .buildSessionFactory();

        // Insert multiple entreprises (Exercise 1)
        insertMultipleEntreprises(factory);

        // Retrieve an Entreprise by ID (Exercise 2)
        Entreprise entreprise = getEntrepriseById(factory, 1);
        if (entreprise != null) {
            System.out.println("Retrieved Entreprise: " + entreprise.getNomEnt());
        } else {
            System.out.println("Entreprise not found.");
        }

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
}