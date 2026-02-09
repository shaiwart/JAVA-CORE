/*
 * 

An alternative way for "mock()" method is to use the @Mock annotation. 
When you use it, you will need to initialize the mocks with a call to 
MockitoAnnotations.initMocks(this) or specify MockitoJUnitRunner as 
the JUnit test runner as @RunWith(MockitoJUnitRunner.class).
 */

package mypack;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductDAOTest2 
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

}
