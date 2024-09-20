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
//        System.out.println("Printing all data ...");
//        printAllData(tasksData);
//        printDataWithStreams(tasksData);

        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);
        printDeadlinesUsingStream(tasksData);
//        System.out.println("Printing deadlines ...");
//        printDeadlines(tasksData);
//        printDeadlinesWithStreams(tasksData);

        System.out.println("Total deadlines using iteration: " + countDeadlines(tasksData));
        System.out.println("total deadlines using streams: " + countDeadlinesWithStreams(tasksData));

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
    private static int countDeadlinesWithStreams(ArrayList<Task> tasks) {
        int count = (int) tasks.stream()
                .filter( (t) -> t instanceof Deadline ) // lambda functions
                .count(); //
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("print all data with iteration _____________________");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }
    public static void printDataWithStreams(ArrayList<Task> tasks) {
        System.out.println("print all data with streams _______________-");
        tasks.stream()                         // create a stream
                .forEach(System.out::println); // terminal operator
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("print deadline with iteration __________________");
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
    public static void printDeadlinesWithStreams(ArrayList<Task> tasks) {
        System.out.println("printing deadline with streams _________________");
        tasks.stream()                                  //
                .filter( (t) -> t instanceof Deadline ) // lambda function
                .forEach(System.out::println);          //
    }



    public static ArrayList<Task> filterTasksByString(ArrayList<Task> tasks, String filterByString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter( t -> t.getDescription().contains(filterByString) )
                .collect( toList() );
        return filteredList;
    }
}
