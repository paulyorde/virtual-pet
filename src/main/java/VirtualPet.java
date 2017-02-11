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
        hungerPoints = generateRandomPoint();
        sleepyPoints  = generateRandomPoint();
        thirstPoints  =  generateRandomPoint();
        boredPoints   = generateRandomPoint();
        ignoredPoints = generateRandomPoint();
        tick();
    }

    public int getTicker() {
        return ticker;
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
        if(ticker > 3 && sleepyPoints > 9) {
            endGamePoints = 10;
        }
        sleepyPoints--;
        hungerPoints += generateRandomPoint();
        thirstPoints += generateRandomPoint();
    }

    public void feed() {
        if(ticker > 5 && hungerPoints > 9) {
            endGamePoints = 10;
        }
        hungerPoints--;
        thirstPoints++;
        sleepyPoints -= generateRandomPoint();
    }

    public void water() {
        if(ticker > 7 && thirstPoints > 9) {
            endGamePoints = 10;
        }
        hungerPoints++;
        thirstPoints--;
        sleepyPoints += generateRandomPoint();
    }

    public void playWith() {
        if(ticker > 9 && boredPoints > 9) {
            endGamePoints = 10;
        }
        hungerPoints += generateRandomPoint();
        thirstPoints++;
        sleepyPoints += generateRandomPoint();
    }

    public void ignore() {
        if(ticker > 11 && ignoredPoints > 9) {
            endGamePoints = 10;
        }
        thirstPoints += generateRandomPoint();
        sleepyPoints += generateRandomPoint();
        ignoredPoints += generateRandomPoint();
    }

    public int generateRandomPoint() {
        return pointGenerator.nextInt(10);
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
