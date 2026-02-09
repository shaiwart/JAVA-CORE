package mypack;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
public class TestJunit1 {
        
   int val=100;      
   Target1 t = new Target1(100);

   @Test(expected = ArithmeticException.class)
   public void testPrintMessage()
   {
	  System.out.println("in testprintmessage");
	 // t.devide(4);
	  t.devide(0);
   }
   @Test(expected = IndexOutOfBoundsException.class)
   public void testEmptyList() {
	   System.out.println("in testEmptyList");
       new ArrayList<>().get(0);
	  // new ArrayList<>();
   }
   @Test(expected=ClassCastException.class)
   public void tryObject()
   {
	   String ref=(String)t.getObject(1);
	   //String ref=(String)t.getObject(2);
   }
}
