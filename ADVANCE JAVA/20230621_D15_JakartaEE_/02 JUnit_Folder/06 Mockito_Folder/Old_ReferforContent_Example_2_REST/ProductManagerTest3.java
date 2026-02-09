package mypack;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
public class ProductManagerTest3
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
	    public void getProductsTest()
	    {
	        List<Product> list = new ArrayList<Product>();
	        Product p1 = new Product();
	        p1.setId(1);
	        p1.setName("abc");
	        p1.setPrice(100);
	        p1.setQty(30);
	        Product p2 = new Product();
	        p2.setId(2);
	        p2.setName("xyz");
	        p1.setPrice(150);
	        p1.setQty(80);
	         
	        list.add(p1);
	        list.add(p2);
	    	         
	        when(dao.getProducts()).thenReturn(list);
	         
	        //Try to comment following 2 lines:
	        
	        List<Product> prodList = productManager.getProducts();
	         
	        assertEquals(2, prodList.size());
	        
	        verify(dao, times(1)).getProducts();
	    }
	 	  	@Test
	 	    public void getProductByIdTest()
	 	    {
	 	  		Product p1=new Product();
	 	  		p1.setId(1);
	 	  		p1.setName("bbb");
	 	  		p1.setPrice(200);
	 	  		p1.setQty(40);
	 	        when(dao.getProduct(1)).thenReturn(p1);
	 	         
	 	        Product ref = productManager.getProduct(1);
	 	  
	 	        assertEquals("bbb", ref.getName());
	 	        assertEquals(200, ref.getPrice());
	 	        assertEquals(40, ref.getQty());
	 	    }
	 	   @Test
	 	    public void addProductTest()
	 	    {
	 	        Product p1 = new Product();
	 	        p1.setId(1);
		        p1.setName("abc");
		        p1.setPrice(100);
		        p1.setQty(30);
	 	         
		        //  try commenting next statement
		        
	 	      //  productManager.addProduct(p1);
	 	         
	 	        verify(dao, times(1)).addProduct(p1);
	 	    }
}
	 	