


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import mypack.Book;
import mypack.HibernateUtil;
import org.hibernate.*;
@WebServlet("/BookServ") 
public class BookServ extends HttpServlet
{
	private Session session;
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			session = HibernateUtil.getSessionFactory().openSession(); 
			System.out.println("after change");
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			String name=request.getParameter("name");
			String subject=request.getParameter("subject");
			int cost=Integer.parseInt(request.getParameter("cost").trim());
			Book b1=new Book();
			b1.setName(name);
			b1.setSubject(subject);
			b1.setCost(cost);
			Transaction tx = session.beginTransaction();
			session.save(b1);
			tx.commit();
			session.close();
			out.println("Done with book");
		}
		catch(Exception ee)
		{
			System.out.println("in service  "+ee);
		}
	}






}