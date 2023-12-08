package ru.klimov.mirea;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

abstract public class AbstractSolution {
    String symbols;
    char[] chars;
    MessageDigest md;

    {
        symbols = "abcdefghijklmnopqrstuvwxyz";
        chars = symbols.toCharArray();
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract String getPasswordFromHash(String hashPassword);
}
