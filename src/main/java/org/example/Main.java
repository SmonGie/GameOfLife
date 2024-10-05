package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        double a = 20.00;
        double b = 80.00;
        double finalScore = (a + b)*100;

        double moduloScore = finalScore % 40;
        boolean isIt = (moduloScore == 0.00) ? true : false;

        System.out.println("isIt: " + isIt);

        if(!isIt)
        {
            System.out.println("got some remainder");
        }

    }
}
