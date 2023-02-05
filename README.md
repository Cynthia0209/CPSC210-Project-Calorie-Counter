# Calorie Counter

## A food and weight loss tracker

This is a tool that can record your **daily calorie intake and consumption** 
and help you calculate your daily **calorie deficit**. Also, it can provide a statistic for your daily weight.
It is specially designed for **weight loss people**.
The reason for me to design this calorie counter is that I am losing weight.

## User Stories
- As a user, I want to be able to add an Action(either eat or exercise) to the ActionList.
- As a user, I want to be able to get my daily calorie deficit (maximum total calorie + total subtracted exercise
  calorie - total added food calorie)
- As a user, I want to be able to set a maximum total calorie.
- As a user, I want to be able to add a weight to the weightList. 
- As a user, I want to be able to get the changes of my daily weight.

- As a user, I want to be able to give an option to save my weight list and 
  give an option to save my action list (including my total daily intake) to file.
- As a user, I want to be able to give an option to load my weight list and
  give an option to load my action list (including my total daily intake) to file.

# Instructions for Grader
- You can generate the first required event related to adding actions to an actionList by clicking on 
the "Add food" and "Add an exercise" buttons. After you click on it, you can then type the action name and action 
calorie according to the instruction, and your action will be added to the list. Later you can click on the see your
calorie dairy button to see the list.
- You can generate the second required event related to adding weights to an weightList by clicking on
the "Add a weight" button. After you click on it, you can then type weight according to the instruction, and your
weight will be added to the list. Later you can click on the see your weight records button to see the list.
- You can locate my visual component by seeing a green apple picture on the menu page.
- You can save the state of my application by clicking on save your calorie dairy and save your weight records buttons.
- You can reload the state of my application by clicking on load your calorie dairy and load your weight records.

# Phase 4: Task 2
Thu Nov 24 12:36:05 PST 2022 \
one eat action added to action list

Thu Nov 24 12:36:10 PST 2022 \
one exercise action added to action list

Thu Nov 24 12:36:14 PST 2022 \
one 65 kg weight added to weight list

# Phase 4: Task 3
Action class is a part ActionList class since ActionList is a list of Actions. The ActionList can be empty but can 
not be null. \
WeightList is a list of integers, and it can be empty but can not be null. \
CalorieCounter and CalorieCounterUI class both include one actionList and one weightList. \
The main class depend on one CalorieCounterUI. \
The WeightListJsonReader and WeightListJsonWriter depend on the WeightList Class. \
The ActionListJsonReader and ActionListJsonWriter depend on the ActionList Class. \
The MaximumCalJsonWriter also depend on the ActionList class, but the MaximumCalJsonReader does not. \
CalorieCounter and CalorieCounterUI class both include one json writer and reader for all json classes. 

If I have more time to work on the project, I will have an interface for all the json classes and another interface for
the ActionList and WeightList class. If possible, I will write some exceptions for the methods that include 
requirements.