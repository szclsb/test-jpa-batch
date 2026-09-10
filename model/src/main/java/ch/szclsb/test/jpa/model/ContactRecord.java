package ch.szclsb.test.jpa.model;

import java.time.LocalDate;

public record ContactRecord(
        long id,
        long version,
        boolean valid,
        String externalId,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        Sex sex,
        String email,
        String phone,
        String language,
        String languageAlternative,
        String street,
        Integer postalCode,
        String city,
        String country
) {
}
