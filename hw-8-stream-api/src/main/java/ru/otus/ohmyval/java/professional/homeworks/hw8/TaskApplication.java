package ru.otus.ohmyval.java.professional.homeworks.hw8;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
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

    List<MyTask> getMyTaskList() {
        return getMyTaskStream().collect(Collectors.toList());
    }

}
