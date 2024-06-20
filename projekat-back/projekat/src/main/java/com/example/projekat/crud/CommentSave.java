package com.example.projekat.crud;
import javax.persistence.*;
public class CommentSave {

    @Id
    private int commentId;

    private String naslov;
    private String tekst;

    public CommentSave( String tekst, String naslov) {

        this.tekst = tekst;
        this.naslov = naslov;
    }

    public CommentSave() {

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