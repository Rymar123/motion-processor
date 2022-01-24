package org.rymarski.motionprocessor.motion;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
abstract class AbstractEntity {

    @EmbeddedId
    @Setter
    CompositeId compositeId = new CompositeId();

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    public long getId() {
        return this.compositeId.getId();
    }

    public int getVersion() {
        return this.compositeId.getVersion();
    }

    public void setId(long id) {
        this.compositeId.setId(id);
    }

    public void setVersion(int version) {
        this.compositeId.setVersion(version);
    }

    public void incrementVersion() {
        final int curr = this.getVersion();
        this.getCompositeId().setVersion(curr + 1);
    }
}