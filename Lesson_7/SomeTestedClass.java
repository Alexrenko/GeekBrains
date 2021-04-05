package FirstSemestr.Java3.Lesson_7;

import FirstSemestr.Java3.Lesson_7.Tester.*;

public class SomeTestedClass {

    @BeforeSuite
    public void before() {
        System.out.println("BeforeSuite");
    }

    @AfterSuite
    public void after() {
        System.out.println("AfterSuite");
    }

    @Test (priority = 4)
    public void test1() {
        System.out.println("test1, priority - 4");
    }

    @Test (priority = 3)
    public void test2() {
        System.out.println("test2, priority - 3");
    }

    @Test (priority = 2)
    public void test3() {
        System.out.println("test3, priority - 2");
    }

    @Test (priority = 1)
    public void test4() {
        System.out.println("test4, priority - 1");
    }

    @Test (priority = 0)
    public void test5() {
        System.out.println("test5, priority - 0");
    }
}
