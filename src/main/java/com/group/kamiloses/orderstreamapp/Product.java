package com.group.kamiloses.orderstreamapp;

public class Product {

    @Id
    private String id; // Unikalny identyfikator produktu

    private String name; // Nazwa produktu
    private String description; // Opis produktu
    private double price; // Cena produktu
    private int stock; // Ilość dostępna w magazynie

    // Gettery i settery
}