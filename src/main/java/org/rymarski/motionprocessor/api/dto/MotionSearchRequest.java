package org.rymarski.motionprocessor.api.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.rymarski.motionprocessor.pageable.PageableSearchRequest;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MotionSearchRequest extends PageableSearchRequest {
    private String name;
    private String status;
}
