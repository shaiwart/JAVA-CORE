package com.example.demo;
public class Person 
{
	private String personName, address;
	private int age;
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+personName+"   "+age+"   "+address+"]";
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
