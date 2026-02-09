/*
 * Verifying
Our objective is to test ProductService, and until now we only mocked 
Product and ProductDao and stubbed getProduct(int id) of ProductDao.

We now want to verify the behavior of the getProduct(int id) method of 
ProductManager. First, we want to verify whether it’s calling the 
getProduct(int id) of ProductDao with the required set of parameters.
 */
package mypack;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
public class ProductManagerTest1 
{
	@Mock
    private ProductDAO dao;
	@InjectMocks
	private ProductManagerImpl productManager;
	@Mock
	private Product product;
	
	@Before
    public void setupMock() 
	{
		MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testgetProduct() 
	{
		System.out.println("productManager is\t"+productManager.getClass().getName());
	    productManager.getProduct(1);
	    verify(dao).getProduct(1);
	}
}
/*

how will u test above "testgetProduct()" method?

if the code inside "ProductManager"s "getProduct()" method is as follows:

public Product getProduct(int id) 
{
	return dao.getProduct(id);
}

then your test will succeed.  It is because ProductManager's getProduct 
calls ProductDAO's getProduct() method.

 
 now try with following changes inside "ProductManager" getProduct() method:
 
 
 public Product getProduct(int id) 
 {
		return null;
  }
  
  
  now your test will fail because "ProductManager" getProduct() is not
  invoking getProduct of "ProductDAO"
  
  it will give failure trace saying that:
  
  Wanted but not invoked:
  	dao.getProduct(1)
  	
  	Actually there were zero interactions with this mock.
  
*/
