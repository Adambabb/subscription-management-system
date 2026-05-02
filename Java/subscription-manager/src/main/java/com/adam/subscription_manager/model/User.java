package com.adam.subscription_manager.model;

import jakarta.persistence.*; 
import java.time.LocalDateTime;

@Entity 
@Table(name = "USERS")
public class User {

    @Id 
    /* 
       ESTRATEGIA UNIVERSAL PARA ORACLE:
       1. Usamos SEQUENCE en lugar de IDENTITY para que funcione en Oracle 11g y superiores.
       2. 'generator' vincula este ID con el configurador de abajo.
    */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_config")
    
    /* 
       CONFIGURADOR DE LA SECUENCIA:
       - name: nombre interno para Java.
       - sequenceName: EL NOMBRE REAL QUE TIENE LA SECUENCIA EN ORACLE (USERS_SEQ).
       - allocationSize = 1: Indica que los números van de 1 en 1.
    */
    @SequenceGenerator(name = "user_seq_config", sequenceName = "USERS_SEQ", allocationSize = 1)
    @Column(name = "USERID")
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "LASTNAME", nullable = false)
    private String lastname;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "DNI", nullable = false, unique = true)
    private String dni;

    @Column(name = "USRPASS", nullable = false)
    private String password;

    @Column(name = "CREATE_DATE", updatable = false)
    private LocalDateTime createDate;

    // --- CONSTRUCTORES ---

    public User() {
    }

    public User(String username, String lastname, String email, String dni, String password) {
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.dni = dni;
        this.password = password;
        this.createDate = LocalDateTime.now(); 
    }

    // --- ¡NO OLVIDES GENERAR GETTERS Y SETTERS AQUÍ! ---
}