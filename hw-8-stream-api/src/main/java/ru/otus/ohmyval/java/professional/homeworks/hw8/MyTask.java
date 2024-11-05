package ru.otus.ohmyval.java.professional.homeworks.hw8;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class MyTask {

    @Getter
    private int id;

    @Getter
    private String title;

    @Getter
    @Setter
    private Status status;
}
