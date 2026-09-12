package ch.szclsb.test.springjdbc.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "target_contacts")
@GenericGenerator(name = "target_gen", type = SourceIdGenerator.class)
public class TargetContactEntity extends ContactEntity implements TargetEntity<Long> {
    @Id @GeneratedValue(generator = "target_gen")
    private Long id;

    @Transient
    private Long sourceId;
}
