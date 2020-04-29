package com.springboot.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_Poetry", schema = "test")
public class TPoetry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;//自增
    private String poetryname;//古诗名
    private String text;//原文
    private String translation;//译文
    private String appreciation;//赏析
    private String note;//注释
    private String type;//类别
    private String poetname;//作者名
    public TPoetry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoetryname() {
        return poetryname;
    }

    public void setPoetryname(String poetryname) {
        this.poetryname = poetryname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoetname() {
        return poetname;
    }

    public void setPoetname(String poetname) {
        this.poetname = poetname;
    }
}
