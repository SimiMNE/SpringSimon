package com.example.projekat;

import com.example.projekat.controller.UserController;
import com.example.projekat.entity.User;
import com.example.projekat.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    private void setUserField(User user, String fieldName, String value) throws Exception {
        Field field = User.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(user, value);
    }

    @Test
    public void testInitRoleAndUser() throws Exception {
        doNothing().when(userService).initRoleAndUser();

        // Calling the init method using a POST request
        mockMvc.perform(post("/initRoleAndUser"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterNewUser() throws Exception {
        User user = new User();
        setUserField(user, "username", "testUser");
        setUserField(user, "password", "testPassword");

        when(userService.registerNewUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/registerNewUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testUser\", \"password\": \"testPassword\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"username\": \"testUser\", \"password\": \"testPassword\"}"));
    }

    @Test
    @WithMockUser(roles = "Admin")
    public void testForAdmin_withAdminRole() throws Exception {
        mockMvc.perform(get("/forAdmin"))
                .andExpect(status().isOk())
                .andExpect(content().string("This URL is only accessible to the admin"));
    }

    @Test
    @WithMockUser(roles = "User")
    public void testForAdmin_withUserRole() throws Exception {
        mockMvc.perform(get("/forAdmin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "User")
    public void testForUser_withUserRole() throws Exception {
        mockMvc.perform(get("/forUser"))
                .andExpect(status().isOk())
                .andExpect(content().string("This URL is only accessible to the user"));
    }

    @Test
    @WithMockUser(roles = "Admin")
    public void testForUser_withAdminRole() throws Exception {
        mockMvc.perform(get("/forUser"))
                .andExpect(status().isForbidden());
    }
}
