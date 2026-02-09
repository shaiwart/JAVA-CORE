package mypack;

import org.apache.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunner {
	final static Logger logger = Logger.getLogger(TestRunner.class);

	
   public static void main(String[] args) {
	  
	      Result result = JUnitCore.runClasses(JUnitTestSuite.class);
      for (Failure failure : result.getFailures()) {
         logger.error(failure.toString());
        
      }
if(result.wasSuccessful())
      logger.info("it was successful");
   }
}       
