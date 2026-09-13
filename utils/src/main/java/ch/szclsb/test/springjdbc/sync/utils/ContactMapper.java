package ch.szclsb.test.springjdbc.sync.utils;

import ch.szclsb.test.springjdbc.model.ContactRecord;
import ch.szclsb.test.springjdbc.model.TargetContactEntity;

public final class ContactMapper {
    private ContactMapper() {
    }

    public static TargetContactEntity mapToTarget(ContactRecord record) {
        var target = new TargetContactEntity();
        target.setSourceId(record.id());
        target.setVersion(record.version());
        target.setValid(record.valid());
        target.setExternalId(record.externalId());
        target.setFirstName(record.firstName());
        target.setLastName(record.lastName());
        target.setDateOfBirth(record.dateOfBirth());
        target.setSex(record.sex());
        target.setEmail(record.email());
        target.setPhone(record.phone());
        target.setLanguage(record.language());
        target.setLanguageAlternative(record.languageAlternative());
        target.setStreet(record.street());
        target.setPostalCode(record.postalCode());
        target.setCity(record.city());
        target.setCountry(record.country());
        return target;
    }
}
