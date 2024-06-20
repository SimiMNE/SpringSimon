package com.example.projekat.controller;

import com.example.projekat.crud.CommentSave;
import com.example.projekat.crud.CommentUpdate;
import com.example.projekat.entity.Comment;
import com.example.projekat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;



    @PostMapping(path = "/add")
    public String addComment(@RequestBody CommentSave commentSave){
        String id = commentService.addComment(commentSave);
        return id;
    }

    @GetMapping(path = "/getAllComments")
    public List<Comment> getAllComments(){
        List<Comment> allComments = commentService.getAllComments();
        return allComments;
    }

    @PutMapping(path = "/update")
    public String updateComment(@RequestBody CommentUpdate commentUpdate){
        String id = commentService.updateComment(commentUpdate);
        return id;
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteComment(@PathVariable(value = "id")int id) {
        boolean deleteComment = commentService.deleteComment(id);
        return "deleted";
    }}
