/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

import java.util.ArrayList;
import javax.swing.JOptionPane;


class User {
    
   
    private int userID;
    private String name;
    private String contactInfo;
    private ArrayList<Book> borrowedBooks;

    public User(int userID, String name, String contactInfo) {
        this.userID = userID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBooks = new ArrayList<>();
    }

    

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        System.out.println("Book added to borrowed books list: " + book.getTitle());
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
    @Override

    public String toString() {
        return "User ID: " + userID + ", Name: " + name + ", Contact Info: " + contactInfo + ", Borrowed Books: " + borrowedBooks.size();
    }
    
}
