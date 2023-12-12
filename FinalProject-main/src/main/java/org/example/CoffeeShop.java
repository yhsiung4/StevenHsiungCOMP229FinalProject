package org.example;

import com.github.javafaker.Faker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoffeeShop {
    public static void main(String[] args) {
        final int numberOfCashiers = 2;
        final int numberOfMachines = 4;
        final int threadCount = 10;

        ExecutorService executorService =
                Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < numberOfMachines; i++) {
            executorService.submit(new CoffeeMachine("Machine-" + i));
        }

        for (int i = 0; i < numberOfCashiers; i++) {
            executorService.submit(new Cashier("Cashier-" + i));
        }
    }
}