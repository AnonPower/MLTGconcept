import java.io.IOException;


public class PrintWork
{
	//Used to clear the game console.
    public void clearConsole()
    {
       Window.output.setText("");
    }
	
	public void printWhoIsHere(String dir) throws IOException, InterruptedException
	{
		XMLParse xP = new XMLParse();
		
		xP.xMLParser(dir);
		
		String output = "";
		
		int x = 0,
			y = 1;
		
		try
		{
			do
			{
				if(xP.getTStrD().get(x).equals("player") || xP.getTStrD().get(x).equals("me") || xP.getTStrD().get(x).equals("myself") || xP.getTStrD().get(x).equals("self"))
				{
					y++;
				}
				else
				{
					if(x == xP.getTStrD().size()-2)
					{
						output += (xP.getTStrD().get(x) + " and ");
					}
					else
					{
						if(x == xP.getTStrD().size()-1)
						{
							output += (xP.getTStrD().get(x) + " are here.\n");
							break;
						}
						else
						{
							output += (xP.getTStrD().get(x) + ", ");
						}
					}
				}
				x++;
			}while(xP.getTStrD().get(x).equals(null) == false);
		}
		catch(Exception e)
		{
		}
		
		if(output.endsWith("are here.\n"))
		{
			System.out.print(output);
		}
		else
		{
			if(output.contains(" and "))
			{
				output += (" are here.\n");
				System.out.print(output);
			}
			else
			{
				output = output.replace(" " + xP.getTStrD().get(x-y) + ",", " and " + xP.getTStrD().get(x-y) + " are here.\n");
				System.out.print(output);
			}
		}
	}
	
	public void printWhatIsHere(String dir) throws IOException, InterruptedException
	{
		XMLParse xP = new XMLParse();
		StringMod sM = new StringMod();
		
		xP.xMLParser(dir);
		
		int x = 0;
		
		try
		{
			do
			{
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
		XMLParse xP = new XMLParse();
		
		xP.xMLParser("/invXMLs/commands");
		
		for(int i = 0; i < xP.getTStrD().size(); i++)
		{
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
