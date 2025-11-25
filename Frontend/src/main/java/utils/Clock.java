package utils;


public class Clock {
    private static Clock instance;
    private final double time;

    private Clock() {
        this.time = 0;
    }

    public static Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    public double getTime() {
        return time;
    }
}
