package mypack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductManagerImpl implements ProductManager
{
	@Autowired
	ProductDAO dao;

	@Override
	public void addProduct(Product p) {
		dao.addProduct(p);
		
	}

	@Override
	public List<Product> getProducts() {
		return dao.getProducts();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public void update(Product product,int id) {
		// TODO Auto-generated method stub
		dao.update(product,id);
		
	}

	@Override
	public Product getProduct(int id) {
		// TODO Auto-generated method stub
		//return null;
		return dao.getProduct(id);
	}
	
	

}
