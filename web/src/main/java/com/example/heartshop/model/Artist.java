package com.example.heartshop.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    @Column(name = "artist_address")
    private String address;

    public Artist(Long id, String name, String country, String address) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
    }

    public Artist(String name, String country, String address) {
        this.name = name;
        this.country = country;
        this.address = address;
    }

    public Artist(String name, String address) {
        this.name=name;
        this.address=address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public Artist() {

    }
}
