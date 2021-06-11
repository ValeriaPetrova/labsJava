package ru.nsu;

class Waiter implements Runnable {
    private final Restaurant restaurant;
    private final Meal meal;

    public Waiter(Restaurant restaurant, Meal meal) {
        this.restaurant = restaurant;
        this.meal = meal;
    }

    private void serve() {
        System.out.println("Serve " + restaurant.getOrders());
    }

    @Override
    public void run() {
        while (restaurant.getOrders() != 10) {
            synchronized (meal) {
                try {
                    meal.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            serve();
            synchronized (meal)
            {
                meal.notify();
            }
        }
    }
}