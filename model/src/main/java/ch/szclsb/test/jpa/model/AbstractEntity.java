package ch.szclsb.test.jpa.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Entity<ID> {
    @Version
    private long version;
}
