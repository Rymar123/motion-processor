package org.rymarski.motionprocessor.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class MotionUpdateRequest {
  @NotNull(message = "Id is mandatory")
  private Long id;
  @NotBlank(message = "Edited content is mandatory")
  private String editedContent;
}
