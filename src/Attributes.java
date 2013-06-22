import java.io.File;
import java.io.IOException;


public class Attributes
{	
	public int focusIntData = -1;
	public String focusID = null;
	
	public void findCharacterHappiness(String charName) throws IOException, InterruptedException
	{
		StringMod sM = new StringMod();
		
		XMLParse character = new XMLParse();
		
		String charFile = sM.stringToEffectsTarget(charName);
		
		File file = new File("save/charXMLs/" + charFile);
		
		if(file.exists())
		{
			character.xMLParser("save/charXMLs/" + charFile);
		}
		else
		{
			character.xMLParser("charXMLs/" + charFile);
		}
		
		int happiness = findAttributeData("happiness", character);
		
		boolean found;
		if(happiness == -1)
		{
			found = false;
		}
		else
		{
			found = true;
		}
		if(found)
		{
			focusIntData = happiness;
			focusID = "happiness";
		}
	}
	
	public int findAttributeData(String attributeToFind, XMLParse character)
	{
		int attributeValue = -1;
		for(int x = 0; x < character.getTID1().size(); x++)
		{
			if(character.getTID1().get(x).equalsIgnoreCase(attributeToFind))
			{
				attributeValue = character.getTIntD().get(x);
				break;
			}
		}
		return attributeValue;
	}
	
	public String getFocusID()
	{
		return focusID;
	}
	
	public int getFocusIntData()
	{
		return focusIntData;
	}
}
