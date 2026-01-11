package org.example.hickingshop.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "personal_information")
public class PersonalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    private String address;
    private String email;

    @Builder
    public PersonalInformation(Long id, String name, String lastname, String address, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
    }
}
