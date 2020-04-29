package com.springboot.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_Poet", schema = "test")
public class TPoet {
    //诗人表
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;//自增
    private String poetname;//诗人名
    private String life;//生平介绍
    private String dynasty;//朝代
    private String gender;//“男” “女”
    private String img;//画像

    public TPoet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPoetname() {
        return poetname;
    }

    public void setPoetname(String poetname) {
        this.poetname = poetname;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
