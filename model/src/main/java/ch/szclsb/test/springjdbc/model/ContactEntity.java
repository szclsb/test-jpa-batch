package ch.szclsb.test.springjdbc.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class ContactEntity extends AbstractEntity<Long> {
    private boolean valid;
    private String externalId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String email;
    private String phone;
    private String language;
    private String languageAlternative;
    private String street;
    private Integer postalCode;
    private String city;
    private String country;
}
