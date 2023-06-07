package com.emte.usermanager;

import com.emte.dto.UserDto;
import com.emte.usermanager.service.UserService;
import com.emte.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserPublicControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @JsonView(Views.UserView.class)
    @Test
    public void testGetUser() throws Exception {
        UserDto userDto = new UserDto();
        when(userService.getUser(anyInt())).thenReturn(userDto);

        mockMvc.perform(get("/public/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @JsonView(Views.UserView.class)
@Test
public void testUpdateUser() throws Exception {
    UserDto userDto = new UserDto();
    when(userService.updateUser(anyInt(), any())).thenReturn(userDto);

    MvcResult result = mockMvc.perform(put("/public/users/{id}", 1)
            .contentType("application/json")
            .content("{ \"name\": \"John Doe\", \"email\": \"john.doe@example.com\" }"))
            .andExpect(status().isOk())
            .andReturn();

    String responseBody = result.getResponse().getContentAsString();
    System.out.println("Response body: " + responseBody);

    mockMvc.perform(asyncDispatch(result))
            .andExpect(jsonPath("$.name").value("John Doe"))
            .andExpect(jsonPath("$.email").value("john.doe@example.com"));
}

    @Test
    public void testDeleteUser() throws Exception {
        when(userService.deleteUser(anyInt())).thenReturn(true);

        mockMvc.perform(delete("/public/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @JsonView(Views.UserView.class)
    @Test
    public void testCreateUser() throws Exception {
        UserDto userDto = new UserDto();
        when(userService.createUser(any())).thenReturn(userDto);

        mockMvc.perform(post("/public/user")
                .contentType("application/json")
                .content("{ \"name\": \"John Doe\", \"email\": \"john.doe@example.com\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testAuthentication() throws Exception {
        when(userService.authenticate(any())).thenReturn(true);

        mockMvc.perform(post("/public/auth")
                .contentType("application/json")
                .content("{ \"login\": \"john.doe\", \"password\": \"password123\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @JsonView(Views.UserView.class)
    @Test
    public void testGetUsers() throws Exception {
        UserDto userDto = new UserDto();
        when(userService.getUsers()).thenReturn(Collections.singletonList(userDto));

        mockMvc.perform(get("/public/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }
}
