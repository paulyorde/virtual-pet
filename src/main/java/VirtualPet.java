package main.java;

/**
 * Virtual Pet class
 *
 * @param int ticker
 * threaded timer that checks against VirtualPet state
 *
 */

import java.util.*;

public class VirtualPet {
    private static final int MAX_POINTS_ALLOWED = 10;
    private int hungerPoints;
    private int sleepyPoints;
    private int thirstPoints;
    private int boredPoints;
    private int ignoredPoints;
    private int ticker;
    private int endGamePoints;

    Random pointGenerator = new Random();

    Timer timer = new Timer();
    TimerTask task = getTimerTask();

    public VirtualPet() {
        hungerPoints  = generateRandomNumber();
        sleepyPoints  = generateRandomNumber();
        thirstPoints  = generateRandomNumber();
        boredPoints   = generateRandomNumber();
        ignoredPoints = generateRandomNumber();
        tick();
    }

    public int getSleepyPoints() {
        return sleepyPoints;
    }

    public Integer getHungerPoints() {
        return hungerPoints;
    }

    public Integer getThirstPoints() {
        return thirstPoints;
    }

    public int getIgnoredPoints() {
        return ignoredPoints;
    }

    public int getEndGamePoints() {
        return endGamePoints;
    }

    public int getBoredPoints() {
        return boredPoints;
    }

    public void rubEars() {
        checkElapsedTimeToGamePoints();
        sleepyPoints--;
        hungerPoints += generateRandomNumber();
        thirstPoints += generateRandomNumber();
    }

    public void feed() {
        checkElapsedTimeToGamePoints();
        hungerPoints--;
        thirstPoints++;
        sleepyPoints -= generateRandomNumber();
    }

    public void water() {
        checkElapsedTimeToGamePoints();
        hungerPoints++;
        thirstPoints--;
        sleepyPoints += generateRandomNumber();
    }

    public void playWith() {
        checkElapsedTimeToGamePoints();
        hungerPoints += generateRandomNumber();
        thirstPoints++;
        sleepyPoints += generateRandomNumber();
    }

    public void ignore() {
        checkElapsedTimeToGamePoints();
        thirstPoints += generateRandomNumber();
        sleepyPoints += generateRandomNumber();
        ignoredPoints += generateRandomNumber();
    }

    public int generateRandomNumber() {
        return pointGenerator.nextInt(MAX_POINTS_ALLOWED);
    }

    private void checkElapsedTimeToGamePoints() {
        if(isTimerUp()) {
            endGamePoints = MAX_POINTS_ALLOWED;
        }
    }

    private boolean isTimerUp() {
        return (ticker > generateRandomNumber()) && (sleepyPoints == MAX_POINTS_ALLOWED);
    }

    public void tick() {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    private TimerTask getTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                ticker++;
            }
        };
    }
}
