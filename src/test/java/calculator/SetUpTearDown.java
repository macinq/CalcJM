package calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class SetUpTearDown implements ITestListener {
  private static final Logger logger = LogManager.getLogger(CalcTest.class);

  @Override
  public void onTestStart(ITestResult iTestResult) {
    logger.info("Start test " + iTestResult.getMethod().getMethodName());
  }

  @Override
  public void onTestSuccess(ITestResult iTestResult) {
    logger.info(iTestResult.getMethod().getMethodName() + " PASSED! \n\n");
  }

  @Override
  public void onTestFailure(ITestResult iTestResult) {
    logger.error(iTestResult.getMethod().getMethodName() + " FAILURE! \n\n");
  }

  @Override
  public void onTestSkipped(ITestResult iTestResult) {
    logger.warn(iTestResult.getMethod().getMethodName() + " SKIPPED! \n\n");
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    // TODO
  }

  @Override
  public void onStart(ITestContext iTestContext) {
    logger.info("---------- START ALL ----------------\n\n");
  }

  @Override
  public void onFinish(ITestContext iTestContext) {
    logger.info("---------- FINISH ALL ----------------\n\n");
  }
}
