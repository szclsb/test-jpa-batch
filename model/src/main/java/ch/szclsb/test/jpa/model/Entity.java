package ch.szclsb.test.jpa.model;

import java.io.Serializable;

public interface Entity<ID extends Serializable> {
    ID getId();
}
