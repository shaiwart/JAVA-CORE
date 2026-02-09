package mypack;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO
{
	@Autowired
	HibernateTemplate template;

	@Override
	public List<Product> getProducts()
	{
		List<Product>mylist=(List<Product>) template.find("from Product p");
		return mylist;
	}
	@Override
	public void delete(int id)
	{
		template.delete(template.get(Product.class, id));
	}
	@Override
	public void update(Product product,int id)
	{
		template.bulkUpdate("update Product p set p.name=?,p.qty=?,p.price=?  where p.id=?",product.getName(),product.getQty(),product.getPrice(),id);
		//template.update(template.get(Product.class, id));
	}
	
	@Override
	public Product getProduct(int id)
	{
		Product temp=(Product)template.find("from Product p where p.id=?",id).get(0);
		return temp;
	}
	@Override
	public void addProduct(Product p) {
		template.save(p);
		
	}
}
