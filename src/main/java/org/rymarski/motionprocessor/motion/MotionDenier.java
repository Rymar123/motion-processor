package org.rymarski.motionprocessor.motion;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.rymarski.motionprocessor.motion.Motion.Status.VERIFIED;

@Service
@NoArgsConstructor
@Slf4j
class MotionDenier implements MotionStatusChanger {
    @Override
    public void run(MotionEntity motion) {
        String current = motion.getStatus();
        log.info("Current status for motion with id {} is {}. Denying motion...", motion.getId(), current);

        switch (Motion.Status.valueOf(current)) {
            case CREATED:
                log.info("Motion denied - status set to {}", Motion.Status.DELETED);
                motion.setStatus(Motion.Status.DELETED.name());
                break;
            case VERIFIED:
            case ACCEPTED:
                log.info("Motion denied - status set to {}", Motion.Status.REJECTED);
                motion.setStatus(Motion.Status.REJECTED.name());
                break;
            case PUBLISHED:
            case DELETED:
            case REJECTED:
                String message = "Motion with statuses PUBLISHED, DELETED and REJECTED can not be denied.";
                log.error(message);
                throw new UnableToChangeMotionStatusException(message);
            default:
                throw new IllegalStateException("Unexpected value: " + Motion.Status.valueOf(current));
        }
    }
}
