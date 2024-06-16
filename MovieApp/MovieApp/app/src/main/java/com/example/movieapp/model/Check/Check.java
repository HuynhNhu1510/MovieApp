package com.example.movieapp.model.Check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
    public static boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhone(String phoneNumber) {
        String phoneRegex = "^(\\+84|0)\\d{9,10}$";

        // Tạo pattern từ biểu thức chính quy
        Pattern pattern = Pattern.compile(phoneRegex);

        // Tạo matcher để so khớp với mẫu
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static String checkPasswordStrength(String password) {
        if (password.length() < 8) {
            return "Password must be at least 8 characters long.";
        }

        if (!containsUpperCaseLetter(password)) {
            return "Password must contain at least one uppercase letter.";
        }

        if (!containsLowerCaseLetter(password)) {
            return "Password must contain at least one lowercase letter.";
        }

        if (!containsDigit(password)) {
            return "Password must contain at least one digit.";
        }

        if (!containsSpecialCharacter(password)) {
            return "Password must contain at least one special character.";
        }

        return "Strong password.";
    }

    private static boolean containsUpperCaseLetter(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsLowerCaseLetter(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsDigit(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsSpecialCharacter(String password) {
        Pattern pattern = Pattern.compile("[!@#$%^&*()-+=]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

}
