package mypack;

public class Target1 {

   private int num;

public int getNum() {
	return num;
}
public int devide(int bynum)
{
	return num/bynum;
}
public Target1(int num) {
	super();
	this.num = num;
}
public Object getObject(int i)
{
	if(i==1)
	{
		return new String();
	}
	else
	{
		return new Object();
	}
}
}
 

