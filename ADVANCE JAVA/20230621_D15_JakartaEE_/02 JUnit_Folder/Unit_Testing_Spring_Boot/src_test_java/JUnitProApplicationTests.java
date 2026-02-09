package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JUnitProApplicationTests 
{

	private Calculator c=new Calculator();
	
	@Test
	void contextLoads() 
	{
	}
	
	@Test
	void testSum()
	{
		int expectedResult=20;
		int actualResult=c.doSum(12,3,7);
		
		assertThat(actualResult).isEqualTo(expectedResult);
	}
	
	@Test
	void testMultiplication()
	{
		int expectedResult=20;
		int actualResult=c.doMultiplication(4, 5);
		assertThat(actualResult).isEqualTo(expectedResult);
	}

}
