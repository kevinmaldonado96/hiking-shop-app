package org.example.hickingshop.repository;

import org.example.hickingshop.entities.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Long> {

    PersonalInformation findPersonalInformationByEmail(String email);

}
