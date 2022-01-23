package org.rymarski.motionprocessor.motion;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Slf4j
class MotionPromoter implements MotionStatusChanger {
  @Override
  public void run(MotionEntity motion) {
    String current = motion.getStatus();
    log.info("Current status for motion with id {} is {}. Promoting...", motion.getId(), current);

    switch (Motion.Status.valueOf(current)) {
      case CREATED:
        log.info("Promoted status to {}", Motion.Status.VERIFIED);
        motion.setStatus(Motion.Status.VERIFIED.name());
        break;
      case VERIFIED:
        log.info("Promoted status to {}", Motion.Status.ACCEPTED);
        motion.setStatus(Motion.Status.ACCEPTED.name());
        break;
      case ACCEPTED:
        log.info("Promoted status to {}", Motion.Status.PUBLISHED);
        motion.setStatus(Motion.Status.PUBLISHED.name());
        break;
      case PUBLISHED:
      case DELETED:
      case REJECTED:
        throw new UnableToChangeMotionStatusException("Motion with statuses PUBLISHED, DELETED and REJECTED can not be promoted.");
      default:
        throw new IllegalStateException("Unexpected value: " + Motion.Status.valueOf(current));
    }
  }
}
