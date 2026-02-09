package mypack;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestTarget2 {

	String message = "Robert";   
	   Target t = new Target(message);
	 
	   @Test
	   public void testSalutationMessage() {
	      System.out.println("Inside testSalutationMessage()");
	      message = "Hi!" + "Kunal";
	      assertEquals(message,t.salutationMessage());
	   }
	}
