package com.devsan.seenitassignment.model;

import java.io.Serializable;

public class BookVO implements Serializable {

    private int id;
    private String title;
    private String isbn;
    private int price;
    private String currencyCode;
    private String author;
    private String description;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPrice() {
        return price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }
}
