package ui;

import model.ActionList;
import model.Event;
import model.EventLog;
import model.WeightList;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Graphical user interface for calorie counter
public class CalorieCounterUI extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private final JPanel menu;
    private JButton addFood;
    private JButton addExercise;
    private JButton addWeight;
    private JButton getActionList;
    private JButton getWeightList;
    private JButton setMaxCal;
    private JButton quit;
    private JButton saveActionList;
    private JButton loadActionList;
    private JButton saveWeightList;
    private JButton loadWeightList;
    private ActionList actionList;
    private WeightList weightList;
    private static final String WEIGHT_LIST_JSON = "./data/weight_list.json";
    private static final String ACTION_LIST_JSON = "./data/action_list.json";
    private static final String MAXIMUM_CAL_JSON = "./data/maximum_cal.json";
    private WeightListJsonWriter weightListJsonWriter;
    private ActionListJsonWriter actionListJsonWriter;
    private MaximumCalJsonWriter maximumCalJsonWriter;
    private WeightListJsonReader weightListJsonReader;
    private ActionListJsonReader actionListJsonReader;
    private MaximumCalJsonReader maximumCalJsonReader;


    // EFFECTS: sets up window in which the calorie counter will be played
    public CalorieCounterUI() {
        super("Calorie Counter");
        menu = new JPanel();
        actionList = new ActionList();
        weightList = new WeightList();
        setupJson();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setUpButton();
        addMenu();
        this.add(menu);
        ImageIcon imageIcon = new ImageIcon("./data/picture1.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        JLabel label = new JLabel(imageIcon);
        JLabel welcome = new JLabel("Welcome to your calorie counter!  Let's get started! ");
        menu.add(welcome);
        menu.add(label);
        setVisible(true);

    }

    // EFFECTS: initializes buttons and add functions to them
    private void setUpButton() {
        addFood = new JButton("Add food");
        setAddFood();
        addExercise = new JButton("Add an exercise");
        setAddExercise();
        addWeight = new JButton("Add a Weight");
        setAddWeight();
        getActionList = new JButton("see your calorie dairy");
        setGetActionList();
        getWeightList = new JButton("see your weight records");
        setGetWeightList();
        setMaxCal = new JButton("set a daily maximum calorie intake");
        setNewMaxCal();
        saveActionList = new JButton("save your calorie dairy");
        setSaveActionList();
        loadActionList = new JButton("load your calorie dairy");
        setLoadActionList();
        saveWeightList = new JButton("save your weight records");
        setSaveWeightList();
        loadWeightList = new JButton("load your weight records");
        setLoadWeightList();
        quit = new JButton("quit");
        quit();
    }


    // EFFECTS: initialize json writers and readers
    private void setupJson() {
        weightListJsonWriter = new WeightListJsonWriter(WEIGHT_LIST_JSON);
        actionListJsonWriter = new ActionListJsonWriter(ACTION_LIST_JSON);
        maximumCalJsonWriter = new MaximumCalJsonWriter(MAXIMUM_CAL_JSON);
        weightListJsonReader = new WeightListJsonReader(WEIGHT_LIST_JSON);
        actionListJsonReader = new ActionListJsonReader(ACTION_LIST_JSON);
        maximumCalJsonReader = new MaximumCalJsonReader(MAXIMUM_CAL_JSON);
    }

    // EFFECTS: add the buttons to the menu
    private void addMenu() {
        menu.add(addFood);
        menu.add(addExercise);
        menu.add(addWeight);
        menu.add(getActionList);
        menu.add(getWeightList);
        menu.add(saveActionList);
        menu.add(loadActionList);
        menu.add(saveWeightList);
        menu.add(loadWeightList);
        menu.add(setMaxCal);
        menu.add(quit);
    }

    // EFFECTS: set up add food button, add an eat action to the action list
    private void setAddFood() {
        addFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("What is the food name?");
                String a = JOptionPane.showInputDialog("How many cals have you ate?");
                if (a.equals("")) {
                    a = "0";
                }
                int cal = Integer.parseInt(a);
                actionList.addAction("eat", name, cal);
                JOptionPane.showMessageDialog(CalorieCounterUI.this,
                        "Successfully add the food to your calorie tracker!");
            }
        });
    }

    // EFFECTS: set up add exercise button, add an exercise action to the action list
    private void setAddExercise() {
        addExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("What is the exercise name?");
                String a = JOptionPane.showInputDialog("How many cals have you burned?");
                if (a.equals("")) {
                    a = "0";
                }
                int cal = Integer.parseInt(a);
                actionList.addAction("exercise", name, cal);
                JOptionPane.showMessageDialog(CalorieCounterUI.this,
                        "Successfully add the exercise to your calorie tracker!");
            }
        });
    }

    // EFFECTS: set up add weight button, add a weight to the weight list
    private void setAddWeight() {
        addWeight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = (JOptionPane.showInputDialog("What is your weight now (in kg)?"
                        + " Please enter an integer"));
                if (a.equals("")) {
                    a = "0";
                }
                int weight = Integer.parseInt(a);
                weightList.addWeight(weight);
                JOptionPane.showMessageDialog(CalorieCounterUI.this,
                        "Successfully add the weight to your weight loss tracker!");
            }
        });
    }

    // EFFECTS: set up get action list button, show the action list
    private void setGetActionList() {
        getActionList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel(new BorderLayout(10, 10));
                List<String> a = actionList.getListString();
                JList<String> list = new JList(a.toArray());
                String b = getDeficit();
                JLabel deficit = new JLabel(b);
                panel.add(list, BorderLayout.PAGE_START);
                panel.add(deficit, BorderLayout.CENTER);
                JOptionPane.showMessageDialog(null, panel, "Action List",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    // EFFECTS: set up get weight list button, show the weight list
    private void setGetWeightList() {
        getWeightList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel(new BorderLayout(10, 10));
                List<String> a = weightList.getListString();
                JList<String> list = new JList(a.toArray());
                JLabel label = new JLabel(printWeightList());
                panel.add(list, BorderLayout.CENTER);
                panel.add(label, BorderLayout.PAGE_END);
                JOptionPane.showMessageDialog(null, panel, "Weight List",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    // EFFECTS: set up set new cal button, set up a new maximum calorie
    private void setNewMaxCal() {
        setMaxCal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg1 = "Your current maximum calorie intake is " + actionList.getMaximumCal()
                        + " Cals, Set a new one !";
                String a = JOptionPane.showInputDialog(msg1);
                if (a.equals("")) {
                    a = "0";
                }
                actionList.setMaximumCal(Integer.parseInt(a));
                String msg3 = "Success! Your current maximum calorie intake is "
                        + actionList.getMaximumCal() + " Cals";
                JOptionPane.showMessageDialog(CalorieCounterUI.this, msg3);
            }
        });
    }

    // EFFECTS: set up save action list button, save the action list
    private void setSaveActionList() {
        saveActionList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actionListJsonWriter.open();
                    actionListJsonWriter.write(actionList);
                    actionListJsonWriter.close();
                    maximumCalJsonWriter.open();
                    maximumCalJsonWriter.write(actionList);
                    maximumCalJsonWriter.close();
                    String a = "Success! Saved " + "calorie record" + " to " + ACTION_LIST_JSON + "\n"
                            + "Success! Saved " + "maximum intake" + " to " + MAXIMUM_CAL_JSON;
                    JOptionPane.showMessageDialog(CalorieCounterUI.this, a);
                } catch (FileNotFoundException fne) {
                    JOptionPane.showMessageDialog(CalorieCounterUI.this,
                            "Unable to write to file");
                }
            }
        });
    }

    // EFFECTS: set up save weight list button, save the weight list
    private void setSaveWeightList() {
        saveWeightList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    weightListJsonWriter.open();
                    weightListJsonWriter.write(weightList);
                    weightListJsonWriter.close();
                    JOptionPane.showMessageDialog(CalorieCounterUI.this,
                            "Success! Saved " + "weight record" + " to " + WEIGHT_LIST_JSON);
                } catch (FileNotFoundException fne) {
                    JOptionPane.showMessageDialog(CalorieCounterUI.this,
                            "Unable to write to file: " + WEIGHT_LIST_JSON);
                }
            }
        });
    }

    // EFFECTS: set up load action list button, load the action list
    private void setLoadActionList() {
        loadActionList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actionList = actionListJsonReader.read();
                    int max = maximumCalJsonReader.read();
                    actionList.setMaximumCal(max);
                    JOptionPane.showMessageDialog(CalorieCounterUI.this,
                            "Success! Loaded " + "calorie record" + " from " + ACTION_LIST_JSON
                                    + "\n" + "Success! Loaded " + "maximum intake" + " from " + MAXIMUM_CAL_JSON);
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(CalorieCounterUI.this,
                            "Unable to read from file");
                }
            }
        });
    }

    // EFFECTS: set up load weight list button, load the weight list
    private void setLoadWeightList() {
        loadWeightList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    weightList = weightListJsonReader.read();
                    JOptionPane.showMessageDialog(CalorieCounterUI.this,
                            "Success! Loaded " + "weight record" + " from " + WEIGHT_LIST_JSON);
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(CalorieCounterUI.this,
                            "Unable to read from file: " + WEIGHT_LIST_JSON);
                }
            }
        });
    }

    // EFFECTS: print the calorie deficit into string
    private String getDeficit() {
        int b = actionList.getDeficit();
        if (b <= 0) {
            return ("Watch out! Your calorie deficit is " + b
                    + " Cals! Do some exercises!");
        } else {
            return ("Your calorie deficit is " + b + " Cals !");
        }
    }

    // EFFECTS: print weightList
    private String printWeightList() {
        String str = "";
        if (weightList.countWeightList() <= 1) {
            str = "Add more weight to see the difference ! You can do it !";
        } else {
            int total = weightList.getTotalChange();
            int recent = weightList.getRecentChange();
            if (total > 0) {
                str = str + "Congrats! You have lost a total of " + total + " kg !";
            } else if (total == 0) {
                str = str + "Watch out! Your weight is the same as the first day! You can do it !";
            } else if (total < 0) {
                str = str + "Come on! You are " + (-total)
                        + " kg heavier than the first day ! You can do it!";
            }
            if (recent > 0) {
                str = str + "\n" + "Congrats! You are " + recent + " kg lighter than yesterday !";
            } else if (recent == 0) {
                str = str + "\n" + "Watch out! Your weight is the same as yesterday! You can do it!";
            } else if (recent < 0) {
                str = str + "\n" + "Come on! You are " + (-recent) + " kg heavier than yesterday ! You can do it!";
            }
        }
        return str;
    }

    private void quit() {
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
    }

    private void printLog(EventLog el) {
        for (Event next : EventLog.getInstance()) {
            System.out.print(next.toString() + "\n\n");
        }
    }

}
