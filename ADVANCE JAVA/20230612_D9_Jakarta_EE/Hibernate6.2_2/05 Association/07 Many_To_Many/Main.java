package mypack;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory=cfg.buildSessionFactory();
		Session session=factory.openSession();
		Transaction tr=session.beginTransaction();
		Employee e1=new Employee();
		Employee e2=new Employee();
		e1.setEname("Harry");
		e2.setEname("Javed");
		Project p1=new Project();
		Project p2=new Project();
		p1.setPname("Emart");
		p2.setPname("Etour");
		List<Project>projectlist=new ArrayList<Project>();
		List<Employee>employeelist=new ArrayList<Employee>();
		projectlist.add(p1);
		projectlist.add(p2);
		employeelist.add(e1);
		employeelist.add(e2);
		// Harry has been assigned two projects
		e1.setProjects(projectlist);
		// Etour project has been assigned both the employees
		p2.setEmployees(employeelist);
		session.persist(e1);
		session.persist(e2);
		session.persist(p1);
		session.persist(p2);
		tr.commit();
		factory.close();
		System.out.println("Done with Many to Many");
	}

}
