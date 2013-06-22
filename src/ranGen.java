import java.util.ArrayList;
import java.util.Collections;


public class RanGen
{
	int ranNum;
	
	//Generates a random number based on incoming parameters.
	public void setRanNum(int range)
	 {
	     //define ArrayList to hold Integer objects
	     ArrayList<Integer> numbers = new ArrayList<Integer>();
	     for(int i = 0; i < range; i++)
	     {
	       numbers.add(i+1);
	     }
	     Collections.shuffle(numbers);
	     ranNum = numbers.get(0);
	     numbers.clear();
	 }
	
	public int getRanNum()
	{
		return ranNum;
	}
}
