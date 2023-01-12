package tests;

import org.junit.*;

public class TestCasesClass {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Class");
    }

    @Before
    public void before() {
        System.out.println("Before Test Case");
    }

    @Test
    public void isGreaterTest() {
        System.out.println("Test");
        JUnitHelloWorld helloWorld = new JUnitHelloWorld();
        assert ("test".equals(helloWorld.firstJunitTest()));
    }

    @After
    public void after() {
        System.out.println("After Test Case");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After Class");
    }
}
