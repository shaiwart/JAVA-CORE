package mypack;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main 
{

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
        Transaction transaction = null; 
        try { 
            transaction = session.beginTransaction(); 
            Employee e1=new Employee();
           
            e1.setName("abc");
            e1.setSalary(30000);
            
            Employee e2=new Employee();
           
            e2.setName("xyz");
            e2.setSalary(40000);
            
            Set<Employee> employees=new HashSet<Employee>();
            employees.add(e1);
            employees.add(e2);
            Company c1=new Company();
            c1.setName("3I");
            c1.setLocation("Vashi");
            c1.setEmployees(employees);
           
            session.save(c1);
            transaction.commit(); 
            System.out.println("done with employees");  
             }
        catch(Exception ee)
        {
        	System.out.println("in catch\t"+ee);
        }
}
}








