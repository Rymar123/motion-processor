package org.rymarski.motionprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.rymarski.motionprocessor.api.dto.MotionCreateRequest;
import org.rymarski.motionprocessor.api.dto.MotionUpdateRequest;
import org.rymarski.motionprocessor.motion.MotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MotionProcessorApplication.class)
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = {"classpath:test_db_setup.sql"}, executionPhase = BEFORE_TEST_METHOD)

public class MotionControllerTest {

  @Autowired
  private MockMvc mockMvc;
  private ObjectMapper mapper = new ObjectMapper();

  @Test
  @Order(1)
  @WithMockUser(username = "manager", password = "manager", roles = "MANAGER")
  public void shouldAuthenticateSearchRequestsForManager() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/motion/search"))
            .andExpect(status().isOk());
  }

  @Test
  @Order(2)
  @WithMockUser(username = "manager", password = "manager", roles = "MANAGER")
  public void shouldAuthenticateGetRequestsForManager() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/motion")
                    .param("id", "111"))
            .andExpect(status().isOk());
  }

  @Test
  @Order(3)
  @WithMockUser(username = "manager", password = "manager", roles = "MANAGER")
  public void shouldAuthenticateCreateRequestsForManager() throws Exception {
    MotionCreateRequest request = new MotionCreateRequest("testMotion", "testContent");

    mockMvc.perform(MockMvcRequestBuilders.post("/motion")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isOk());
  }

  @Test
  @Order(4)
  @WithMockUser(username = "manager", password = "manager", roles = "MANAGER")
  public void shouldAuthenticateUpdateRequestsForManager() throws Exception {
    MotionUpdateRequest request = new MotionUpdateRequest(111L, "testContent");

    mockMvc.perform(MockMvcRequestBuilders.patch("/motion")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isOk());
  }

  @Test
  @Order(5)
  @WithMockUser(username = "manager", password = "manager", roles = "MANAGER")
  public void shouldAuthenticatePromoteRequestsForManager() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/motion/progress")
                    .param("id", "111"))
            .andExpect(status().isOk());
  }

  @Test
  @Order(6)
  @WithMockUser(username = "manager", password = "manager", roles = "MANAGER")
  public void shouldAuthenticateDenyRequestsForManager() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/motion/deny")
                    .param("id", "111"))
            .andExpect(status().isOk());
  }

  @Test
  @Order(7)
  @WithMockUser(username = "user", password = "user", roles = "USER")
  public void shouldAuthenticateSearchRequestsForUser() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/motion/search"))
            .andExpect(status().isOk());
  }

  @Test
  @Order(8)
  @WithMockUser(username = "user", password = "user", roles = "USER")
  public void shouldAuthenticateGetRequestsForUser() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/motion")
                    .param("id", "111"))
            .andExpect(status().isOk());
  }

  @Test
  @Order(9)
  @WithMockUser(username = "user", password = "user", roles = "USER")
  public void shouldNotAuthenticateCreateRequestsForUser() throws Exception {
    MotionCreateRequest request = new MotionCreateRequest("testMotion", "testContent");

    mockMvc.perform(MockMvcRequestBuilders.post("/motion")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isForbidden());
  }

  @Test
  @Order(10)
  @WithMockUser(username = "user", password = "user", roles = "USER")
  public void shouldNotAuthenticateUpdateRequestsForUser() throws Exception {
    MotionUpdateRequest request = new MotionUpdateRequest(111L, "testContent");

    mockMvc.perform(MockMvcRequestBuilders.patch("/motion")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isForbidden());
  }

  @Test
  @Order(11)
  @WithMockUser(username = "user", password = "user", roles = "USER")
  public void shouldNotAuthenticatePromoteRequestsForUser() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/motion/progress")
                    .param("id", "111"))
            .andExpect(status().isForbidden());
  }

  @Test
  @Order(12)
  @WithMockUser(username = "user", password = "user", roles = "USER")
  public void shouldNotAuthenticateDenyRequestsForUser() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/motion/deny")
                    .param("id", "111"))
            .andExpect(status().isForbidden());
  }
}
