package ui;

import model.ActionList;
import model.WeightList;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Calorie Counter application
public class CalorieCounter {
    private static final String WEIGHT_LIST_JSON = "./data/weight_list.json";
    private static final String ACTION_LIST_JSON = "./data/action_list.json";
    private static final String MAXIMUM_CAL_JSON = "./data/maximum_cal.json";
    private ActionList actionList;
    private WeightList weightList;
    private Scanner input;
    private WeightListJsonWriter weightListJsonWriter;
    private ActionListJsonWriter actionListJsonWriter;
    private MaximumCalJsonWriter maximumCalJsonWriter;
    private WeightListJsonReader weightListJsonReader;
    private ActionListJsonReader actionListJsonReader;
    private MaximumCalJsonReader maximumCalJsonReader;

    // run the Calorie Counter application
    public CalorieCounter() {
        runCalorieCounter();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCalorieCounter() {
        boolean keepGoing = true;

        init();

        while (keepGoing) {
            displayMenu();
            String command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("eat")) {
            addFood();
        } else if (command.equals("do")) {
            addExercise();
        } else if (command.equals("add")) {
            addWeight();
        } else if (command.equals("cal")) {
            printActionList();
        } else if (command.equals("wei")) {
            printWeightList();
        } else if (command.equals("set")) {
            setMaximumCal();
        } else if (command.equals("1")) {
            saveWeightList();
        } else if (command.equals("2")) {
            saveActionList();
        } else if (command.equals("3")) {
            loadWeightList();
        } else if (command.equals("4")) {
            loadActionList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes actionList and weightList
    private void init() {
        actionList = new ActionList();
        weightList = new WeightList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        weightListJsonWriter = new WeightListJsonWriter(WEIGHT_LIST_JSON);
        actionListJsonWriter = new ActionListJsonWriter(ACTION_LIST_JSON);
        maximumCalJsonWriter = new MaximumCalJsonWriter(MAXIMUM_CAL_JSON);
        weightListJsonReader = new WeightListJsonReader(WEIGHT_LIST_JSON);
        actionListJsonReader = new ActionListJsonReader(ACTION_LIST_JSON);
        maximumCalJsonReader = new MaximumCalJsonReader(MAXIMUM_CAL_JSON);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\teat      -> Add food");
        System.out.println("\tdo       -> Add an exercise");
        System.out.println("\tadd      -> Add a weight");
        System.out.println("\tcal      -> see your calorie tracker");
        System.out.println("\twei      -> see your weight loss tracker");
        System.out.println("\tset      -> set a daily maximum calorie intake");
        System.out.println("\t1        -> save the your weight record to file");
        System.out.println("\t2        -> save the your calorie record to file");
        System.out.println("\t3        -> load your weight record");
        System.out.println("\t4        -> load your calorie record");
        System.out.println("\tq        -> quit");
    }

    // MODIFIES: actionList
    // EFFECTS: add an eat action to the actionList
    private void addFood() {
        System.out.println("What is the food name?");
        input.nextLine();
        String foodName = input.nextLine();
        System.out.println("How many cals have you ate?");
        int amountCalEat = input.nextInt();
        actionList.addAction("eat", foodName, amountCalEat);
        System.out.println("Successfully add the food to your calorie tracker!");
    }

    // MODIFIES: actionList
    // EFFECTS: add an exercise action to the actionList
    private void addExercise() {
        System.out.println("What is the exercise name?");
        input.nextLine();
        String exerciseName = input.nextLine();
        System.out.println("How many cals have you exercised?");
        int amountCalExercise = input.nextInt();
        actionList.addAction("exercise", exerciseName, amountCalExercise);
        System.out.println("Successfully add the exercise to your calorie tracker!");
    }

    // MODIFIES: weightList
    // EFFECTS: add a weight to the weightList
    private void addWeight() {
        System.out.println("What is your weight now? Please enter an integer");
        int weight = input.nextInt();
        weightList.addWeight(weight);
        System.out.println("Successfully add the weight to your weight loss tracker!");
    }


    // EFFECTS: set a maximum calorie intake
    private void setMaximumCal() {
        System.out.println("Your current maximum calorie intake is " + actionList.getMaximumCal() + " Cals");
        System.out.println("Set a new one !");
        int amount = input.nextInt();
        actionList.setMaximumCal(amount);
        System.out.println("Success!\nYour current maximum calorie intake is "
                + actionList.getMaximumCal() + " Cals");
    }


    // EFFECTS: print actionList
    private void printActionList() {
        String a = actionList.getString();
        if (actionList.getDeficit() <= 0) {
            System.out.println("Watch out! Your calorie deficit is " + actionList.getDeficit()
                    + " Cals! Do some exercises!");
        } else {
            System.out.println("Your calorie deficit is " + actionList.getDeficit() + " Cals !");
        }
    }

    // EFFECTS: print weightList
    private void printWeightList() {
        String b = weightList.getString();
        System.out.print(b);
        if (weightList.countWeightList() <= 1) {
            System.out.println("Add more weight to see the difference ! You can do it !");
        } else {
            int total = weightList.getTotalChange();
            int recent = weightList.getRecentChange();
            if (total > 0) {
                System.out.println("Congrats! You have lost a total of " + total + " kg !");
            } else if (total == 0) {
                System.out.println("Watch out! Your weight is the same as the first day! You can do it !");
            } else if (total < 0) {
                System.out.println("Come on! You are " + (-total)
                        + " kg heavier than the first day ! You can do it!");
            }
            if (recent > 0) {
                System.out.println("Congrats! You are " + recent + " kg lighter than yesterday !");
            } else if (recent == 0) {
                System.out.println("Watch out! Your weight is the same as yesterday! You can do it!");
            } else if (recent < 0) {
                System.out.println("Come on! You are " + (-recent) + " kg heavier than yesterday ! You can do it!");
            }

        }
    }

    // EFFECTS: saves the weightList to file
    // refer to JsonSerializationDemo
    private void saveWeightList() {
        try {
            weightListJsonWriter.open();
            weightListJsonWriter.write(weightList);
            weightListJsonWriter.close();
            System.out.println("Success! Saved " + "weight record" + " to " + WEIGHT_LIST_JSON);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + WEIGHT_LIST_JSON);
        }
    }

    // EFFECTS: saves the actionList and the maximum daily calorie intake to file
    // refer to JsonSerializationDemo
    private void saveActionList() {
        try {
            actionListJsonWriter.open();
            actionListJsonWriter.write(actionList);
            actionListJsonWriter.close();
            maximumCalJsonWriter.open();
            maximumCalJsonWriter.write(actionList);
            maximumCalJsonWriter.close();
            System.out.println("Success! Saved " + "calorie record" + " to " + ACTION_LIST_JSON);
            System.out.println("Success! Saved " + "maximum intake" + " to " + MAXIMUM_CAL_JSON);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }
    }

    // MODIFIES: this
    // EFFECTS: load the weightList from file to the counter
    // refer to JsonSerializationDemo
    private void loadWeightList() {
        try {
            weightList = weightListJsonReader.read();
            System.out.println("Success! Loaded " + "weight record" + " from " + WEIGHT_LIST_JSON);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + WEIGHT_LIST_JSON);
        }
    }

    // MODIFIES: this
    // EFFECTS: load the actionList from file to the counter
    // refer to JsonSerializationDemo
    private void loadActionList() {
        try {
            actionList = actionListJsonReader.read();
            int max = maximumCalJsonReader.read();
            actionList.setMaximumCal(max);
            System.out.println("Success! Loaded " + "calorie record" + " from " + ACTION_LIST_JSON);
            System.out.println("Success! Loaded " + "maximum intake" + " from " + MAXIMUM_CAL_JSON);
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

}

