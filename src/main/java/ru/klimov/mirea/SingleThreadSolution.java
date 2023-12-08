package ru.klimov.mirea;

import java.nio.charset.StandardCharsets;

public class SingleThreadSolution extends AbstractSolution{
    @Override
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



    /*public String getPasswordFromHash(String hashPassword) throws NoSuchAlgorithmException {
        String result = null;
        List<String> stringCombinations = getStringCombination();
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        for (String line : stringCombinations) {
            StringBuilder sb = new StringBuilder();
            byte[]hashInBytes = md.digest(line.getBytes(StandardCharsets.US_ASCII));
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            result = sb.toString();
            if (result.equals(hashPassword)) return result;
        }

        return result;
    }*/

    /*private List<String> getStringCombination() {
        List<String> result = new ArrayList<String>();
        char[] chars = symbols.toCharArray();
        StringBuilder sb;

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                for (int k = 0; k < chars.length; k++) {
                    for (int l = 0; l < chars.length; l++) {
                        for (int m = 0; m < chars.length; m++) {
                            sb = new StringBuilder();
                            sb.append(chars[i]);
                            sb.append(chars[j]);
                            sb.append(chars[k]);
                            sb.append(chars[l]);
                            sb.append(chars[m]);
                            result.add(sb.toString());
                        }
                    }
                }
            }
        }
        return result;
    }*/


}
