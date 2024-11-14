package ru.otus.ohmyval.java.professional.homeworks.hw11;

public class ThreadPoolTask {

    private int ctr;

    public int getCtr() {
        return ctr;
    }

    public void setCtr(int ctr) {
        this.ctr = ctr;
    }

    public ThreadPoolTask(int ctr) {
        this.ctr = ctr;
    }

    public String doWork(){
        return "Задача номер " + ctr;
    }
}
