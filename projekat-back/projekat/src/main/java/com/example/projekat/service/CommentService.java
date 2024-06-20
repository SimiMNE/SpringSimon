package com.example.projekat.service;

import com.example.projekat.crud.CommentSave;
import com.example.projekat.crud.CommentUpdate;
import com.example.projekat.dao.CommentDao;
import com.example.projekat.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {


    @Autowired
    private CommentDao commentDao;
    public String addComment(CommentSave commentSave){
        Comment comment = new Comment(
                commentSave.getNaslov(),
                commentSave.getTekst()
        );

        commentDao.save(comment);
        return comment.getNaslov();
    }

    public List<Comment> getAllComments() {
        List<Comment> getComments  = commentDao.findAll();
        return getComments;
    }



    public String updateComment(CommentUpdate commentUpdate) {
        Optional<Comment> optionalComment = commentDao.findById(commentUpdate.getCommentId());
        if (optionalComment.isPresent()) {
            Comment komentar = optionalComment.get();
            komentar.setTekst(commentUpdate.getTekst());
            komentar.setNaslov(commentUpdate.getNaslov());
            commentDao.save(komentar);
        } else {
            System.out.println("Tvoj komentar ne postoji");
        }
        return null;
    }

    public boolean deleteComment(int id) {
        if (commentDao.existsById(id)){
            commentDao.deleteById(id);
        }else{
            System.out.println("Nismo pronasli ni jedan komentar sa tim id-em!!!");
        }
        return true;
    }
}