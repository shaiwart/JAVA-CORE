package mypack;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class SampleTest 
{
	private Scanner sc=new Scanner(System.in);
	private Sample s=new Sample();
	@Test
	public void testSqr() {
		System.out.println("Enter input");
		int input=sc.nextInt();
		System.out.println("Enter expected Result");
		int result=sc.nextInt();
		assertEquals(result,s.sqr(input));
	}

}
