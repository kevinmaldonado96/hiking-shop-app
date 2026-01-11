package org.example.hickingshop.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.hickingshop.entities.PersonalInformation;
import org.example.hickingshop.repository.PersonalInformationRepository;
import org.example.hickingshop.validators.anotations.UniqueEmail;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {


    private final PersonalInformationRepository personalInformationRepository;

    UniqueEmailValidator(PersonalInformationRepository personalInformationRepository) {
        this.personalInformationRepository = personalInformationRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        if(email == null || email.isEmpty()) return true;
        return personalInformationRepository.findPersonalInformationByEmail(email) == null;
    }
}
