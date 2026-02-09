package master.com.vita1;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    			Session session = HibernateUtil.getSessionFactory().openSession(); 
    	        Transaction transaction = null; 
    	        try { 
    	            transaction = session.beginTransaction(); 
    	            Employee e1=new Employee();
    	           
    	            e1.setName("abc");
    	            e1.setDesig("officer");
    	            e1.setSalary(30000);
    	            
    	            Employee e2=new Employee();
    	           
    	            e2.setName("xyz");
    	            e2.setDesig("manager");
    	            e2.setSalary(40000);
    	            session.save(e1);
    	            session.save(e2);
    	            transaction.commit(); 
    	            HibernateUtil.getSessionFactory().close();
    	            System.out.println("done with employees");  
    	             }
    	        catch(Exception ee)
    	        {
    	        	System.out.println("in catch\t"+ee);
    	        }
    	}
}
