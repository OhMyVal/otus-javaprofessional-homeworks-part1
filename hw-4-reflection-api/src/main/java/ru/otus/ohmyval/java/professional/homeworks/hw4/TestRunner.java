package ru.otus.ohmyval.java.professional.homeworks.hw4;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void run(Class testSuiteClass) {
        Method[] methods = testSuiteClass.getDeclaredMethods();
        List<Method> testSuitMethods = new ArrayList<>();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                testSuitMethods.add(m);
            }
        }
        testSuitMethods.sort((m1, m2) -> m2.getAnnotation(Test.class).priority() - m1.getAnnotation(Test.class).priority());
              System.out.println(testSuitMethods);
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                testSuitMethods.add(0, m);
            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                testSuitMethods.add(m);
            }
        }
        System.out.println(testSuitMethods);
    }
}

