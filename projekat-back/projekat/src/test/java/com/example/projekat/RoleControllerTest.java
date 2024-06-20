package com.example.projekat;

import com.example.projekat.controller.RoleController;
import com.example.projekat.entity.Role;
import com.example.projekat.service.RoleService;
import com.example.projekat.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoleController.class)
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @MockBean
    private JwtUtil jwtUtil;  // Mocking JwtUtil

    private Role role;

    @BeforeEach
    public void setup() {
        role = new Role();
        role.setRoleName("ROLE_USER");
    }

    @Test
    public void testCreateNewRole() throws Exception {
        Mockito.when(roleService.createNewRole(Mockito.any(Role.class))).thenReturn(role);

        String roleJson = "{\"roleName\":\"ROLE_USER\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/createNewRole")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(roleJson))
                .andExpect(status().isOk())
                .andExpect(content().json(roleJson));
    }
}
