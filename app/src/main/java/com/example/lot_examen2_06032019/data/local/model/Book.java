package com.example.lot_examen2_06032019.data.local.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book")
public class Book {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String title;
    @NonNull
    private String author;
    @NonNull
    private int year;
    private String image;
    private String sinopsis;

    public Book(@NonNull String title, @NonNull String author, int year, String image, String sinopsis) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.image = image;
        this.sinopsis = sinopsis;
    }

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
