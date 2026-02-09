package mypack;

import java.util.List;

public interface ProductDAO 
{
	void addProduct(Product p);
	List<Product> getProducts();
	void delete(int id);
	void update(Product product,int id);
	Product getProduct(int id);
}
