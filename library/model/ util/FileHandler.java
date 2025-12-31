package library.util;

import java.util.Scanner;

public class InputValidator {
    private static Scanner scanner = new Scanner(System.in);

    public static String getValidISBN(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty() && input.length() >= 10) {
                return input;
            }
            System.out.println("✗ Please enter a valid ISBN (at least 10 characters)");
        }
    }

    public static String getValidId(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("✗ Please enter a valid ID");
        }
    }

    public static String getValidInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("✗ Input cannot be empty");
        }
    }

    public static int getValidYear() {
        int year;
        while (true) {
            System.out.print("Enter publication year: ");
            try {
                year = Integer.parseInt(scanner.nextLine().trim());
                if (year > 0 && year <= java.time.Year.now().getValue()) {
                    return year;
                }
                System.out.println("✗ Please enter a valid year");
            } catch (NumberFormatException e) {
                System.out.println("✗ Please enter a valid number");
            }
        }
    }

    public static int getValidChoice(int min, int max) {
        int choice;
        while (true) {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.println("✗ Invalid choice. Please enter between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("✗ Please enter a valid number");
            }
        }
    }

    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
