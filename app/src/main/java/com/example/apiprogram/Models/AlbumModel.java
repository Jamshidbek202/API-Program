package com.example.apiprogram.Models;

public class AlbumModel {

    int userID, albumID;
    String title, author_name;

    public AlbumModel(int userID, int albumID, String title, String author_name) {
        this.userID = userID;
        this.albumID = albumID;
        this.title = title;
        this.author_name = author_name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
}
