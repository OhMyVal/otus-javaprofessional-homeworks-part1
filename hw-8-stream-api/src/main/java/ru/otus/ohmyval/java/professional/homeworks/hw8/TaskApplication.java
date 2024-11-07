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
        Status status;

        @Getter
        private String parity;

        public void setParity() {
            if (id % 2 == 0) {
                this.parity = "Even";
            } else {
                this.parity = "Odd";
            }
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

    Stream<MyTask> getMyTaskStream() {
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

    List<MyTask> myTaskList = getMyTaskStream().toList();

    public List<MyTask> myTasksInWork() {
        return myTaskList.stream()
                .filter(myTask -> myTask.status.equals(Status.INWORK))
                .toList();
    }

    public Long closedTasksQuantity() {
        return myTaskList.stream()
                .filter(myTask -> myTask.status.equals(Status.CLOSED))
                .count();
    }

    public List<Boolean> ifTaskExist() {
        boolean taskId2Exist = myTaskList.stream()
                .anyMatch(myTask -> myTask.getId() == 2);
        boolean taskId99NotExist = myTaskList.stream()
                .noneMatch(myTask -> myTask.getId() == 99);
        return Arrays.asList(taskId2Exist, taskId99NotExist);
    }

    public List<TaskApplication.MyTask> myTaskSortedList() {
        return myTaskList.stream()
                .sorted(Comparator.comparing(myTask -> myTask.status))
                .toList();
    }

    public Map<Status, Map<String, List<MyTask>>> tasksGroupedByStatusAndId() {
        return myTaskList.stream()
                .collect(Collectors.groupingBy(TaskApplication.MyTask::getStatus,
                        Collectors.groupingBy(myTask -> {
                            myTask.setParity();
                            return myTask.getParity();
                        })));
    }

    public Map<Boolean, List<TaskApplication.MyTask>> tasksDividedByStatus() {
        return myTaskList.stream()
                .collect(Collectors.partitioningBy(myTask -> myTask.status.equals(Status.CLOSED)));
    }

}
