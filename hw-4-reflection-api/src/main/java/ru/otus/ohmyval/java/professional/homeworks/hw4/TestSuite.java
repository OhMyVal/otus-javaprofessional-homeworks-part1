package ru.otus.ohmyval.java.professional.homeworks.hw4;

public class TestSuite {
    @BeforeSuite
    public static void init() {
        System.out.println("init");
    }

    @Test
    public static void test1() {
        System.out.println(1);
    }

    @AfterSuite
    public static void endpoint() {
        System.out.println("endpoint");
    }
}
