package mypack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
public class ProductDAOTest1 
{
	 private ProductDAO productDao;
	 private Product product;
	 @Before
	 public void setupMock() 
	 {
	    product = mock(Product.class);
	    productDao = mock(ProductDAO.class);
	 }
	 @Test
	 public void testMockCreation()
	 {
	    assertNotNull(product);
	    assertNotNull(productDao);
	  }
}
