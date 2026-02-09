package mypack;

import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;;
public class AuthenticatorApplicationTest {
	 
    @Test
    public void testAuthenticate() {
        AuthenticatorInterface authenticatorMock;
        AuthenticatorApplication authenticator;
        String username = "JavaCodeGeeks";
        String password = "unsafePassword";
         
        authenticatorMock = Mockito.mock(AuthenticatorInterface.class);
        System.out.println(authenticatorMock.getClass().getName());
        authenticator = new AuthenticatorApplication(authenticatorMock);
         
        /*when(authenticatorMock.authenticateUser(username, password)).thenReturn(true);
        boolean actual = authenticator.authenticate(username, password);
        System.out.println("actual is\t"+actual);*/
        
        	//	or
        when(authenticatorMock.authenticateUser(username, password)).thenReturn(false);
       
       boolean actual = authenticator.authenticate(username, password);
        System.out.println("actual is\t"+actual);
     
        // now let's pass real implementation of AuthenticatorInterface
        
       authenticator=new AuthenticatorApplication(new AuthenticatorInterfaceImpl());
        boolean b=authenticator.authenticate("scott","tiger");
        System.out.println(b);
    }
}
