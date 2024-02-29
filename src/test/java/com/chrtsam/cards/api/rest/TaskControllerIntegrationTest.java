package com.chrtsam.cards.api.rest;

import com.chrtsam.cards.api.BaseTest;
import com.chrtsam.cards.api.model.external.AuthRequest;
import com.chrtsam.cards.api.model.external.TaskDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Chris
 */
@SpringBootTest()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskControllerIntegrationTest extends BaseTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain).build();
    }

    private HttpHeaders getMemberAuthHeaders() throws Exception {
        return authenticateAndGetAuthHeaders("member_one@company.gr", "AStrongPass");
    }

    private HttpHeaders getAdminAuthHeaders() throws Exception {
        return authenticateAndGetAuthHeaders("admin@company.gr", "AStrongPass");
    }

    @Test
    public void shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/card/1")).andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturnUnauthorizedWithWrongCredentials() throws Exception {
        AuthRequest authrequest = new AuthRequest("member_zero@comapny.gr", "pass");
        mockMvc.perform(post("/user/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authrequest))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturnTask() throws Exception {
        mockMvc.perform(get("/card/1")
                .headers(getMemberAuthHeaders()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNotFoundTask() throws Exception {
        mockMvc.perform(get("/card/100")
                .headers(getMemberAuthHeaders()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnNotFoundTaskDueToPermissions() throws Exception {
        mockMvc.perform(get("/card/6")
                .headers(getMemberAuthHeaders()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnTaskDueToAdminPermission() throws Exception {
        mockMvc.perform(get("/card/6")
                .headers(getAdminAuthHeaders()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateNewTask() throws Exception {
        TaskDTO task = new TaskDTO();
        task.setName("IntegrationTask");
        task.setDescription("Task from app's test");
        task.setColor("#ebdcc8");
        mockMvc.perform(post("/card")
                .headers(getAdminAuthHeaders())
                .contentType("application/json;charset=UTF-8")
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().stringValues("location", "http://localhost/card/11"));
    }

    @Test
    public void shouldFailOnCreateNewTaskColorValidation() throws Exception {
        TaskDTO task = new TaskDTO();
        task.setName("IntegrationTask");
        task.setDescription("Task from app's test");
        task.setColor("ebdcc8");
        mockMvc.perform(post("/card")
                .headers(getAdminAuthHeaders())
                .contentType("application/json;charset=UTF-8")
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldFailOnCreateNewTaskNameValidation() throws Exception {
        TaskDTO task = new TaskDTO();
        task.setDescription("Task from app's test");
        task.setColor("#ebdcc8");
        mockMvc.perform(post("/card")
                .headers(getAdminAuthHeaders())
                .contentType("application/json;charset=UTF-8")
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isBadRequest());
    }

}
