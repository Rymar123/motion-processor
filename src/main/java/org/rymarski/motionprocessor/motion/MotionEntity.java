package org.rymarski.motionprocessor.motion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "MOTIONS")
class MotionEntity extends AbstractEntity {
    private String name;
    private String content;
    private String status;
    private String rejectionReason;
}
