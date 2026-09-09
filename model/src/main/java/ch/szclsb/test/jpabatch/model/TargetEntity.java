package ch.szclsb.test.jpabatch.model;

import java.io.Serializable;

public interface TargetEntity<ID extends Serializable> {
    ID getSourceId();
}
