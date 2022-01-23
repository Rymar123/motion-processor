package org.rymarski.motionprocessor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MotionProcessorApplication.class)
@AutoConfigureMockMvc
@Transactional
public class MotionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(roles = "MANAGER")
  public void shouldAuthenticateGetRequestsForManager() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/motion/search"))
            .andExpect(status().isOk());
  }

  //todo add more cases
}
