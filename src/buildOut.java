import java.util.ArrayList;
import java.util.StringTokenizer;


public class BuildOut
{
	public String buildCommOut(ArrayList<String> commandIn, String output, String input, String doer)
	{
		StringMod sM = new StringMod();
		
		try
		{
			if(commandIn.get(1).isEmpty())
			{
				if(doer.equals("player"))
				{
					output += ("You " + commandIn.get(0));
				}
				else
				{
					if(commandIn.get(0).endsWith("s") || commandIn.get(0).endsWith("h") && commandIn.get(0).endsWith("gh") == false || commandIn.get(0).endsWith("x"))
					{
						output += (doer + " " + commandIn.get(0) + "es");
					}
					else
					{
						if(commandIn.get(0).endsWith("y"))
						{
							if(sM.ifStartsWithVowel(String.valueOf(commandIn.get(0).charAt(commandIn.get(0).length()-2))))
							{
								output += (doer + " " + commandIn.get(0) + "s");
							}
							else
							{
								commandIn.set(0, commandIn.get(0).replace(commandIn.get(0).charAt(commandIn.get(0).length()-2) + "y", String.valueOf(commandIn.get(0).charAt(commandIn.get(0).length()-2)) + ""));
								output += (doer + " " + commandIn.get(0) + "ies");
							}
						}
						else
						{
							output += (doer + " " + commandIn.get(0) + "s");
						}
					}
				}
			}
			else
			{
				int y = 0;
				String tokenStor;
				StringTokenizer st = new StringTokenizer(input);
				while(y == 0)
				{
					tokenStor = st.nextToken();
					
					if(commandIn.contains(tokenStor))
					{
						y++;
						commandIn.add(commandIn.get(0));
						commandIn.set(0, tokenStor);
						int y3 = 0;
						StringTokenizer st2 = new StringTokenizer(input);
						while(y3 == 0)
						{
							tokenStor = st2.nextToken();
							
							if((commandIn.contains(tokenStor)))
							{
								if(commandIn.get(0).equals(tokenStor))
								{
								}
								else
								{
									y3++;
									commandIn.set(1, tokenStor);
								}
							}
						}
					}
				}
				if(doer.equals("player"))
				{
					output += ("You " + commandIn.get(0) + " and then " + commandIn.get(1));
				}
				else
				{
					if(commandIn.get(0).endsWith("s") || commandIn.get(0).endsWith("h") && commandIn.get(0).endsWith("gh") == false || commandIn.get(0).endsWith("x"))
					{
						output += (doer + " " + commandIn.get(0)+ "es and then ");
					}
					else
					{
						if(commandIn.get(0).endsWith("y"))
						{
							if(sM.ifStartsWithVowel(String.valueOf(commandIn.get(0).charAt(commandIn.get(0).length()-2))))
							{
								output += (doer + " " + commandIn.get(0) + "s and then ");
							}
							else
							{
								commandIn.set(0, commandIn.get(0).replace(commandIn.get(0).charAt(commandIn.get(0).length()-2) + "y", String.valueOf(commandIn.get(0).charAt(commandIn.get(0).length()-2)) + ""));
								output += (doer + " " + commandIn.get(0) + "ies and then ");
							}
						}
						else
						{
							output += (doer + " " + commandIn.get(0)+ "s and then ");
						}
					}
					if(commandIn.get(1).endsWith("s") || commandIn.get(1).endsWith("h") && commandIn.get(1).endsWith("gh") == false || commandIn.get(1).endsWith("x"))
					{
						output += (commandIn.get(1) + "es");
					}
					else
					{
						if(commandIn.get(1).endsWith("y"))
						{
							if(sM.ifStartsWithVowel(String.valueOf(commandIn.get(1).charAt(commandIn.get(1).length()-2))))
							{
								output += (doer + " " + commandIn.get(1) + "s");
							}
							else
							{
								commandIn.set(1, commandIn.get(1).replace(commandIn.get(1).charAt(commandIn.get(1).length()-2) + "y", String.valueOf(commandIn.get(1).charAt(commandIn.get(1).length()-2)) + ""));
								output += (commandIn.get(1) + "ies");
							}
						}
						else
						{
							output += (commandIn.get(1) + "s");
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			if(doer.equals("player"))
			{
				output += ("You " + commandIn.get(0));
			}
			else
			{
				if(commandIn.get(0).endsWith("s") || commandIn.get(0).endsWith("h") && commandIn.get(0).endsWith("gh") == false || commandIn.get(0).endsWith("x"))
				{
					output += (doer + " " + commandIn.get(0) + "es");
				}
				else
				{
					if(commandIn.get(0).endsWith("y"))
					{
						if(sM.ifStartsWithVowel(String.valueOf(commandIn.get(0).charAt(commandIn.get(0).length()-2))))
						{
							output += (doer + " " + commandIn.get(0) + "s");
						}
						else
						{
							commandIn.set(0, commandIn.get(0).replace(commandIn.get(0).charAt(commandIn.get(0).length()-2) + "y", String.valueOf(commandIn.get(0).charAt(commandIn.get(0).length()-2)) + ""));
							output += (doer + " " + commandIn.get(0) + "ies");
						}
					}
					else
					{
						output += (doer + " " + commandIn.get(0) + "s");
					}
				}
			}
		}
		return output;
	}
	
	public String buildCharOut(ArrayList<String> charsIn, String output, String input, String doer)
	{
		try
		{
			if(charsIn.get(1).isEmpty())
			{
				if(charsIn.get(0).equals("player") || charsIn.get(0).equals("me") || charsIn.get(0).equals("myself"))
				{
					if(doer.equals("player"))
					{
						output += (" yourself");
					}
					else
					{
						output += (" you");
					}
				}
				else
				{
					if(doer.equals(charsIn.get(0)))
					{
						output += (" themself");
					}
					else
					{
						output += (" " + charsIn.get(0));
					}
				}
			}
			else
			{
				int y = 0;
				String tokenStor;
				StringTokenizer st = new StringTokenizer(input);
				while(y == 0)
				{
					tokenStor = st.nextToken();
					
					if(charsIn.contains(tokenStor))
					{
						y++;
						charsIn.add(charsIn.get(0));
						charsIn.set(0, tokenStor);
						int y3 = 0;
						StringTokenizer st2 = new StringTokenizer(input);
						while(y3 == 0)
						{
							tokenStor = st2.nextToken();
							
							if((charsIn.contains(tokenStor)))
							{
								if(charsIn.get(0).equals(tokenStor))
								{
								}
								else
								{
									y3++;
									charsIn.set(1, tokenStor);
								}
							}
						}
					}
				}
				if(charsIn.get(0).equals("player") || charsIn.get(0).equals("me") || charsIn.get(0).equals("myself"))
				{
					if(doer.equals("player"))
					{
						output += (" yourself");
					}
					else
					{
						output += (" you");
					}
				}
				else
				{
					if(doer.equals(charsIn.get(0)))
					{
						output += (" themself");
					}
					else
					{
						output += (" " + charsIn.get(0));
					}
				}
			}
		}
		catch(Exception e)
		{
			if(charsIn.get(0).equals("player") || charsIn.get(0).equals("me") || charsIn.get(0).equals("myself"))
			{
				if(doer.equals("player"))
				{
					output += (" yourself");
				}
				else
				{
					output += (" you");
				}
			}
			else
			{
				if(doer.equals(charsIn.get(0)))
				{
					output += (" themself");
				}
				else
				{
					output += (" " + charsIn.get(0));
				}
			}
		}
		return output;
	}
	
	public String buildObjOut(ArrayList<String> objIn, String output, String input)
	{
		StringMod sM = new StringMod();
		
		try
		{
			if(objIn.get(1).isEmpty())
			{
				if(sM.ifStartsWithVowel(objIn.get(0)))
				{
					if(objIn.get(0).endsWith("s"))
					{
						output += (" " + objIn.get(0));
					}
					else
					{
						output += (" an " + objIn.get(0));
					}
				}
				else
				{
					if(objIn.get(0).endsWith("s"))
					{
						output += (" " + objIn.get(0));
					}
					else
					{
						output += (" a " + objIn.get(0));
					}
				}
			}
			else
			{
				int y = 0;
				String tokenStor;
				StringTokenizer st = new StringTokenizer(input);
				while(y == 0)
				{
					tokenStor = st.nextToken();
					
					if(objIn.contains(tokenStor))
					{
						y++;
						objIn.add(objIn.get(0));
						objIn.set(0, tokenStor);
						int y3 = 0;
						StringTokenizer st2 = new StringTokenizer(input);
						while(y3 == 0)
						{
							tokenStor = st2.nextToken();
							
							if((objIn.contains(tokenStor)))
							{
								if(objIn.get(0).equals(tokenStor))
								{
								}
								else
								{
									y3++;
									objIn.set(1, tokenStor);
								}
							}
						}
					}
				}
				if(sM.ifStartsWithVowel(objIn.get(0)))
				{
					if(objIn.get(0).endsWith("s"))
					{
						output += (" " + objIn.get(0));
					}
					else
					{
						output += (" an " + objIn.get(0));
					}
				}
				else
				{
					if(objIn.get(0).endsWith("s"))
					{
						output += (" " + objIn.get(0));
					}
					else
					{
						output += (" a " + objIn.get(0));
					}
				}
			}
		}
		catch(Exception e)
		{
			if(sM.ifStartsWithVowel(objIn.get(0)))
			{
				if(objIn.get(0).endsWith("s"))
				{
					output += (" " + objIn.get(0));
				}
				else
				{
					output += (" an " + objIn.get(0));
				}
			}
			else
			{
				if(objIn.get(0).endsWith("s"))
				{
					output += (" " + objIn.get(0));
				}
				else
				{
					output += (" a " + objIn.get(0));
				}
			}
		}
		return output;
	}
	
	public String buildUseOut(ArrayList<String> useLinkingIn, String output, String input)
	{
		try
		{
			if(useLinkingIn.get(1).isEmpty())
			{
				output += (" " + useLinkingIn.get(0));
			}
			else
			{
				int y = 0;
				String tokenStor;
				StringTokenizer st = new StringTokenizer(input);
				while(y == 0)
				{
					tokenStor = st.nextToken();
					
					if(useLinkingIn.contains(tokenStor))
					{
						y++;
						useLinkingIn.add(useLinkingIn.get(0));
						useLinkingIn.set(0, tokenStor);
						int y3 = 0;
						StringTokenizer st2 = new StringTokenizer(input);
						while(y3 == 0)
						{
							tokenStor = st2.nextToken();
							
							if((useLinkingIn.contains(tokenStor)))
							{
								if(useLinkingIn.get(0).equals(tokenStor))
								{
								}
								else
								{
									y3++;
									useLinkingIn.set(1, tokenStor);
								}
							}
						}
					}
				}
				output += (" " + useLinkingIn.get(0));
			}
		}
		catch(Exception e)
		{
			output += (" " + useLinkingIn.get(0));
		}
		return output;
	}
	
	public String buildToTarOut(ArrayList<String> toLinkingIn, String output, String input)
	{
		try
		{
			if(toLinkingIn.get(1).isEmpty())
			{
				output += (" " + toLinkingIn.get(0));
			}
			else
			{
				int y = 0;
				StringTokenizer st = new StringTokenizer(input);
				String tokenStor;
				while(y == 0)
				{
					tokenStor = st.nextToken();
					
					if(toLinkingIn.contains(tokenStor))
					{
						y++;
						toLinkingIn.add(toLinkingIn.get(0));
						toLinkingIn.set(0, tokenStor);
						int y3 = 0;
						StringTokenizer st2 = new StringTokenizer(input);
						while(y3 == 0)
						{
							tokenStor = st2.nextToken();
							
							if((toLinkingIn.contains(tokenStor)))
							{
								if(toLinkingIn.get(0).equals(tokenStor))
								{
								}
								else
								{
									y3++;
									toLinkingIn.set(1, tokenStor);
								}
							}
						}
					}
				}
				output += (" " + toLinkingIn.get(0));
			}
		}
		catch(Exception e)
		{
			output += (" " + toLinkingIn.get(0));
		}
		return output;
	}

	public void saveOutput(String outputToAdd) throws InterruptedException
	{
		GameDriver.savedOutput += outputToAdd;
	}
}
