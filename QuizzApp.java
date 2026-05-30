// package com.java.project;

import java.util.Scanner;

public class QuizzGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] questions = {
            "Which language is used for Android?", "What is the size of 'int' in Java?",
            "Which keyword is used for inheritance?", "What is the default value of a boolean?",
            "Which company developed Java?", "What is a 'string' in Java?",
            "Which loop is guaranteed to execute at least once?", "Which operator is used to compare two values?",
            "What is the base class of all Java classes?", "Which package contains the Scanner class?"
        };

        String[][] options = {
            {"1. Swift", "2. Java", "3. Python", "4. C++"},
            {"1. 2 bytes", "2. 4 bytes", "3. 8 bytes", "4. 1 byte"},
            {"1. extends", "2. implements", "3. inherits", "4. super"},
            {"1. true", "2. false", "3. null", "4. 0"},
            {"1. Microsoft", "2. Sun Microsystems", "3. Google", "4. Apple"},
            {"1. Primitive", "2. Object", "3. Character", "4. Integer"},
            {"1. for", "2. while", "3. do-while", "4. foreach"},
            {"1. =", "2. ==", "3. equals", "4. is"},
            {"1. String", "2. System", "3. Object", "4. Main"},
            {"1. java.io", "2. java.lang", "3. java.util", "4. java.net"}
        };

        int[] answers = {2, 2, 1, 2, 2, 2, 3, 2, 3, 3};
        int[] rewards = {1000, 2000, 3000, 5000, 10000, 20000, 40000, 80000, 160000, 320000};

        // Lifeline Tracking: [0]=50-50, [1]=Audience Poll, [2]=Skip
        boolean[] usedLifelines = {false, false, false};
        int currentWinnings = 0;

        System.out.println("--- Welcome to KBC Quiz ---");

        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQUESTION " + (i + 1) + " for Rs." + rewards[i]);
            System.out.println(questions[i]);
            for (String opt : options[i]) System.out.println(opt);

            System.out.println("\nOPTIONS: [1-4] Answer | [5] Lifelines | [6] Quit");
            System.out.print("Your choice: ");
            int choice = sc.nextInt();

            if (choice == 6) {
                System.out.println("You quit with Rs." + currentWinnings);
                return;
            }

            // Lifeline Menu Logic
            if (choice == 5) {
                if (i >= 8) {
                    System.out.println("!! No lifelines allowed for Q9 and Q10 !!");
                } else {
                    System.out.println("Available Lifelines:");
                    if (!usedLifelines[0]) System.out.println("7. 50-50");
                    if (!usedLifelines[1]) System.out.println("8. Audience Poll");
                    if (!usedLifelines[2]) System.out.println("9. Skip Question");
                    
                    int lChoice = sc.nextInt();
                    
                    if (lChoice == 7 && !usedLifelines[0]) {
                        usedLifelines[0] = true;
                        System.out.println("50-50: One wrong option is 4 and the correct is " + answers[i]);
                    } else if (lChoice == 8 && !usedLifelines[1]) {
                        usedLifelines[1] = true;
                        System.out.println("Audience Poll: Option " + answers[i] + " got 75% votes.");
                    } else if (lChoice == 9 && !usedLifelines[2]) {
                        usedLifelines[2] = true;
                        System.out.println("Skipping Question...");
                        continue; // Moves to the next question in the 'for' loop
                    } else {
                        System.out.println("Lifeline already used or invalid!");
                    }
                }
                System.out.print("Now, enter your final answer [1-4]: ");
                choice = sc.nextInt();
            }

            // Win/Loss Logic
            if (choice == answers[i]) {
                currentWinnings = rewards[i];
                System.out.println("Correct! Current Balance: Rs." + currentWinnings);
            } else {
                System.out.println("Wrong! Correct answer was " + answers[i]);
                if (i < 5) currentWinnings = 0;
                else if (i < 7) currentWinnings = rewards[4];
                else currentWinnings = rewards[6];
                System.out.println("Game Over! You take home: Rs." + currentWinnings);
                return;
            }
        }
        System.out.println("JACKPOT! You won Rs." + currentWinnings);
    }
}