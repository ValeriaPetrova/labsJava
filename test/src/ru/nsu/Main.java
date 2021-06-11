package ru.nsu;

public class Main {

    public static void main(String[] args) {
        Meal meal = new Meal();
        Restaurant restaurant = new Restaurant();
        Thread waiter = new Thread(new Waiter(restaurant, meal));
        Thread chief = new Thread(new Chef(restaurant, meal));
        waiter.start();
        chief.start();
    }

}