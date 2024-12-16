package ru.otus.ohmyval.java.professional.homeworks.hw8;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TaskApplication {

    @Builder
    static class MyTask {

        @Getter
        private int id;

        @Getter
        private String title;

        @Getter
        @Setter
        private Status status;

        public String getParity() {
            return id % 2 == 0 ? "Even" : "Odd";
//            if (id % 2 == 0) return "Even";
//            return "Odd";
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    private Stream<MyTask> getTaskStream() {
        return Stream.of(
                MyTask.builder()
                        .id(1)
                        .title("First")
                        .status(Status.OPENED)
                        .build(),
                MyTask.builder()
                        .id(2)
                        .title("Second")
                        .status(Status.CLOSED)
                        .build(),
                MyTask.builder()
                        .id(3)
                        .title("Third")
                        .status(Status.OPENED)
                        .build(),
                MyTask.builder()
                        .id(4)
                        .title("Fourth")
                        .status(Status.INWORK)
                        .build(),
                MyTask.builder()
                        .id(5)
                        .title("Fifth")
                        .status(Status.CLOSED)
                        .build(),
                MyTask.builder()
                        .id(6)
                        .title("Sixth")
                        .status(Status.INWORK)
                        .build()

        );
    }

    List<MyTask> taskList = getTaskStream().toList();

    public List<MyTask> tasksInWork() {
        return taskList.stream()
                .filter(myTask -> myTask.status.equals(Status.INWORK))
                .toList();
    }

    public Long closedTasksQuantity() {
        return taskList.stream()
                .filter(myTask -> myTask.status.equals(Status.CLOSED))
                .count();
    }

    public List<Boolean> ifTaskExist() {
        boolean taskId2Exist = taskList.stream()
                .anyMatch(myTask -> myTask.getId() == 2);
        boolean taskId99NotExist = taskList.stream()
                .noneMatch(myTask -> myTask.getId() == 99);
        return Arrays.asList(taskId2Exist, taskId99NotExist);
    }

    public List<MyTask> taskSortedList() {
        List<Status> statusOrder = List.of(Status.OPENED, Status.INWORK, Status.CLOSED);
        Comparator<MyTask> statusComparator = Comparator
                .comparingInt(task -> statusOrder.indexOf(task.getStatus()));
        return taskList.stream()
                .sorted(statusComparator)
                .toList();
    }

    public List<MyTask> taskSortedList2() {
        return taskList.stream()
                .sorted(Comparator.comparing(MyTask::getStatus)) // тут сортируется по тому, как статусы объявлены в Enum
//                .sorted(Comparator.comparingInt(myTask -> myTask.status.ordinal()))  // здесь тоже, но с использованием метода ordinal() в Enum, результат такой же
                .toList();
    }

    public Map<Status, Map<String, List<MyTask>>> tasksGroupedByStatusAndId() {
        return taskList.stream()
                .collect(Collectors.groupingBy(MyTask::getStatus,
                        Collectors.groupingBy(MyTask::getParity)));
    }


    public Map<Boolean, List<MyTask>> tasksDividedByStatus() {
        return taskList.stream()
                .collect(Collectors.partitioningBy(myTask -> myTask.status.equals(Status.CLOSED)));
    }

}
