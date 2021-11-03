package ru.geekbrains.guess_the_number;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.lang.String;

public class Main {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        boolean userColorBoolean = true;

        int bank = 1000;
        while (bank > 0) {
            int userNumber = -1;
            System.out.println("Жду ставок, господа! Число от 0 до 36, а если боитесь, ставьте на красное или черное!");

            while (true) {
                String userInput = scanner.nextLine();
                if (Objects.equals(userInput, "красное")) {
                    userColorBoolean = true;
                    break;
                } else if (Objects.equals(userInput, "Красное")) {
                    userColorBoolean = true;
                    break;
                } else if (Objects.equals(userInput, "черное")) {
                    userColorBoolean = false;
                    break;
                } else if (Objects.equals(userInput, "Черное")) {
                    userColorBoolean = false;
                    break;
                } else if (Objects.equals(userInput, "quit")) {
                    System.out.println("Вы решили соскочить? Грац! Игра окончена.");
                    scanner.close();
                } else {
                    try {
                        userNumber = Integer.parseInt(userInput);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Вы печатаете какую-то дичь. Введите целое число от 0 до 36 либо Красное или Черное");
                    }
                }
            }

            int userStake;
            do {
                System.out.println("Сколько ставим? У вас осталось " + bank + ". Для выхода из игры наберите quit");
                String inputStake = scanner.nextLine();
                if (Objects.equals(inputStake, "quit")) {
                    System.out.println("Вы решили соскочить? Грац! Игра окончена.");
                    scanner.close();
                } else {
                    try {
                        userStake = Integer.parseInt(inputStake);
                        if (userStake > bank) {
                            System.out.println("У вас нет столько денег. Введите соразмерную банку сумму!");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы печатаете какую-то дичь.");
                    }
                }
            } while(true);

            Random resultColor = new Random();
            boolean color = resultColor.nextBoolean();
            String colorName = "красное";
            if (!color) {
                colorName = "черное";
            }
            int resultNumber = (int) (Math.random() * 36);
            if (userNumber == resultNumber) {
                bank = bank + (userStake * 35);
                System.out.println("Ставка - " + userStake + ". Надо же, редкая удача! Число угадано, и это " + resultNumber + ", " + colorName + ". Вы получаете " + (userStake * 35) + "в банк. В вашем банке " + bank);
            } else if (userNumber == -1) {
                if (userColorBoolean == color) {
                    bank = bank + (userStake * 2);
                    System.out.println("Ставка - " + userStake + ". Надо же, редкая удача! Цвет угадан! Выпало " + resultNumber + ", " + colorName + ". Вы получаете " + (userStake * 2) + " в банк. В вашем банке " + bank);
                } else if (userColorBoolean != color){
                    bank = bank - userStake;
                    System.out.println("Ставка - " + userStake + ". Неудача! Выпало " + resultNumber + ", " + colorName + ", вы не угадали цвет. В вашем банке " + bank);
                }
            } else {
                bank = bank - userStake;
                System.out.println("Ставка - " + userStake + ". Неудача! Выпало " + resultNumber + ", " + colorName + ", вы не угадали число. В вашем банке " + bank);
            }
        }
        System.out.println("Деньги испарились! Игра окончена.");
        scanner.close();
    }
}
