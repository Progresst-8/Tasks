package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        // C:\\Users\\User\\CS2113\\ip\\data\\data.txt <<< absolute path
        // ./data/data.txt <<  relative path
        // Unix based machines start with /, which indicates root folder
        ArrayList<Task> tasksData = dataManager.loadData();

//        System.out.println("Printing all data ...");
//        printAllData(tasksData);

        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);
        printDeadlinesUsingStream(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        ArrayList<Task> filteredList = filterTasksByString(tasksData, "11");
        System.out.println(filteredList);

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
        System.out.println("print deadlines using iteration");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }
    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        System.out.println("print sorted deadlines using stream");
        tasks.stream()
                .filter( t -> t instanceof Deadline )
                .sorted( (t1, t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()) )
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTasksByString(ArrayList<Task> tasks, String filterByString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter( t -> t.getDescription().contains(filterByString) )
                .collect( toList() );
        return filteredList;
    }
}
