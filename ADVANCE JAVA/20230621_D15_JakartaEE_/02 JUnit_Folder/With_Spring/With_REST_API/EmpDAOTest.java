package mypack;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:mydispatcher-servlet.xml"})
public class EmpDAOTest {

	//DI
    @Autowired
    EmpDAO dao;

    @Test
    public void test_getAllEmployees()
    {
    	assertEquals(null,dao.getAllEmployees());
    }
    @Test
    public void test_totalEmployees()
    {
    	System.out.println(dao.getAllEmployees().size());
    }
}



