package com.example.hibernaterelationships.jpa.domain;

import com.example.hibernaterelationships.jpa.domain.converters.YesNoBooleanConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Student {

    @Id
    private long id;

//    @Temporal(TemporalType.DATE)
//    private Date birthDate;

    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Convert(converter = YesNoBooleanConverter.class)
    private boolean wantsNewsletter;

    @Embedded
    //We should note that, since JPA 2.2, the @AttributeOverride annotation is repeatable.
   // @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "ST_STREET"))//,
            @AttributeOverride(name = "number", column = @Column(name = "ST_NUMBER"))//,
            @AttributeOverride(name = "city", column = @Column(name = "ST_CITY"))
   // })
    private Address address;


    public Long id() {
        return id;
    }


}
