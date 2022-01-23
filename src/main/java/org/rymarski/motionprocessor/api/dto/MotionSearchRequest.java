package org.rymarski.motionprocessor.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.rymarski.motionprocessor.pageable.PageableSearchRequest;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MotionSearchRequest extends PageableSearchRequest {
  private String name;
  private String status;
}
