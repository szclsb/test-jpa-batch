package ch.szclsb.test.jpa.model;

import java.io.Serializable;

public interface TargetEntity<ID extends Serializable> {
    ID getSourceId();
}
