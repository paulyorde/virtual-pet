package main.java;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VirtualPetApp {
    private VirtualPet pet = new VirtualPet();
    private Map<String, String> menu = new LinkedHashMap<>();
    private Map<String, Integer> petDisplay = new HashMap<>();
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        VirtualPetApp game = new VirtualPetApp();
        game.run();
    }

    private void run() {
        setUpGame();
    }

    private void setUpGame() {
        String choice;
        do {
            displayUI();
            choice = promptAction();
            respondTo(choice);
            if (checkEndGamePoints()) {
                displayEndGameMessage();
                break;
            }
        } while(!choice.equals("quit"));
    }

    private void displayUI() {
        displayGameHeading();
        buildMenu();
        buildPetDisplay();
        displayPet();
    }

    private String promptAction() {
        System.out.println();
        System.out.println("Options are: ");
        for(Map.Entry<String, String> option : menu.entrySet()) {
            System.out.printf("%s - %s %n",
                    option.getKey(),
                    option.getValue());
        }
        System.out.println();
        System.out.print("What do want to do?: ");
        String choice = input.next();
        return choice.trim().toLowerCase();
    }

    private void respondTo(String choice) {
        switch (choice) {
            case "play":
                indicateProcessing();
                pet.playWith();
                break;
            case "feed":
                indicateProcessing();
                pet.feed();
                break;
            case "rub":
                indicateProcessing();
                pet.rubEars();
                break;
            case "water":
                indicateProcessing();
                pet.water();
                break;
            case "ignore":
                indicateProcessing();
                pet.ignore();
                break;
            case "quit":
                // quit method
                System.out.print("Goodbye");
                break;
            default:
                System.out.print("foo bar");
        }
    }

    private boolean checkEndGamePoints() {
        return pet.getEndGamePoints() > 9;
    }

    private void displayEndGameMessage() {
        System.out.print("Sorry Charlie; You mishandled your pet. Goodbye.");
        pet.timer.cancel();
    }

    private void displayGameHeading() {
        System.out.print("Your virtual pet is feeling all of this: ");
        System.out.println();
    }

    private void displayPet() {
        for(Map.Entry<String, Integer> display : petDisplay.entrySet()) {
            System.out.println(display.getKey() + " " + display.getValue());
        }
    }

    private void buildPetDisplay() {
        petDisplay.put("sleepy", pet.getSleepyPoints());
        petDisplay.put("hungry", pet.getHungerPoints());
        petDisplay.put("thirsty", pet.getThirstPoints());
        petDisplay.put("bored", pet.getBoredPoints());
        petDisplay.put("ignored", pet.getIgnoredPoints());
    }

    private void buildMenu() {
        menu.put("play", "play with virtual pet");
        menu.put("feed", "feed virtual pet");
        menu.put("rub", "rub virtual pets ears");
        menu.put("water", "give virtual pet some water");
        menu.put("ignore", "ignoring virtual pet per your request");
        menu.put("quit", "exit the game");
    }

    private void indicateProcessing() {
        System.out.print("processing...");
    }
}

