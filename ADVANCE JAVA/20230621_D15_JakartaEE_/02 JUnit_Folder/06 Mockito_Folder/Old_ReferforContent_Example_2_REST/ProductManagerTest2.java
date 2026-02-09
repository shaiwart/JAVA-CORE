/*
 * Verifying
We now want to verify whether "addProduct()" of "dao" gets invoked at least once and it all depends wheather we invoke "addProduct()" of 
ProductManager. 
 */
package mypack;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
public class ProductManagerTest2
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
	public void testaddProduct() 
	{
		//productManager.addProduct(product);
		verify(dao, atLeastOnce()).addProduct(product);
	}
}
/*

in the above code try to comment following line
	productManager.addProduct(product);
	
  now your test will fail. It is because "ProductManager" addProduct() 
  invokes "addProduct()" of "ProductDAO".
  
  it will give failure trace saying that:
  
  Wanted but not invoked:
  	dao.getProduct(1)
  	
  	Actually there were zero interactions with this mock.
  
*/
