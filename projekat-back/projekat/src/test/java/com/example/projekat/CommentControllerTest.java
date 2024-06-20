package com.example.projekat;

import com.example.projekat.controller.CommentController;
import com.example.projekat.crud.CommentSave;
import com.example.projekat.crud.CommentUpdate;
import com.example.projekat.entity.Comment;
import com.example.projekat.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CommentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    public void testAddComment() throws Exception {
        CommentSave commentSave = new CommentSave();
        // Assuming CommentSave has a constructor or setter methods to set properties
        // commentSave.setContent("Test Comment");

        when(commentService.addComment(any(CommentSave.class))).thenReturn("1");

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"Test Comment\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        verify(commentService, times(1)).addComment(any(CommentSave.class));
    }

    @Test
    public void testGetAllComments() throws Exception {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        when(commentService.getAllComments()).thenReturn(comments);

        mockMvc.perform(get("/getAllComments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(commentService, times(1)).getAllComments();
    }

    @Test
    public void testUpdateComment() throws Exception {
        CommentUpdate commentUpdate = new CommentUpdate();
        // Assuming CommentUpdate has a constructor or setter methods to set properties
        // commentUpdate.setContent("Updated Comment");

        when(commentService.updateComment(any(CommentUpdate.class))).thenReturn("1");

        mockMvc.perform(put("/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"Updated Comment\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        verify(commentService, times(1)).updateComment(any(CommentUpdate.class));
    }

    @Test
    public void testDeleteComment() throws Exception {
        when(commentService.deleteComment(1)).thenReturn(true);

        mockMvc.perform(delete("/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("deleted"));

        verify(commentService, times(1)).deleteComment(1);
    }
}