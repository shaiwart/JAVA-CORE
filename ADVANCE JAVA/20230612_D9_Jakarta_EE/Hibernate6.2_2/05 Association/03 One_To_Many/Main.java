package mypack;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
        Transaction transaction = null; 
        try { 
            transaction = session.beginTransaction(); 
           Student s1=new Student();
           Phone p1=new Phone();
           p1.setPhoneNumber("923456789");
           p1.setPhoneType("mobile");
           Phone p2=new Phone();
           p2.setPhoneNumber("2654321");
           p2.setPhoneType("resd");
           Set<Phone> h=new HashSet<Phone>();
           h.add(p1);
           h.add(p2);
           s1.setStudentName("abc");
           s1.setStudentPhoneNumbers(h);
           session.save(s1);
           transaction.commit(); 
            System.out.println("done with student");  
             }
        catch(Exception ee)
        {
        	System.out.println("in catch\t"+ee);
        }
}
}