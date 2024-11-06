package ru.otus.ohmyval.java.professional.homeworks.hw8;

import java.util.*;
import java.util.stream.Collectors;


public class MainApp {
    public static void main(String[] args) {

        TaskApplication taskApp = new TaskApplication();

        List<TaskApplication.MyTask> myTaskList = taskApp.getMyTaskList();

        System.out.println("myTaskList: " + myTaskList);
        System.out.println("myTasksInWork: " + myTasksInWork(taskApp));
        System.out.println("closedTasksQuantity: " + closedTasksQuantity(taskApp));
        System.out.println("ifTaskExist: " + ifTaskExist(taskApp));
        System.out.println("myTaskSortedList: " + myTaskSortedList(taskApp));
        System.out.println("tasksGroupedByStatusAndId: " + tasksGroupedByStatusAndId(taskApp));
        System.out.println("tasksDividedByStatus: " + tasksDividedByStatus(taskApp));
    }

    public static List<TaskApplication.MyTask> myTasksInWork(TaskApplication taskApp) {
        List<TaskApplication.MyTask> myTaskList = taskApp.getMyTaskList();
        return myTaskList.stream()
                .filter(myTask -> myTask.status.equals(Status.INWORK))
                .toList();
    }

    public static Long closedTasksQuantity(TaskApplication taskApp) {
        List<TaskApplication.MyTask> myTaskList = taskApp.getMyTaskList();
        return myTaskList.stream()
                .filter(myTask -> myTask.status.equals(Status.CLOSED))
                .count();
    }

    public static List<Boolean> ifTaskExist(TaskApplication taskApp) {
        List<TaskApplication.MyTask> myTaskList = taskApp.getMyTaskList();
        boolean taskId2Exist = myTaskList.stream()
                .anyMatch(myTask -> myTask.getId() == 2);
        boolean taskId99NotExist = myTaskList.stream()
                .noneMatch(myTask -> myTask.getId() == 99);
        return Arrays.asList(taskId2Exist, taskId99NotExist);
    }

    public static List<TaskApplication.MyTask> myTaskSortedList(TaskApplication taskApp) {
        List<TaskApplication.MyTask> myTaskList = taskApp.getMyTaskList();
        return myTaskList.stream()
                .sorted(Comparator.comparing(myTask -> myTask.status))
                .toList();
    }

    public static Map<Status, Map<String, List<TaskApplication.MyTask>>> tasksGroupedByStatusAndId(TaskApplication taskApp) {
        List<TaskApplication.MyTask> myTaskList = taskApp.getMyTaskList();
        return myTaskList.stream()
                .collect(Collectors.groupingBy(TaskApplication.MyTask::getStatus,
                        Collectors.groupingBy(myTask -> {
                            myTask.setParity();
                            return myTask.getParity();
                        })));
    }

    public static Map<Boolean, List<TaskApplication.MyTask>> tasksDividedByStatus(TaskApplication taskApp) {
        List<TaskApplication.MyTask> myTaskList = taskApp.getMyTaskList();
        return myTaskList.stream()
                .collect(Collectors.partitioningBy(myTask -> myTask.status.equals(Status.CLOSED)));

    }

}
