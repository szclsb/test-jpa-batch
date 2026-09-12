package ch.szclsb.test.springjdbc.model;

import java.io.Serializable;

public interface Entity<ID extends Serializable> {
    ID getId();
}
