package com.example;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "Entreprises")
@Data

public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEntreprise;

    private String nomEnt;
    private String listeActivities;
    private int nbEmployee;
}
