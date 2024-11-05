package ru.otus.ohmyval.java.professional.homeworks.hw8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) {

        List<MyTask> myTaskList = getMyTaskStream().collect(Collectors.toList());

    }

    private static Stream<MyTask> getMyTaskStream() {
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

}
