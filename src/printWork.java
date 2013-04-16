import java.io.IOException;


public class printWork
{
	//Used to clear the game console.
    public void clearConsole()
    {
       for(int x = 0; x < 5; x++)
       {
    	   System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
       }
    }
	
	public void printWhoIsHere(String dir) throws IOException, InterruptedException
	{
		xMLParse xP = new xMLParse();
		xP.xMLParser(dir);
		
		int x = 0;
		
		try
		{
			do
			{
				if(x%7 == 0)
				{
					System.out.print("\n");
				}
				if(x == xP.getTStrD().size()-2)
				{
					System.out.print(xP.getTStrD().get(x) + " and ");
				}
				else
				{
					if(x == xP.getTStrD().size()-1)
					{
						System.out.print(xP.getTStrD().get(x) + " are here.\n");
					}
					else
					{
						System.out.print(xP.getTStrD().get(x) + ", ");
					}
				}
				x++;
			}while(xP.getTStrD().get(x).equals(null) == false);
		}
		catch(Exception e)
		{
		}
	}
	
	public void printWhatIsHere(String dir) throws IOException, InterruptedException
	{
		xMLParse xP = new xMLParse();
		stringMod sM = new stringMod();
		
		xP.xMLParser(dir);
		
		int x = 0;
		
		try
		{
			do
			{
				if(x%7 == 0)
				{
					System.out.print("\n");
				}
				if(x == xP.getTStrD().size()-2)
				{
					if(sM.ifStartsWithVowel(xP.getTStrD().get(x)))
					{
						System.out.print("an " + xP.getTStrD().get(x) + " and ");
					}
					else
					{
						System.out.print("a " + xP.getTStrD().get(x) + " and ");
					}
				}
				else
				{
					if(x == xP.getTStrD().size()-1)
					{
						if(sM.ifStartsWithVowel(xP.getTStrD().get(x)))
						{
							System.out.print("an " + xP.getTStrD().get(x) + " is here.\n");
						}
						else
						{
							System.out.print("a " + xP.getTStrD().get(x) + " is here.\n");
						}
					}
					else
					{
						if(sM.ifStartsWithVowel(xP.getTStrD().get(x)))
						{
							System.out.print("an " + xP.getTStrD().get(x) + ", ");
						}
						else
						{
							System.out.print("a " + xP.getTStrD().get(x) + ", ");
						}
					}
				}
				x++;
			}while(xP.getTStrD().get(x).equals(null) == false);
		}
		catch(Exception e)
		{
		}
	}

	//when called upon, activated by the playerCommand ="help" in the setPlayerCommand method,
	//will loop through each line in the commands txt until it reaches the end, and will print them to the screen.
	public void printCommands() throws IOException, InterruptedException
	{
		xMLParse xP = new xMLParse();
		
		xP.xMLParser("/invXMLs/commands");
		
		for(int i = 0; i < xP.getTStrD().size(); i++)
		{
			if(i%7 == 0)
			{
				System.out.print("\n");
			}
			
			System.out.print(xP.getTStrD().get(i));
			
			if(xP.getTStrD().size()-1 != i)
			{
				System.out.print(", ");
			}
			else
			{
				System.out.print(".\n");
			}
		}
	}
}
