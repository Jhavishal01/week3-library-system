package library;

import library.model.Book;
import library.model.Member;
import library.service.Library;
import library.util.InputValidator;

public class Main {
    private static Library library;

    public static void main(String[] args) {
        library = new Library();
        displayWelcome();
        mainMenu();
    }

    private static void displayWelcome() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(" LIBRARY MANAGEMENT SYSTEM");
        System.out.println("=".repeat(60) + "\n");
    }

    private static void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("MAIN MENU");
            System.out.println("-".repeat(40));
            System.out.println("1. Add New Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Books");
            System.out.println("4. Remove Book");
            System.out.println("5. Register Member");
            System.out.println("6. View All Members");
            System.out.println("7. Borrow Book");
            System.out.println("8. Return Book");
            System.out.println("9. View Statistics");
            System.out.println("0. Exit");
            System.out.println("-".repeat(40));

            int choice = InputValidator.getValidChoice(0, 9);

            switch (choice) {
                case 1 -> addBook();
                case 2 -> library.displayAllBooks();
                case 3 -> searchBooks();
                case 4 -> removeBook();
                case 5 -> registerMember();
                case 6 -> library.displayAllMembers();
                case 7 -> borrowBook();
                case 8 -> returnBook();
                case 9 -> library.displayStatistics();
                case 0 -> {
                    System.out.println("\nThank you for using Library Management System!");
                    InputValidator.closeScanner();
                    running = false;
                }
            }
        }
    }

    private static void addBook() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("ADD NEW BOOK");
        System.out.println("=".repeat(40));
        String isbn = InputValidator.getValidISBN("Enter ISBN: ");
        String title = InputValidator.getValidInput("Enter title: ");
        String author = InputValidator.getValidInput("Enter author: ");
        int year = InputValidator.getValidYear();

        library.addBook(new Book(isbn, title, author, year));
    }

    private static void searchBooks() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("SEARCH BOOKS");
        System.out.println("=".repeat(40));
        String keyword = InputValidator.getValidInput("Enter search keyword (title/author): ");
        var results = library.searchBooks(keyword);

        if (results.isEmpty()) {
            System.out.println("✗ No books found matching: " + keyword);
        } else {
            System.out.println("\nFound " + results.size() + " book(s):");
            results.forEach(book -> System.out.println("- " + book));
        }
    }

    private static void removeBook() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("REMOVE BOOK");
        System.out.println("=".repeat(40));
        String isbn = InputValidator.getValidISBN("Enter ISBN: ");
        library.removeBook(isbn);
    }

    private static void registerMember() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("REGISTER NEW MEMBER");
        System.out.println("=".repeat(40));
        String id = InputValidator.getValidId("Enter Member ID: ");
        String name = InputValidator.getValidInput("Enter name: ");
        String email = InputValidator.getValidInput("Enter email: ");

        library.registerMember(new Member(id, name, email));
    }

    private static void borrowBook() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("BORROW BOOK");
        System.out.println("=".repeat(40));
        String isbn = InputValidator.getValidISBN("Enter Book ISBN: ");
        String memberId = InputValidator.getValidId("Enter Member ID: ");

        library.borrowBook(isbn, memberId);
    }

    private static void returnBook() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("RETURN BOOK");
        System.out.println("=".repeat(40));
        String isbn = InputValidator.getValidISBN("Enter Book ISBN: ");
        String memberId = InputValidator.getValidId("Enter Member ID: ");

        library.returnBook(isbn, memberId);
    }
}
