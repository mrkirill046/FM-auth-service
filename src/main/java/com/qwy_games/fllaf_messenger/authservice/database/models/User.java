package com.qwy_games.fllaf_messenger.authservice.database.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 32)
    private String link;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, length = 100)
    private String email;

    @Column(name = "phone_number", unique = true, length = 15)
    private String phoneNumber;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String surname;

    @Column(length = 125)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date createdAt;
}
