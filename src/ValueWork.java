import java.util.ArrayList;
import java.util.Collections;

public class ValueWork
{
	ArrayList<Integer> sortedBadGoodArray = new ArrayList<Integer>(),
					  sortedGoodBadArray = new ArrayList<Integer>();
	
	public ArrayList<Integer> getSortedBadGoodArray()
	{
		return sortedBadGoodArray;
	}
	
	public ArrayList<Integer> getSortedGoodBadArray()
	{
		return sortedGoodBadArray;
	}
	
	public void sortBadGoodValues(ArrayList<Integer> badGoodArray)
	{
		Collections.sort(badGoodArray);
		Collections.reverse(badGoodArray);
		sortedBadGoodArray.addAll(badGoodArray);
	}
	
	public void sortGoodBadValues(ArrayList<Integer> goodBadArray)
	{
		Collections.sort(goodBadArray);
		sortedGoodBadArray.addAll(goodBadArray);
	}
}
