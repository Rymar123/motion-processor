package org.rymarski.motionprocessor.motion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class UnableToEditMotion extends RuntimeException {
  public UnableToEditMotion(Long id) {
    super(String.format("Motion with id: %d can no longer be edited.", id));
  }
}
