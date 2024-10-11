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
        System.out.println("Priority: " + 3 + "; " + "test1");
    }

    @Test
    public static void test2() {
        System.out.println("Priority: " + 5 + "; " + "test2");
    }

    @Test
    public static void test3() {
        System.out.println("Priority: " + 5 + "; " + "test3");
    }

    @Test(priority = 2)
//    @Disabled(description = "Метод test4 отключен")
    public static void test4() {
        System.out.println("Priority: " + 2 + "; " + "test4");
    }

    @Test(priority = 10)
    public static void test5() {
        System.out.println("Priority: " + 10 + "; " + "test5");
    }

    public static void notTest() {
        System.out.println("notTest");
    }

    @AfterSuite
//    @Disabled(description = "Метод endpoint отключен")
    public static void endpoint() {
        System.out.println("endpoint");
    }

    @Test(priority = 4)
    public static void test6() {
        int a = 0;
        int b = 10;
        int result = b / a;
        System.out.println(result + "; " + "Priority: " + 4 + "test6");
    }

    @Test(priority = 9)
    public static void test7() {
        int a = 0;
        int b = 33;
        int result = b / a;
        System.out.println(result + "; " + "Priority: " + 9 + "test7");
    }

}
