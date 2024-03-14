/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.librarymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class LibraryManagementSystem {
    private Library library;

    public LibraryManagementSystem() {
        this.library = new Library();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryManagementSystem system = new LibraryManagementSystem();
            system.loadLibraryFromFile(); // Load library data from file
            system.createGUI();
        });
    }

    private void createGUI() {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new FlowLayout());

        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });
        JButton removeUserButton = new JButton("Remove User");
        removeUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeUser();
            }
        });
         JButton removeBookButton = new JButton("Remove Book");
        removeBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeBook();
            }
        });
        JButton displayUsersButton = new JButton("Display Users");
        displayUsersButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayUsers();
            }
        });
        JButton displayBooksButton = new JButton("Display Books");
        displayBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayBooks();
            }
        });

        JButton checkOutButton = new JButton("Check Out Book");
        checkOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkOutBook();
            }
        });

        JButton returnButton = new JButton("Return Book");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });

        JButton searchButton = new JButton("Search Books");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBooks();
            }
        });

        JButton saveButton = new JButton("Save Library to File");
        saveButton.addActionListener((ActionEvent e) -> {
            saveLibraryToFile();
        });

        frame.add(addBookButton);
        frame.add(removeBookButton);
        frame.add(addUserButton);
        frame.add(removeUserButton);
        frame.add(displayBooksButton);
        frame.add(displayUsersButton);
        frame.add(checkOutButton);
        frame.add(returnButton);
        frame.add(searchButton);
        frame.add(saveButton);
        frame.setVisible(true);
    }

    private void addBook() {
        try{
        String bookIDString = JOptionPane.showInputDialog("Enter Book ID:");
        if (bookIDString == null || bookIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Book ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int bookID = Integer.parseInt(bookIDString);
         for (Book existingBook : library.getBooks()) {
        if (existingBook.getBookID() == bookID) {
            JOptionPane.showMessageDialog(null, "Book with this ID already exists. Please choose a different ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }


        String title = JOptionPane.showInputDialog("Enter Title:");
        if (title == null || title.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Title.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String author = JOptionPane.showInputDialog("Enter Author:");
        if (author== null || author.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Author.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String genre = JOptionPane.showInputDialog("Enter Genre:");
        if (genre == null || genre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Genre.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Book newBook = new Book(bookID, title, author, genre);
        library.addBook(newBook);
        JOptionPane.showMessageDialog(null, "Book added successfully!");}
     catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Please enter a valid numeric Book ID.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

        
    }

    private void addUser() {
        try{
       String userIDString = JOptionPane.showInputDialog("Enter User ID:");
        if (userIDString == null || userIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid User ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userID = Integer.parseInt(userIDString);
          for (User user : library.getUsers()) {
            if (user.getUserID() == userID) {
                JOptionPane.showMessageDialog(null, "User with this ID already exists. Please choose a different ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        String name = JOptionPane.showInputDialog("Enter Name:");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
         String contactInfo= contactInfo();
         if(contactInfo==null){
             return;
         }
       
        User newUser = new User(userID, name, contactInfo);
        library.addUser(newUser);
        JOptionPane.showMessageDialog(null, "User added successfully!");}
        catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Please enter a valid numeric User ID.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
       
    }
private void removeUser() {
    try {
        String userIDString = JOptionPane.showInputDialog("Enter User ID:");
        if (userIDString == null || userIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid User ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        if (!userIDString.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric User ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userID = Integer.parseInt(userIDString);
        boolean userFound = false;

        for (User user : library.getUsers()) {
            if (user.getUserID() == userID) {
                userFound = true;
                if (user.getBorrowedBooks() == null || user.getBorrowedBooks().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "User has borrowed book(s)");
                   
                    break; 
                } else {
                    library.removeUser(user);
                    JOptionPane.showMessageDialog(null, "User removed successfully");
                    break; 
                    
                }
            }
        }

        if (!userFound) {
            JOptionPane.showMessageDialog(null, "No user found with this user ID");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Please enter a valid numeric User ID.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

       
    
    private void removeBook(){
        
            String bookIDString = JOptionPane.showInputDialog("Enter Book ID");
            if(bookIDString==null ||bookIDString.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please Enter a valid Book ID.","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            int bookID=Integer.parseInt(bookIDString);
            boolean bookFound=false;
            for(Book book:library.getBooks()){
                if(book.getBookID()==bookID){
                    bookFound= true;
                    library.removeBook(book);
                 JOptionPane.showMessageDialog(null, "Book removed Successfully");
                }
            }
            if(!bookFound){
        JOptionPane.showMessageDialog(null, "No Book found with this book ID");
        }
       
        }
    
    private void displayBooks() {
        ArrayList<Book> books = library.getBooks();
        StringBuilder bookList = new StringBuilder("Books in the Library:\n");
        for (Book book : books) {
            bookList.append(book.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, bookList.toString());
       
    }
    private void displayUsers() {
        ArrayList<User> users = library.getUsers();
        StringBuilder userList = new StringBuilder("Users in the Library:\n");
        for (User user : users) {
            userList.append(user.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, userList.toString());
        
    }

    private void checkOutBook() {
        try{
         String userIDString = JOptionPane.showInputDialog("Enter User ID:");
        if (userIDString == null || userIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid User ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userID = Integer.parseInt(userIDString);
       String bookIDString = JOptionPane.showInputDialog("Enter Book ID:");
        if (bookIDString == null || bookIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Book ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int bookID = Integer.parseInt(bookIDString);

        User user = findUser(userID);
        Book book = findBook(bookID);

        if (user != null && book != null) {
            library.checkOutBook(user, book);
        } else {
            JOptionPane.showMessageDialog(null, "User or Book not found!");
        }
        }
         catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Please enter a valid numeric Book ID.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    private void returnBook() {
           try{
            String userIDString = JOptionPane.showInputDialog("Enter User ID:");
        if (userIDString == null || userIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Book ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userID = Integer.parseInt(userIDString);
        String bookIDString = JOptionPane.showInputDialog("Enter Book ID:");
        if (bookIDString == null || bookIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Book ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int bookID = Integer.parseInt(bookIDString);

        User user = findUser(userID);
        Book book = findBook(bookID);

        if (user != null && book != null) {
            library.returnBook(user, book);
        } else {
            JOptionPane.showMessageDialog(null, "User or Book not found!");
        }
           }
       catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Please enter a valid numeric Book ID.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
       
    }

     private void searchBooks() {
       try{
          int searchType=JOptionPane.showOptionDialog(null, "Select Search type: ", "Types", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Title","Author","Book ID"}, null);
       
            switch(searchType){
                case 0: 
                String title = JOptionPane.showInputDialog("Enter Title:");
                ArrayList<Book> titleresult = library.searchBooksByTitle(title);
                displaySearchResult(titleresult);
                break;
                case 1:
                String author = JOptionPane.showInputDialog("Enter Author:");
                ArrayList<Book> authorresult = library.searchBooksByAuthor(author);
                displaySearchResult(authorresult);
                break;
                case 2:
                String BookIDstring = JOptionPane.showInputDialog("Enter BookID:");
                if (BookIDstring== null || BookIDstring.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Book ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int bookID = Integer.parseInt(BookIDstring);
        ArrayList<Book> bookIDresult = library.searchBooksByID(bookID);
                displaySearchResult(bookIDresult);
             
            default:
                JOptionPane.showMessageDialog(null, "Invalid search type. Please enter 'Title' or 'Author'.");
            
        }
            }
         catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Please enter a valid numeric Book ID.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
       
     }
    
    private void displaySearchResult(ArrayList<Book> result) {
        StringBuilder searchResult = new StringBuilder("Search Result:\n");
        if (result.isEmpty()) {
            searchResult.append("No matching books found.");
        } else {
            for (Book book : result) {
                searchResult.append(book.toString()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, searchResult.toString());
    }

    private User findUser(int userID) {
        for (User user : library.getUsers()){
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    private Book findBook(int bookID) {
        for (Book book : library.getBooks()) {
            if (book.getBookID() == bookID) {
                return book;
            }
        }
        return null;
    }
    
    private String contactInfo(){
         String contactInfo = "";
       try{
           int choice=JOptionPane.showOptionDialog(null, "Contact Info", "Contact Info",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,  new String[]{"E-Mail","Phone Numer","Both E-Mail & Phone Number"}, null);
      
        switch(choice){
            case 0:
               contactInfo = JOptionPane.showInputDialog("Enter EMail:");
                if (contactInfo == null ||contactInfo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Email Address.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
                 if (!isValidEmail(contactInfo)) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Email Address.", "Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
             break;
            case 1:
                contactInfo = JOptionPane.showInputDialog("Enter Phone Number:");
                 if (!isValidPhoneNumber(contactInfo)) {
                    JOptionPane.showMessageDialog(null, "Phone Number must begin with \"03\" and must contain 11 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                    
                 }
                 
                break;
            case 2:
                  String email = JOptionPane.showInputDialog("Enter EMail:");
                if (email == null ||email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Email Address.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
                 if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Email Address.", "Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
                 String phoneNumber = JOptionPane.showInputDialog("Enter Phone Number:");
                 if (!isValidPhoneNumber(phoneNumber)) {
                    JOptionPane.showMessageDialog(null, "Phone Number must begin with \"03\" and must contain 11 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                    
                 }
               
                 contactInfo = email + " (Email); " + phoneNumber + " (Phone Number)";
                break;
             default:
                JOptionPane.showMessageDialog(null, "Invalid search type. Please enter 'Title' or 'Author'.");
        }
       return contactInfo;
        }
       catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Please enter a valid numeric Book ID.", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }
      
    }
    private boolean isValidEmail(String email) {
    String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}
    private boolean isValidPhoneNumber(String phoneNumber) {
    return phoneNumber != null && phoneNumber.matches("^03\\d{9}$");
}
    
    private void saveLibraryToFile() {
        try (PrintWriter writer = new PrintWriter("library_data.txt")) {
            for (Book book : library.getBooks()) {
                writer.println(book.getBookID() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getGenre() + "," + book.isAvailable());
            }

            for (User user : library.getUsers()) {
                writer.println(user.getUserID() + "," + user.getName() + "," + user.getContactInfo() + "," + user.getBorrowedBooks());
            }

            JOptionPane.showMessageDialog(null, "Library data saved to file successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving library data to file.");
        }
    }

     private void loadLibraryFromFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader("library_data.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim(); 
            if (!line.isEmpty()) { 
                String[] parts = line.split(",");
                if (parts.length == 5) { 
                    int bookID = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    String genre = parts[3];
                    boolean availability = Boolean.parseBoolean(parts[4]);
                    Book book = new Book(bookID, title, author, genre);
                    book.setAvailability(availability);
                    library.addBook(book);
                } else if (parts.length <= 5) {
                    int userID = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String contactInfo = parts[2];
                    User user = new User(userID, name, contactInfo);
                    library.addUser(user);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Library data loaded from file successfully!");
    } catch (IOException | NumberFormatException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading library data from file. Creating a new library.");
        }
    }
     
     
}
