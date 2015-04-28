package ntu.music.pojo;

import java.util.HashSet;
import java.util.Set;


/**
 * TSinger entity. @author MyEclipse Persistence Tools
 */

public class TSinger  implements java.io.Serializable {


    // Fields    

     private Integer singerid;
     private TArea TArea;
     private String singername;
     private Integer sex;
     private String description;
     private String picture;
     private Set TMusics = new HashSet(0);
     private Set TAlbums = new HashSet(0);


    // Constructors

    /** default constructor */
    public TSinger() {
    }

    
    /** full constructor */
    public TSinger(TArea TArea, String singername, Integer sex, String description, String picture, Set TMusics, Set TAlbums) {
        this.TArea = TArea;
        this.singername = singername;
        this.sex = sex;
        this.description = description;
        this.picture = picture;
        this.TMusics = TMusics;
        this.TAlbums = TAlbums;
    }

   
    // Property accessors

    public Integer getSingerid() {
        return this.singerid;
    }
    
    public void setSingerid(Integer singerid) {
        this.singerid = singerid;
    }

    public TArea getTArea() {
        return this.TArea;
    }
    
    public void setTArea(TArea TArea) {
        this.TArea = TArea;
    }

    public String getSingername() {
        return this.singername;
    }
    
    public void setSingername(String singername) {
        this.singername = singername;
    }

    public Integer getSex() {
        return this.sex;
    }
    
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return this.picture;
    }
    
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Set getTMusics() {
        return this.TMusics;
    }
    
    public void setTMusics(Set TMusics) {
        this.TMusics = TMusics;
    }

    public Set getTAlbums() {
        return this.TAlbums;
    }
    
    public void setTAlbums(Set TAlbums) {
        this.TAlbums = TAlbums;
    }
   








}