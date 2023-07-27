package com.wagondepot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ScaleSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int sequenceNumber;
    private String wagonNumber;
    private String cargoCode;
    private double cargoWeight;
    private double wagonWeight;
}
