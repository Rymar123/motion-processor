package org.rymarski.motionprocessor;

import org.junit.jupiter.api.Test;
import org.rymarski.motionprocessor.api.dto.MotionCreateRequest;
import org.rymarski.motionprocessor.motion.MotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = MotionProcessorApplication.class)
@Transactional
public class MotionServiceTest {

  @Autowired
  private MotionService motionService;

  @Test
  public void shouldAddNewMotion() throws Exception {
    motionService.create(new MotionCreateRequest("motion1", "content1"));
  }

  //todo add more cases
}
