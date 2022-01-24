package org.rymarski.motionprocessor.motion;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
class CompositeId implements Serializable {
    @SequenceGenerator(name = "idgen", sequenceName = "ownSeq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    private long id;

    private int version = 1;
}
