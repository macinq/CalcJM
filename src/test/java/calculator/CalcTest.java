package calculator;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Random;

public class CalcTest {
//  private static final Logger logger = LogManager.getLogger(CalcTest.class);

//  @BeforeMethod
//  public void setUp() {
//    logger.info("Initialization");
//  }
//
//  @AfterMethod
//  public void tearDown() {
//    logger.info("Cleaning");
//  }

  @Test(groups = "test-add", dataProvider = "data-add", dataProviderClass = Provider.class/*, enabled = false*/)
  public void testAdd(Number a, Number b, Number ex) {
    assertEquals(Calculator.calc("+", a, b).number, ex.number, "add is failed");
  }

  @Test(groups = "test-sub", dataProvider = "data-sub", dataProviderClass = Provider.class)
  public void testSub(Number a, Number b, Number ex) {
    assertEquals(Calculator.calc("-", a, b).number, ex.number, "sub is failed");
  }

  @Test(groups = "test-mult", dataProvider = "data-mult", dataProviderClass = Provider.class)
  public void testMult(Number a, Number b, Number ex) {
    assertEquals(Calculator.calc("*", a, b).number, ex.number, "mult is failed");
  }

  @Test(groups = "test-div", dataProvider = "data-div", dataProviderClass = Provider.class, dependsOnMethods = {"testRandomDiv", "testFuzzingDiv", "testDivByZero"})
  public void testDiv(Number a, Number b, Number ex) {
    assertEquals(Calculator.calc("/", a, b).number, ex.number, "div is failed");
  }

  @Test(groups = "test-div", dataProvider = "data-div-by-zero", dataProviderClass = Provider.class, expectedExceptions = ArithmeticException.class)
  public void testDivByZero(Number a, Number b, Number ex) {
    assertEquals(Calculator.calc("/", a, b).number, ex.number, "div by zero is failed");
  }

  @Test(groups = {"test-div", "test-random"})
  public void testRandomDiv() {
    boolean flag;

    try {
      int n = 100000;
      Random rand = new Random();
      for (int i = 0; i < n; i++) {
        Calculator.calc("/", NumberFactory.getNumber(String.valueOf(rand.nextInt(50) - 25)), NumberFactory.getNumber(String.valueOf(rand.nextInt(50) - 25)));
      }
      flag = false;
    } catch (ArithmeticException e) {
      flag = true;
    }

    assertTrue(flag, "random test is failed");
  }

  @Test(groups = {"test-div", "test-random"}/*, expectedExceptions = NumberFormatException.class*/)
  public void testFuzzingDiv() {
    int ch = 2;
    char[] array = new char[ch];
    Random rand = new Random();
    int n = 100000;
    int num;
    boolean flag;

    try {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < ch; j++) {
          num = (rand.nextInt(127) + 1); // см. ASCII, (rand.nextInt(max - min) + 1) + min
          array[j] = (char) num;                // например, (rand.nextInt(9) + 1) + 48 получает символьные представления чисел
        }
        String str = new String(array);
//        System.out.println(str);

        num = Integer.parseInt(str);
        Calculator.calc("/", NumberFactory.getNumber(String.valueOf(num)), NumberFactory.getNumber(String.valueOf(num)));
      }
      flag = false;
    } catch (Exception e) {
      flag = true;
    }

    assertTrue(flag, "random test is failed");
  }
}