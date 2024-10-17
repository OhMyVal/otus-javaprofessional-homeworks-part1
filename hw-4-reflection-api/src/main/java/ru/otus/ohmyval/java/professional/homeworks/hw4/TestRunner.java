package ru.otus.ohmyval.java.professional.homeworks.hw4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void run(Class testSuiteClass) throws MyTestException {
        if (testSuiteClass.isAnnotationPresent(Disabled.class)) {
            System.out.println(testSuiteClass.getAnnotation(Disabled.class).toString());
            return;
        }
        Method[] methods = testSuiteClass.getDeclaredMethods();
        List<Method> testSuitMethods = new ArrayList<>();
        for (Method m : methods) {
            if ((m.isAnnotationPresent(Test.class)) && (m.isAnnotationPresent(BeforeSuite.class))) {
                throw new MyTestException("Метод помечен двумя аннотациями: Test и BeforeSuite");
            }
            if ((m.isAnnotationPresent(Test.class)) && (m.isAnnotationPresent(AfterSuite.class))) {
                throw new MyTestException("Метод помечен двумя аннотациями: Test и AfterSuite");
            }
            if ((m.isAnnotationPresent(BeforeSuite.class)) && (m.isAnnotationPresent(AfterSuite.class))) {
                throw new MyTestException("Метод помечен двумя аннотациями: BeforeSuite и AfterSuite");
            }
            if (m.isAnnotationPresent(Disabled.class)) {
                continue;
            }
            if (m.isAnnotationPresent(Test.class)) {
                if ((m.getAnnotation(Test.class).priority() < 1) || (m.getAnnotation(Test.class).priority() > 10)) {
                    throw new MyTestException("Приоритет вышел за границы допуска");
                }
                testSuitMethods.add(m);
            }
        }
        testSuitMethods.sort((m1, m2) -> m2.getAnnotation(Test.class).priority() - m1.getAnnotation(Test.class).priority());
        int countBeforeSuite = 0;
        int countAfterSuite = 0;
        for (Method m : methods) {
            if (m.isAnnotationPresent(Disabled.class)) {
                System.out.println(m.getAnnotation(Disabled.class).description());
                continue;
            }
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                countBeforeSuite++;
                if (countBeforeSuite > 1) {
                    throw new MyTestException("Превышено количество аннотаций BeforeSuite");
                }
                testSuitMethods.add(0, m);
            } else {
                if (m.isAnnotationPresent(AfterSuite.class)) {
                    countAfterSuite++;
                    if (countAfterSuite > 1) {
                        throw new MyTestException("Превышено количество аннотаций AfterSuite");
                    }
                    testSuitMethods.add(m);
                }
            }
        }
        int countFailedTests = 0;
        for (Method testSuitMethod : testSuitMethods) {
            try {
                testSuitMethod.invoke(null);
            } catch (IllegalAccessException e) {
                System.out.println("Тест " + testSuitMethod.getName() + ", приоритет " + testSuitMethod.getAnnotation(Test.class).priority() + ", упал");
                e.printStackTrace();
                throw new MyTestException("Нет доступа");
            } catch (InvocationTargetException e) {
                countFailedTests++;
                System.out.println("Тест " + testSuitMethod.getName() + ", приоритет " + testSuitMethod.getAnnotation(Test.class).priority() + ", упал");
                e.printStackTrace();
            }
        }
        System.out.println("Всего тестов: " + testSuitMethods.size() + "; Прошло успешно: " + (testSuitMethods.size() - countFailedTests) + "; Упало: " + countFailedTests);
    }
}

