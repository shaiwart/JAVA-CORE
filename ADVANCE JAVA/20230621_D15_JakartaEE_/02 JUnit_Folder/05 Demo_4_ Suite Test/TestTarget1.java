package mypack;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestTarget1 {
	String message = "Robert";   
	   Target t = new Target(message);
	   
	   @Test
	   public void testPrintMessage() {     
	      System.out.println("Inside testPrintMessage()");    
	      assertEquals(message, t.printMessage());     
	   }
	}
