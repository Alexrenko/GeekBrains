package FirstSemestr.Java3.Lesson_7;

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Tester {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface BeforeSuite {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Test {
        int priority() default 1;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface AfterSuite {
    }

    private static Class<?> testClass;
    private static Method[] methods;
    private static ArrayList<Method> testMethods = new ArrayList<>();

    public static void start(String className) {
        try {
            start(Class.forName(className));
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        }
    }

    public static void start(Class<?> testClass) {
        Tester.testClass = testClass;
        methods = testClass.getDeclaredMethods();

        if (checkDoublesAnnot()) {
            throw new RuntimeException("Аннотации @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре.");
        }

        try {
            startBefore();
            startTests();
            startAfter();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkDoublesAnnot() {
        int countBeforeSuite = 0;
        int countAfterSuite = 0;
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof BeforeSuite) {
                    countBeforeSuite++;
                } else if (annotation instanceof AfterSuite) {
                    countAfterSuite++;
                }
            }
        }
        if (countBeforeSuite > 1 || countAfterSuite > 1) {
            return true;
        }
        return false;
    }

    private static void startBefore() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for(Method method : methods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                method.invoke(testClass.newInstance());
            }
        }
    }

    private static void startAfter() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for(Method method : methods) {
            if (method.getAnnotation(AfterSuite.class) != null) {
                method.invoke(testClass.newInstance());
            }
        }
    }

    private static void startTests() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for(Method method : methods) {
            if (method.getAnnotation(Test.class) != null) {
                testMethods.add(method);
            }
        }
        sortTestMethodsByPriority();
        for (Method method : testMethods) {
            method.invoke(testClass.newInstance());
        }
    }

    private static void sortTestMethodsByPriority() {
        Method buffer;
        for (int i = 0; i < testMethods.size(); i++) {
            int priorityA = testMethods.get(i).getAnnotation(Test.class).priority();
            for (int j = i; j < testMethods.size(); j++) {
                int priorityB = testMethods.get(j).getAnnotation(Test.class).priority();
                if (priorityA > priorityB) {
                    buffer = testMethods.get(i);
                    testMethods.set(i, testMethods.get(j));
                    testMethods.set(j, buffer);
                    priorityA = priorityB;
                }
            }
        }
    }

}
