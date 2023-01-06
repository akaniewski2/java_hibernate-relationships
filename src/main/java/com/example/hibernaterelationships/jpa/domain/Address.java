package com.example.hibernaterelationships.jpa.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
public class Address {



    private String city;
    private String street;
    private String number;


}
