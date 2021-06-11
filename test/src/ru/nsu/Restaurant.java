package ru.nsu;

class Restaurant  {
    private int ordersCount = 0;

    public void addOrder() {
        ordersCount++;
    }

    public int getOrders() {
        return ordersCount;
    }
}