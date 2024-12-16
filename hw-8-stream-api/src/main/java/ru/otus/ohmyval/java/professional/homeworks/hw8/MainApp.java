package ru.otus.ohmyval.java.professional.homeworks.hw8;


public class MainApp {
    public static void main(String[] args) {

        TaskApplication taskApp = new TaskApplication();

        System.out.println("taskList: " + taskApp.taskList);
        System.out.println("tasksInWork: " + taskApp.tasksInWork());
        System.out.println("closedTasksQuantity: " + taskApp.closedTasksQuantity());
        System.out.println("ifTaskExist: " + taskApp.ifTaskExist());
        System.out.println("taskSortedList: " + taskApp.taskSortedList());
        System.out.println("tasksGroupedByStatusAndId: " + taskApp.tasksGroupedByStatusAndId());
        System.out.println("tasksDividedByStatus: " + taskApp.tasksDividedByStatus());
    }
}
