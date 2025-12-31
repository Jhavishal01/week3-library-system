package library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private String email;
    private List<String> borrowedBooks;

    public Member(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<String> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(String isbn) {
        if (!borrowedBooks.contains(isbn)) {
            borrowedBooks.add(isbn);
        }
    }

    public void returnBook(String isbn) {
        borrowedBooks.remove(isbn);
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Email: %s | Books Borrowed: %d",
            id, name, email, borrowedBooks.size());
    }
}

// ========== FileHandler.java ==========
package library.util;

import library.model.Book;
import library.model.Member;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String BOOKS_FILE = "data/books.dat";
    private static final String MEMBERS_FILE = "data/members.dat";

    public FileHandler() {
        createDirectoryIfNotExists();
    }

    private void createDirectoryIfNotExists() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public void saveBooks(List<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public List<Book> loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKS_FILE))) {
            return (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading books: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveMembers(List<Member> members) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMBERS_FILE))) {
            oos.writeObject(members);
        } catch (IOException e) {
            System.out.println("Error saving members: " + e.getMessage());
        }
    }

    public List<Member> loadMembers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MEMBERS_FILE))) {
            return (List<Member>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading members: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
