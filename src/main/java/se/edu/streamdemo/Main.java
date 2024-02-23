package se.edu.streamdemo;

import se.edu.streamdemo.data.DataManager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager");
        DataManager dataManager = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

//        System.out.println("Printing all data ...");
//        printAllData(tasksData);
//
//        System.out.println("Printing deadlines ...");
//        printDeadlines(tasksData);
//
//        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        //printAllDataUsingStreams(tasksData);
        printAllDeadlineUsingStreams(tasksData);
        System.out.println("Total number of deadlines: " + countDeadlinesUsingStreams(tasksData));
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printAllDataUsingStreams(ArrayList<Task> tasksData) {
        System.out.print("Printing data using streams ..." );
        tasksData
                .forEach(System.out::println);
    }

    public static void printAllDeadlineUsingStreams(ArrayList<Task> tasksData) {
        System.out.print("Printing data using streams ..." );
        tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .forEach(System.out::println);
    }

    private static int countDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        return (int) tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .count();
    }
}
