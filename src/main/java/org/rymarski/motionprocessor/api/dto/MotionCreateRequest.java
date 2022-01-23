package org.rymarski.motionprocessor.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class MotionCreateRequest {
  @NotBlank(message = "Name is mandatory")
  private String name;
  @NotBlank(message = "Content is mandatory")
  private String content;
}
