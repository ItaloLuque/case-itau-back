package org.example.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PasswordValidator {

    public boolean isValid(String password) {
        if (password == null || password.length() < 9) {
            System.out.println("Password is either null or too short.");
            return false;
        }

        boolean hasDigit = false;
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasSpecial = false;
        Set<Character> seenChars = new HashSet<>();
        String specialCharacters = "!@#$%^&*()-+";

        for (char ch : password.toCharArray()) {
            System.out.println("Processing character: " + ch);
            if (ch == ' ') {
                System.out.println("Password contains space.");
                return false;
            }
            if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (Character.isLowerCase(ch)) {
                hasLower = true;
            } else if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (specialCharacters.indexOf(ch) >= 0) {
                hasSpecial = true;
            }

            if (!seenChars.add(ch)) {
                System.out.println("Password contains repeated character: " + ch);
                return false;
            }
        }
        return hasDigit && hasLower && hasUpper && hasSpecial;
    }

}
