/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

import java.util.ArrayList;
import javax.swing.JOptionPane;

class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }
     public void removeUser(User user) {
        users.remove(user);
    }
     public void removeBook(Book book) {
        books.remove(book);
    }

    public void checkOutBook(User user, Book book) {
        if (book.isAvailable()) {
            book.setAvailability(false);
           // user.borrowBook(book);
            JOptionPane.showMessageDialog(null, "Book checked out successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, the book is not available.");
        }
    }
    public void returnBook(User user, Book book) {
        book.setAvailability(true);
        user.returnBook(book);
        JOptionPane.showMessageDialog(null, "Book returned successfully!");
    }
    public ArrayList<Book> searchBooksByTitle(String title) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }
    public ArrayList<Book> searchBooksByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }
   public ArrayList<Book> searchBooksByID(int bookID) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookID()==bookID) {
                result.add(book);
            }
        }
        return result;
    }
    public ArrayList<Book> getBooks() {
        return books;
    }
    public ArrayList<User> getUsers() {
        return users;
    }

    
}

