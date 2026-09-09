package ch.szclsb.test.jpabatch.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "source_contacts")
@SequenceGenerator(name = "source_gen_contact", sequenceName = "seq_contact", allocationSize = 1)
public class SourceContactEntity extends ContactEntity {
    @Id @GeneratedValue(generator = "source_gen_contact", strategy = GenerationType.SEQUENCE)
    private Long id;
}
