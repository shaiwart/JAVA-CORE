package mypack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmpDAOImpl implements EmpDAO 
{
@Autowired
HibernateTemplate template;
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		List<Employee> list=(List<Employee>) template.find("from Employee");
		return list;
	}

}
