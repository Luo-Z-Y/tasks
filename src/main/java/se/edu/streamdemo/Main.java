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
        ArrayList<Task> filteredList = filterTasksByString(tasksData, "11");

//        System.out.println("Printing all data ...");
//        printAllData(tasksData);
//
//        System.out.println("Printing deadlines ...");
//        printDeadlines(tasksData);
//
//        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        //printAllDataUsingStreams(tasksData);
        taskComparator(tasksData);
        System.out.println("Filtered List");
        printAllData(filteredList);
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

    public static void taskComparator(ArrayList<Task> tasksData) {
        tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .sorted((t1, t2) -> t1.getDescription().compareTo(t2.getDescription()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTasksByString(ArrayList<Task> tasksData, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasksData.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .toList();
        return filteredList;
    }
}
