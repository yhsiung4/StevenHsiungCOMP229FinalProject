package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CoffeeMachine implements Runnable {

    private final String machineName;
    private final BlockingQueue<Order> orderQueue;

    public CoffeeMachine(String machineName) {
        this.machineName = machineName;
        this.orderQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        brewCoffee();
    }

    private void brewCoffee() {
        while (true) {
            try {
                Order order = orderQueue.take();

                int brewingTime;
                switch (order.getCoffeeType()) {
                    case ESPRESSO:
                        brewingTime = 2000;
                        break;
                    case LATTE:
                        brewingTime = 3000;
                        break;
                    case MOCHA:
                        brewingTime = 4000;
                        break;
                    default:
                        brewingTime = 2000;
                        break;
                }

                Thread.sleep(brewingTime);

                System.out.println(Thread.currentThread().getName() +
                        " " + machineName + ": Order id " + order.getId() +
                        " (" + order.getCoffeeType() + ") for " +
                        order.getName() + " is ready for pickup");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
