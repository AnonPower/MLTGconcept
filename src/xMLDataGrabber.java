import java.util.ArrayList;


public class XMLDataGrabber
{
	public RanGen rG = new RanGen();
	
	public String grabCommand(ArrayList<String> commandList, ArrayList<String> selectedCommandsList, String input, String doer, int d)
	{
		int ranNum;
		
		try
		{
			//grab command randomly
			rG.setRanNum(commandList.size()*2);
			ranNum = rG.getRanNum();
			
			if(commandList.get(ranNum).isEmpty() || doer.isEmpty())
			{
			}
			else
			{
				if(d != 0)
				{
					if(selectedCommandsList.isEmpty())
					{
						selectedCommandsList.add(commandList.get(ranNum));
						input += (" " + selectedCommandsList.get(0));
					}
					else
					{
						selectedCommandsList.add(commandList.get(ranNum));
						input += (" and " + selectedCommandsList.get(d));
					}
				}
			}
		}
		catch(Exception e)
		{
		}
		return input;
	}
	
	public String grabTarChar(ArrayList<String> characterList, ArrayList<String> tarCharacterList, String input, int chanceMod, String doer)
	{
		int ranNum;
		
		try
		{
			//grab tarChatacter randomly
			rG.setRanNum(characterList.size()*chanceMod);
			ranNum = rG.getRanNum();
			
			if(characterList.get(ranNum).isEmpty())
			{
			}
			else
			{
				if(characterList.get(ranNum).equals(doer))
				{
					tarCharacterList.add(characterList.get(ranNum));
					input += (" themself");
				}
				else
				{
					tarCharacterList.add(characterList.get(ranNum));
					input += (" " + characterList.get(ranNum));
				}
			}
		}
		catch(Exception e)
		{
		}
		return input;
	}
	
	public String grabObj(ArrayList<String> objList, ArrayList<String> objsUsedList, String input, int chanceMod)
	{
		int ranNum;
		
		try
		{
			//grab object randomly
			rG.setRanNum(objList.size()*chanceMod);
			ranNum = rG.getRanNum();
			
			if(objList.get(ranNum).isEmpty())
			{
			}
			else
			{
				objsUsedList.add(objList.get(ranNum));
				input += (" " + objList.get(ranNum));
			}
		}
		catch(Exception e1)
		{
		}
		return input;
	}

	public String grabToTar(ArrayList<String> toTarLinkingList, ArrayList<String> toTarLinkingsUsedList, String input, int chanceMod)
	{
		int ranNum;
		
		try
		{
			//grab toTarLinking randomly
			rG.setRanNum(toTarLinkingList.size()*chanceMod);
			ranNum = rG.getRanNum();
			
			if(toTarLinkingList.get(ranNum).isEmpty())
			{
			}
			else
			{
				toTarLinkingsUsedList.add(toTarLinkingList.get(ranNum));
				input += (" " + toTarLinkingList.get(ranNum));
			}
		}
		catch(Exception e1)
		{
		}
		return input;
	}
	
	public String grabUse(ArrayList<String> useLinkingList, ArrayList<String> useLinkingsUsedList, String input, int chanceMod)
	{
		int ranNum;
		
		try
		{
			//grab useLinking randomly
			rG.setRanNum(useLinkingList.size()*chanceMod);
			ranNum = rG.getRanNum();
			
			if(useLinkingList.get(ranNum).isEmpty())
			{
			}
			else
			{
				useLinkingsUsedList.add(useLinkingList.get(ranNum));
				input += (" " + useLinkingList.get(ranNum));
			}
		}
		catch(Exception e1)
		{
		}
		return input;
	}

	public boolean isInteger(String readIn)
	{
		try
		{
			Integer.parseInt(readIn);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
