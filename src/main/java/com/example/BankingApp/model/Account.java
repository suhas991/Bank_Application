package com.example.BankingApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="ID_SEQ")
    private long id;

    @Column(length = 50)
    private String holderName;

    @Column(length = 50)
    @OrderBy("holderName asc")
    private double balance;

    @Column(length = 10)
    private long phoneNumber;

    @Column(length = 50,nullable = false)
    private String accType="Saving";

}
