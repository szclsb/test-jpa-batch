package ch.szclsb.test.springjdbc.api.client;

import ch.szclsb.test.springjdbc.model.ContactRecord;
import com.fasterxml.jackson.core.type.TypeReference;

import java.net.URI;
import java.util.List;

public class Executor {
    public static void main(String[] args) {
        var client = new ApiClient();
        try {
            var uri = URI.create(args[0]);
            var typeRef = new TypeReference<List<ContactRecord>>() {
            };
            var startTime = System.currentTimeMillis();
            var contacts = client.fetch(uri, "GET", typeRef);
            var endTime = System.currentTimeMillis();
            System.out.printf("fetched %d contacts in %dms%n", contacts.size(), endTime - startTime);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
