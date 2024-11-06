package ru.otus.ohmyval.java.professional.homeworks.hw8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) {

        TaskApplication taskApp = new TaskApplication();

        List<TaskApplication.MyTask> myTaskList = taskApp.getMyTaskList();
//        System.out.println(myTaskList);
//
//
//        List<TaskApplication.MyTask> myTasksInWork = myTaskList.stream()
//                .filter(myTask -> myTask.status.equals(Status.INWORK))
//                .toList();
        System.out.println(myTasksInWork(taskApp));
        System.out.println(closedTasksQuantity(taskApp));
        System.out.println(ifTaskExist(taskApp));


//        Long closedTasksQuantity = myTaskList.stream()
//                .filter(myTask -> myTask.status.equals(Status.CLOSED))
//                .count();
//        System.out.println(closedTasksQuantity);


//        boolean taskId2Exist = myTaskList.stream()
//                .anyMatch(myTask -> myTask.getId() == 2);
//        boolean taskId99NotExist = myTaskList.stream()
//                .noneMatch(myTask -> myTask.getId() == 99);
//        List<Boolean> result = Arrays.asList(taskId2Exist,taskId99NotExist);
//        System.out.println(result);


        List<TaskApplication.MyTask> myTaskSortedList = myTaskList.stream()
                .sorted(Comparator.comparing(myTask -> myTask.status))
                .toList();
        System.out.println(myTaskSortedList);


        Map<Status, Map<String, List<TaskApplication.MyTask>>> tasksGroupedByStatusAndId = myTaskList.stream()
                .collect(Collectors.groupingBy(TaskApplication.MyTask::getStatus,
                        Collectors.groupingBy(myTask -> {
                            myTask.setParity();
                            return myTask.getParity();
                        })));
        System.out.println(tasksGroupedByStatusAndId);


        Map<Boolean, List<TaskApplication.MyTask>> tasksDividedByStatus = myTaskList.stream()
                .collect(Collectors.partitioningBy(myTask -> myTask.status.equals(Status.CLOSED)));
        System.out.println(tasksDividedByStatus);

    }

    public static List<TaskApplication.MyTask> myTasksInWork(TaskApplication taskApp){
        List<TaskApplication.MyTask> myTaskList = taskApp.getMyTaskList();
        return myTaskList.stream()
                .filter(myTask -> myTask.status.equals(Status.INWORK))
                .toList();

    }
    public static Long closedTasksQuantity(TaskApplication taskApp){
        List<TaskApplication.MyTask> myTaskList = taskApp.getMyTaskList();
        return myTaskList.stream()
                .filter(myTask -> myTask.status.equals(Status.CLOSED))
                .count();
    }

    public static List<Boolean> ifTaskExist(TaskApplication taskApp){
        List<TaskApplication.MyTask> myTaskList = taskApp.getMyTaskList();
        boolean taskId2Exist = myTaskList.stream()
                .anyMatch(myTask -> myTask.getId() == 2);
        boolean taskId99NotExist = myTaskList.stream()
                .noneMatch(myTask -> myTask.getId() == 99);
        List<Boolean> result = Arrays.asList(taskId2Exist,taskId99NotExist);
        return result;
    }




}
