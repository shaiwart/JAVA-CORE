package mypack;

public class AuthenticatorInterfaceImpl implements AuthenticatorInterface {

	@Override
	public boolean authenticateUser(String username, String password) {
		System.out.println("inside authenticateUser method");
		if(username.equalsIgnoreCase("scott")&& password.equals("tiger"))
			return true;
		else
		return false;
	}

}
