import org.testng.Assert;
import org.testng.annotations.*;

public class TestNGDemo {

    @Test()
    public void test3() {
        Assert.fail("Hwa keda!");
        System.out.println("Test 3");
    }

    @Test(dependsOnMethods = {"test3"})
    public void test1() {
        System.out.println("Test 1");
    }

    @Test()
    public void test2() {
        System.out.println("Test 2");
    }


    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method to run before each test case");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method to run after each test case");
    }

    @BeforeClass
    public void beforClass() {
        System.out.println("Before Class to run before any test case in the class (first thing in the class to be executed)");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class to run after any test case in the class (last thing in the class to be executed)");
    }
}
