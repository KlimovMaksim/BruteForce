package ru.klimov.mirea;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        consoleLoop();
    }

    protected void read(Path path) {
        try {
            byte[] bytes = Files.readAllBytes(path);
            System.out.println("Содержимое файла " + path.getFileName() + ":");
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }

    private static void consoleLoop(){
        System.out.println("Введите количество потоков: ");
        Scanner scanner = new Scanner(System.in);
        String hashPassword = readFromFile();
        int threadCount = scanner.nextInt();
        System.out.println("Хэш-значение: " + hashPassword);
        long before = System.currentTimeMillis();
        MultiThreadBruteForce multiThreadSolution = new MultiThreadBruteForce(hashPassword, threadCount);
        System.out.println("Пароль: " + multiThreadSolution.getPasswordFromHash());
        long after = System.currentTimeMillis();

        System.out.println("Время выполнения программы: " + (after - before) / 1000 + " с");
    }

    private static String readFromFile() {
        String result = null;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("src/main/resources/hash.txt"));
            result = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
        return result;
    }


}


// 1115dd800feaacefdf481f1f9070374a2a81e27880f187396db67958b207cbad = zyzzx 145s
// c6d29c289fab658299db5ab138141c756bbd3c9cc74da5b1b7e8bdbc2d7902bc = aaaao
// 3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b = apple 4s
// 74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f = mmmmm 70s