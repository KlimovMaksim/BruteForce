package ru.klimov.mirea;


import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args){
        SingleThreadSolution singleThreadSolution = new SingleThreadSolution();
        System.out.println(
                singleThreadSolution.getPasswordFromHash("74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f")
        );

    }
}


// 1115dd800feaacefdf481f1f9070374a2a81e27880f187396db67958b207cbad = zyzzx
// c6d29c289fab658299db5ab138141c756bbd3c9cc74da5b1b7e8bdbc2d7902bc = aaaao
// 3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b = apple
// 74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f = mmmmm