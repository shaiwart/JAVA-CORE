package mypack;

import java.util.List;

import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class EmployeeDAODatabaseImpl implements EmployeeDAO 
{
	private SessionFactory sf;
	private Session session;
	
	public EmployeeDAODatabaseImpl()
	{
		sf=HibernateUtil.getSessionFactory();
	}
	@Override
	public void insertEmployee(Employee ref) 
	{
		   session=sf.openSession();
		   Transaction trans=session.beginTransaction();
		   session.save(ref);
		   trans.commit();
		   session.close();
		   System.out.println("Done with commit");
		
		
	}

	@Override
	public Employee getEmployee() 
	{
		session=sf.openSession();
		Query<?> qry=session.createQuery("from Employee");
		List<?> mylist=qry.list();
		Employee ref=(Employee) mylist.get(0);
		session.close();
		return ref;
	}

}








