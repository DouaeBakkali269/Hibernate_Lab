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

        // Create a Session
        Session session = factory.getCurrentSession();

        try {
            // Create a new Entreprise object
            Entreprise entreprise = new Entreprise();
            entreprise.setNomEnt("Tech Corp");
            entreprise.setListeActivities("Software Development");
            entreprise.setNbEmployee(100);

            // Start a transaction
            session.beginTransaction();

            // Save the Entreprise object
            session.save(entreprise);

            // Commit the transaction
            session.getTransaction().commit();

            System.out.println("Entreprise saved with ID: " + entreprise.getIdEntreprise());
        } finally {
            factory.close();
        }
    }
}