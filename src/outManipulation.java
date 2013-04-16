
public class outManipulation
{
	//Used in the code to make the code wait based on seconds before continuing.
	//Numbers of seconds based on an incoming parameter.
	public void pauseTime(int n)
	{
		double t0,
			   t1,
			   t2 = 1000;
		t0 =  System.currentTimeMillis();
		do
		{
			t1 = System.currentTimeMillis();
		}while ((t1 - t0) < (n * t2));
	}
}