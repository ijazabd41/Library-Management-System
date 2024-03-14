/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

class Book  {
    
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private boolean availability;

    public Book(int bookID, String title, String author, String genre) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = true;
    }
    public int getBookID() {
        return bookID;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getGenre() {
        return genre;
    }
    public boolean isAvailable() {
        return availability;
    }
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    @Override
    public String toString() {
        return "Book ID: " + bookID + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Availability: " + (availability ? "Available" : "Not Available");
    }
}
