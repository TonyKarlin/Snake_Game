package utils;


public class Clock {
    private static Clock instance = null;
    private long startTime;
    private long elapsedTime;
    
    

    private Clock() {
    }

    public static Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }
    
    public void update(long now) {
        if (startTime < 0) {
            startTime = now; // updates the start time on the initial frame
        }
        elapsedTime = now - startTime;
    }
    
    public void reset() {
        startTime = -1;
        elapsedTime = 0;
    }
    
    public long getElapsedTimeInMs() {
        return elapsedTime / 1_000_000;
    }

    public long getElapsedTimeInSeconds() {
        return elapsedTime / 1_000_000_000;
    }

    public long getStartTime() {
        return startTime;
    }
}
