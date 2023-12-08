package ru.klimov.mirea;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SingleThreadSolution {
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

    public String getPasswordFromHash(String hashPassword) {
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                for (int k = 0; k < chars.length; k++) {
                    for (int l = 0; l < chars.length; l++) {
                        for (int m = 0; m < chars.length; m++) {
                            String combination = "" + chars[i] + chars[j]
                                    + chars[k] + chars[l] + chars[m];
                            if (checkHashOfLine(combination, hashPassword)) return combination;
                        }
                    }
                }
            }
        }

        return "NO!";
    }

    private boolean checkHashOfLine(String combination, String hashPassword) {
        StringBuilder sb = new StringBuilder();
        byte[] hashInBytes = md.digest(combination.getBytes(StandardCharsets.US_ASCII));
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        String temp = sb.toString();
        if (hashPassword.equals(temp)){
            return true;
        }
        else {
            return false;
        }
    }
}
