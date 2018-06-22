package com.example.quang.remember.model;

public class ItemNote {

    int id;
    String date;
    String text;
    String sound;
    String image;
    String photo;
    String title;

    public ItemNote(int id, String date, String text, String sound, String image, String photo,String title) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.sound = sound;
        this.image = image;
        this.photo = photo;
        this.title = title;
    }

    public ItemNote() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
