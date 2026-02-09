/*
 * 

Stubbing

Stubbing means simulating the behavior of a mock object’s method. 
We can stub a method on a mock object by setting up an expectation on the 
method invocation. For example, we can stub the getProduct(int id) 
method of the ProductDao mock to return a specific Product instance when 
the method is called.
 */

package mypack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class ProductDAOTest3 
{
	@Mock
    private ProductDAO productDao;
    @Mock
    private Product product;
    @Before
    public void setupMock() {
       MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testMockCreation(){
        assertNotNull(product);
        assertNotNull(productDao);
    }
    @Test
    public void testgetProduct() {
    	Product p=new Product();
        when(productDao.getProduct(1)).thenReturn(p);
       // assertEquals(p,productDao.getProduct(1));
        assertEquals(product,productDao.getProduct(1));
    }


}


/*In the code above, we are stubbing getProduct(int id) of ProductDAO 
to return "p". The when() method represents the trigger to start the 
stubbing and thenReturn() represents the action of the trigger – 
which in the example code is to return the instance "p". On next line
with an assertion, we confirmed that the stubbing performed as expected.*/