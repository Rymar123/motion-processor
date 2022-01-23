package org.rymarski.motionprocessor.motion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
public class UnableToChangeMotionStatusException extends RuntimeException {
  public UnableToChangeMotionStatusException(String message) {
    super(message);
  }
}
