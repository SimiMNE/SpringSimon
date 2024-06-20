package com.example.projekat.entity;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    private String naslov;
    private String tekst;

    public Comment(int commentId, String tekst, String naslov) {
        this.commentId = commentId;
        this.tekst = tekst;
        this.naslov = naslov;
    }

    public Comment() {

    }

    public Comment(String naslov, String tekst) {
        this.naslov = naslov;
        this.tekst = tekst;
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
