package ru.nsu;

class Meal  {
    private boolean isReady = false;

    public void setReady(boolean flag) {
        isReady = flag;
    }
    public boolean isReady() {
        return isReady;
    }
}