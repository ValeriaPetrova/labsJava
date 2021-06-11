package ru.nsu;

class Chef implements Runnable {
    private final Restaurant restaurant;
    private final Meal meal;

    public Chef(Restaurant restaurant, Meal meal) {
        this.restaurant = restaurant;
        this.meal = meal;
    }

    private void cook() {
        restaurant.addOrder();
        System.out.println("Doing " + restaurant.getOrders());
    }

    @Override
    public void run() {
        while (restaurant.getOrders() != 10) {
            cook();
            synchronized (meal) {
                meal.notify();
                try {
                    meal.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
