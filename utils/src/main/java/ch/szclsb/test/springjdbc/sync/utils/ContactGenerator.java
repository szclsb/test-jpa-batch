package ch.szclsb.test.springjdbc.sync.utils;

import ch.szclsb.test.springjdbc.model.ContactRecord;
import ch.szclsb.test.springjdbc.model.Sex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class ContactGenerator {
    private ContactGenerator() {
    }

    public static List<ContactRecord> generate(int size) {
        var records = new ArrayList<ContactRecord>(size);
        for (var i = 0; i < size; i++) {
            var source = new ContactRecord(i, 0,
                    true,
                    "ext-%d".formatted(i),
                    "Max",
                    "Muster",
                    LocalDate.now(),
                    Sex.MALE,
                    "max.muster-%d@exmaple.com".formatted(i),
                    "0791234567",
                    "de",
                    "fr",
                    "Musterstrasse",
                    1234,
                    "Musterhausen",
                    "CH"
            );
            records.add(source);
        }
        return records;
    }
}
