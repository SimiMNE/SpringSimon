package com.example.projekat.crud;

import javax.persistence.*;

public class CommentUpdate {

    @Id
    private int commentId;

    private String naslov;
    private String tekst;

    public CommentUpdate(int commentId, String tekst, String naslov) {
        this.commentId = commentId;
        this.tekst = tekst;
        this.naslov = naslov;
    }

    public CommentUpdate() {

    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getNaslov(){
        return  naslov;
    }

    public void setNaslov(String naslov){
        this.naslov = naslov;
    }
    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
}
