package ru.otus.ohmyval.java.professional.homeworks.hw4;

public class TestSuite {
    @BeforeSuite
    public static void init() {
        System.out.println("init");
    }

    @Test (priority = 3)
    public static void test1() {
        System.out.println(1);
    }
    @Test
    public static void test2() {
        System.out.println(2);
    }
    @Test
    public static void test3() {
        System.out.println(3);
    }
    @Test (priority = 8)
    public static void test4() {
        System.out.println(4);
    }
    @Test (priority = 10)
    public static void test5() {
        System.out.println(5);
    }

    public static void notTest() {
        System.out.println("notTest");
    }

    @AfterSuite
    public static void endpoint() {
        System.out.println("endpoint");
    }
}
