import java.util.ArrayList;
import java.util.StringTokenizer;

public class StringMod
{
	public ArrayList<String> stringArrayCutter(ArrayList<String> arrayIn, ArrayList<String> toRemove)
	{
		for(int y = 0; y < arrayIn.size(); y++)
		{
			try
			{
				for(int x = 0; x < toRemove.size(); x++)
				{
					try
					{
						if(arrayIn.get(y).equals(toRemove.get(x)))
						{
							arrayIn.set(y, null);
						}
					}
					catch(Exception e)
					{
					}
				}
			}
			catch(Exception e)
			{
			}
		}
		return arrayIn;
	}	
	
	public boolean ifStartsWithVowel(String in)
	{
		boolean ifStartsWithVowel = false;
		
		if(in.startsWith("a") || in.startsWith("A") || in.startsWith("i") || in.startsWith("I") || in.startsWith("o") || in.startsWith("O") || in.startsWith("u") || in.startsWith("U") || in.startsWith("e") || in.startsWith("E"))
		{
			ifStartsWithVowel = true;
		}
		return ifStartsWithVowel;
	}

	public String stringToEffectsTarget(String target)
	{
		StringTokenizer targetTokened = new StringTokenizer(target);
		
		String converted = "",
			   tokenStor,
			   capitalStor;
		
		if(target.contains(" "))
		{
			tokenStor = targetTokened.nextToken();
			
			converted += tokenStor.toLowerCase();
			do
			{
				tokenStor = targetTokened.nextToken();
				tokenStor = tokenStor.toLowerCase();
				capitalStor = String.valueOf(tokenStor.charAt(0));
				converted += tokenStor.replaceFirst(String.valueOf(tokenStor.charAt(0)), capitalStor.toUpperCase());
			}while(targetTokened.hasMoreTokens());
		}
		else
		{
			converted = target.toLowerCase();
		}
		return converted;
	}
}
