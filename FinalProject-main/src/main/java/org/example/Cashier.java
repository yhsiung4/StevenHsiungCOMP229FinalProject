package org.example;

import com.github.javafaker.Faker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Cashier implements Runnable {

    private final String cashierName;
    private final BlockingQueue<Order> orderQueue;

    public Cashier(String cashierName) {
        this.cashierName = cashierName;
        this.orderQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        placeOrder();
    }

    private void placeOrder() {
        Faker faker = new Faker();
        CoffeeType[] coffeeTypes = CoffeeType.values();

        while (true) {
            try {
                Thread.sleep(2000); // Simulating time between orders
                String customerName = faker.name().firstName();
                CoffeeType coffeeType = coffeeTypes[(int) (Math.random() * coffeeTypes.length)];
                Order order = new Order(customerName, coffeeType);

                orderQueue.put(order);

                System.out.println(Thread.currentThread().getName() +
                        " " + cashierName + ": Order id " + order.getId() +
                        " (" + order.getCoffeeType() + ") is accepted for " +
                        order.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
