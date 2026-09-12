package ch.szclsb.test.springjdbc.model;

import java.io.Serializable;

public interface TargetEntity<ID extends Serializable> {
    ID getSourceId();
}
