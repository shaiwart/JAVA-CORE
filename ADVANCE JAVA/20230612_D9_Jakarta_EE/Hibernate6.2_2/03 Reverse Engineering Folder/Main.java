package mypack;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration cfg=new Configuration();		
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory=cfg.buildSessionFactory();
		Session session=factory.openSession();
		Transaction tr=session.beginTransaction();
            Dept d=new Dept();
            d.setDeptno(11);
            d.setDname("Training");
            d.setLoc("Kashmir");
            session.save(d);
            transaction.commit(); 
            System.out.println("done with department");

}
}