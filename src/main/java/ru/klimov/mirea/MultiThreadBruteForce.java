package ru.klimov.mirea;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadBruteForce {
    String symbols;
    String hashPassword;
    int threadCount;
    List<MyThread> threadList;

    public MultiThreadBruteForce(String hashPassword, int threadCount) {
        this.hashPassword = hashPassword;
        this.threadCount = threadCount;
        this.threadList = new ArrayList<>(threadCount);
        symbols = "abcdefghijklmnopqrstuvwxyz";
    }

    public String getPasswordFromHash(){
        createThreads();
        startThreads();
        joinThreads();
        return getThreadResult();
    }

    private String getThreadResult() {
        String result = null;
        for (int i = 0; i < threadCount; i++) {
            result = threadList.get(i).getStringPassword();
            if (result != null) return result;
        }
        return result;
    }

    private void joinThreads() {
        for (int i = 0; i < threadCount; i++) {
            try {
                threadList.get(i).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void startThreads() {
        for (int i = 0; i < threadCount; i++) {
            threadList.get(i).start();
        }
    }

    private void createThreads(){
        // Рассчитываем размер каждого подсписка
        int sizeOfSublist = symbols.length() / threadCount;
        for (int i = 0; i < threadCount; i++) {
            int startIndex = i * sizeOfSublist;
            int endIndex = (i == threadCount -1) ? symbols.length() : (i + 1) * sizeOfSublist;
            threadList.add(new MyThread(hashPassword, startIndex, endIndex));
        }
    }
}

class MyThread extends Thread{
    int startIndex;
    int endIndex;
    String stringPassword;
    String hashPassword;
    String symbols;
    char[] chars;
    MessageDigest md;

    {
        stringPassword = null;
        symbols = "abcdefghijklmnopqrstuvwxyz";
        chars = symbols.toCharArray();
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public MyThread(String hashPassword, int startIndex, int endIndex) {
        super();
        this.hashPassword = hashPassword;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        mainLoop();
    }

    private void mainLoop() {
        for (int i = startIndex; i < endIndex; i++) {
            for (int j = 0; j < chars.length; j++) {
                for (int k = 0; k < chars.length; k++) {
                    for (int l = 0; l < chars.length; l++) {
                        for (int m = 0; m < chars.length; m++) {
                            String combination = "" + chars[i] + chars[j]
                                    + chars[k] + chars[l] + chars[m];
                            if (checkHashOfLine(combination)) {
                                stringPassword = combination;
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean checkHashOfLine(String combination) {
        StringBuffer sb = new StringBuffer();
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

    public String getStringPassword(){
        return stringPassword;
    }

}
