package org.rymarski.motionprocessor.motion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Motion implements Serializable {
  private static final long serialVersionUID = -5748057917225641646L;

  private long id;
  private int version;
  private OffsetDateTime createdDate;
  private OffsetDateTime modifiedDate;
  private String createdBy;
  private String modifiedBy;
  private String name;
  private String content;
  private Status status;
  private String rejectionReason;

  enum Status {
    CREATED,
    VERIFIED,
    ACCEPTED,
    PUBLISHED,
    DELETED,
    REJECTED
  }
}
