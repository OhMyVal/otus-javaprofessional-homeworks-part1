package ru.otus.ohmyval.java.professional.homeworks.hw4;

//@Disabled (description = "Класс TestSuite отключен")
public class TestSuite {
    @BeforeSuite
    public static void init() {
        System.out.println("init");
    }

    @Disabled(description = "Метод test1 отключен")
    @Test(priority = 3)
    public static void test1() {
        System.out.println(3);
    }

    @Test
    public static void test2() {
        System.out.println(5);
    }

    @Test
    public static void test3() {
        System.out.println(5);
    }

    @Test(priority = 4)
    @Disabled(description = "Метод test4 отключен")
    public static void test4() {
        System.out.println(4);
    }

    @Test(priority = 10)
    public static void test5() {
        System.out.println(10);
    }

    public static void notTest() {
        System.out.println("notTest");
    }

    @AfterSuite
    @Disabled(description = "Метод endpoint отключен")
    public static void endpoint() {
        System.out.println("endpoint");
    }
}
