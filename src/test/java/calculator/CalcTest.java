package calculator;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import java.util.Random;

@Listeners(SetUpTearDown.class)
public class CalcTest {

  @Test(groups = "test-add", dataProvider = "data-add", dataProviderClass = Provider.class, dependsOnGroups = "test-input-format")
  public void testAdd(Number a, Number b, Number ex) {
    assertEquals(Calculator.calc("+", a, b).number, ex.number, "add is failed");
  }

  @Test(groups = "test-sub", dataProvider = "data-sub", dataProviderClass = Provider.class, dependsOnGroups = "test-input-format")
  public void testSub(Number a, Number b, Number ex) {
    assertEquals(Calculator.calc("-", a, b).number, ex.number, "sub is failed");
  }

  @Test(groups = "test-mult", dataProvider = "data-mult", dataProviderClass = Provider.class, dependsOnGroups = "test-input-format")
  public void testMult(Number a, Number b, Number ex) {
    assertEquals(Calculator.calc("*", a, b).number, ex.number, "mult is failed");
  }

  @Test(groups = "test-div", dataProvider = "data-div", dataProviderClass = Provider.class, dependsOnGroups = {"test-input-format", "test-random", "test-fuzzing", "test-zero"})
  public void testDiv(Number a, Number b, Number ex) {
    assertEquals(Calculator.calc("/", a, b).number, ex.number, "div is failed");
  }

  @Test(groups = {"test-div", "test-zero"}, dataProvider = "data-div-by-zero", dataProviderClass = Provider.class, expectedExceptions = ArithmeticException.class, dependsOnGroups = {"test-input-format", "test-random", "test-fuzzing"})
  public void testDivByZero(Number a, Number b, Number ex) {
    assertEquals(Calculator.calc("/", a, b).number, ex.number, "div by zero is failed");
  }

  @Test(groups = {"test-div", "test-random"}, dependsOnGroups = "test-input-format")
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

  @Test(groups = {"test-div", "test-fuzzing"}, dependsOnGroups = {"test-input-format", "test-random"})
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
        num = Integer.parseInt(str);
        Calculator.calc("/", NumberFactory.getNumber(String.valueOf(num)), NumberFactory.getNumber(String.valueOf(num)));
      }
      flag = false;
    } catch (Exception e) {
      flag = true;
    }

    assertTrue(flag, "random test is failed");
  }

  @Test(groups = "test-input-format", dataProvider = "data-input-format-numbers", dataProviderClass = Provider.class)
  public void testPositiveInputFormatNumbers(String in, String ex) {
    String[] elemsIn = in.split("\\s");
    String[] elemsEx = ex.split("\\s");
    boolean flag;

    if (elemsIn.length == elemsEx.length && elemsIn[1].equals(elemsEx[1])) {
      if (NumberFactory.getNumber(elemsIn[0]).type.equals(NumberFactory.getNumber(elemsEx[0]).type) &&
              NumberFactory.getNumber(elemsIn[2]).type.equals(NumberFactory.getNumber(elemsEx[0]).type)) {
        flag = true;
      } else {
        flag = false;
      }
    } else {
      flag = false;
    }

    assertTrue(flag, "input format test is failed");
  }

  @Test(groups = "test-input-format", dataProvider = "data-input-format-symbols", dataProviderClass = Provider.class, expectedExceptions = RuntimeException.class)
  public void testNegativeInputFormatSymbols(String in, String ex) {
    String[] elemsIn = in.split("\\s");
    String[] elemsEx = ex.split("\\s");
    boolean flag;

    if (elemsIn.length == elemsEx.length && !elemsIn[1].equals(elemsEx[1])) {
      if (!NumberFactory.getNumber(elemsIn[0]).equals(NumberFactory.getNumber(elemsEx[0]).type) &&
              !NumberFactory.getNumber(elemsIn[2]).type.equals(NumberFactory.getNumber(elemsEx[0]).type)) {
        flag = true;
      } else {
        flag = false;
      }
    } else {
      flag = true;
    }

    assertTrue(flag, "input format test is failed");
  }
}