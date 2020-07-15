package calculator;

import org.testng.annotations.*;

public class Provider {

  @DataProvider(name = "data-add")
  public Object[][] dataAdd() {
    return new Object[][]{
            {NumberFactory.getNumber("2"), NumberFactory.getNumber("2"), NumberFactory.getNumber("4")},
            {NumberFactory.getNumber("0"), NumberFactory.getNumber("0"), NumberFactory.getNumber("0")},
            {NumberFactory.getNumber(String.valueOf(Integer.MAX_VALUE)), NumberFactory.getNumber(String.valueOf(Integer.MAX_VALUE)), NumberFactory.getNumber("-2")} //2147483647
    };
  }

  @DataProvider(name = "data-sub")
  public Object[][] dataSub() {
    return new Object[][]{
            {NumberFactory.getNumber("2"), NumberFactory.getNumber("2"), NumberFactory.getNumber("0")},
            {NumberFactory.getNumber("0"), NumberFactory.getNumber("0"), NumberFactory.getNumber("0")},
            {NumberFactory.getNumber(String.valueOf(Integer.MAX_VALUE)), NumberFactory.getNumber(String.valueOf(Integer.MAX_VALUE)), NumberFactory.getNumber("0")}
    };
  }

  @DataProvider(name = "data-mult")
  public Object[][] dataMult() {
    return new Object[][]{
            {NumberFactory.getNumber("-2"), NumberFactory.getNumber("2"), NumberFactory.getNumber("-4")},
            {NumberFactory.getNumber("0"), NumberFactory.getNumber("0"), NumberFactory.getNumber("0")},
            {NumberFactory.getNumber(String.valueOf(Integer.MAX_VALUE)), NumberFactory.getNumber(String.valueOf(Integer.MAX_VALUE)), NumberFactory.getNumber("1")}
    };
  }

  @DataProvider(name = "data-div")
  public Object[][] dataDiv() {
    return new Object[][]{
            {NumberFactory.getNumber("10"), NumberFactory.getNumber("5"), NumberFactory.getNumber("2")},
            {NumberFactory.getNumber("0"), NumberFactory.getNumber("1"), NumberFactory.getNumber("0")},
            {NumberFactory.getNumber(String.valueOf(Integer.MAX_VALUE)), NumberFactory.getNumber(String.valueOf(Integer.MAX_VALUE)), NumberFactory.getNumber("1")}
    };
  }

  @DataProvider(name = "data-div-by-zero")
  public Object[][] dataDivByZero() {
    return new Object[][]{
            {NumberFactory.getNumber("-1"), NumberFactory.getNumber("0"), NumberFactory.getNumber("0")},
            {NumberFactory.getNumber("0"), NumberFactory.getNumber("0"), NumberFactory.getNumber("0")},
            {NumberFactory.getNumber(String.valueOf(Integer.MAX_VALUE)), NumberFactory.getNumber("0"), NumberFactory.getNumber("0")}
    };
  }

  @DataProvider(name = "data-input-format-numbers")
  public Object[][] dataPositiveInputFormatNumbers() {
    return new Object[][]{
            {"1 + -1", "2 + 2"},
            {"0 - -1", Integer.MAX_VALUE + " - " + 2147483647},
            {"3 * 1", "0 * 0"},
            {"-0 / 0", Integer.MAX_VALUE + " / -1"},
    };
  }

  @DataProvider(name = "data-input-format-symbols")
  public Object[][] dataNegativeInputFormatSymbols() {
    return new Object[][]{
            {"I + -1", "/ . 2"},
            {"0 - -1", "Integer.MAX_VALUE * " + 2147483647},
            {"III - ?", "0 3 0"},
            {"0 -/ %", Integer.MAX_VALUE + " / - "},
    };
  }
}
