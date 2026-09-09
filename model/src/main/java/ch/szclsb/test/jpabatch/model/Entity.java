package ch.szclsb.test.jpabatch.model;

import java.io.Serializable;

public interface Entity<ID extends Serializable> {
    ID getId();
}
