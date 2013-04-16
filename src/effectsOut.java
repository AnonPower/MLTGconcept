import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class effectsOut
{
	xMLParse xP = new xMLParse();
	
	int elementID;
	
	String targetOfEffects,
		   filePathOfTarget;
	
	public void effectsOutInit(String output, ArrayList<String> charsUsed, ArrayList<String> toLinkings)
	{
		xMLWrite xW = new xMLWrite();
		
		StringTokenizer outputToken = new StringTokenizer(output);
		
		String tokenStor;
		
		try
		{
			do
			{
				tokenStor = outputToken.nextToken();
				
				xP.xMLArraysWipe();
				
				if(findEffectInInput("/invXMLs/commands", tokenStor, output))
				{
					if(xP.getTLLID().get(elementID).equalsIgnoreCase("target"))
					{
						if(findTargetOfEffects(output, charsUsed, toLinkings))
						{
							xW.xMLWriter(targetOfEffects, filePathOfTarget, xP.getTDTLID().get(elementID), xP.getTCLID().get(elementID), xP.getTSCLID().get(elementID), xP.getTAID().get(elementID), xP.getTStrE().get(elementID), xP.getTBooE().get(elementID), xP.getTIntEEqu().get(elementID), xP.getTIntEAdd().get(elementID), xP.getTIntESub().get(elementID));
						}
					}
					else
					{
					}
					
					if(outputToken.hasMoreTokens())
					{
						continue;
					}
					else
					{
						break;
					}
				}
				if(findEffectInInput("/invXMLs/characters", tokenStor, output))
				{
					if(xP.getTLLID().get(elementID).equalsIgnoreCase("target"))
					{
						if(findTargetOfEffects(output, charsUsed, toLinkings))
						{
							xW.xMLWriter(targetOfEffects, filePathOfTarget, xP.getTDTLID().get(elementID), xP.getTCLID().get(elementID), xP.getTSCLID().get(elementID), xP.getTAID().get(elementID), xP.getTStrE().get(elementID), xP.getTBooE().get(elementID), xP.getTIntEEqu().get(elementID), xP.getTIntEAdd().get(elementID), xP.getTIntESub().get(elementID));
						}
					}
					else
					{
					}
					
					if(outputToken.hasMoreTokens())
					{
						continue;
					}
					else
					{
						break;
					}
				}
				if(findEffectInInput("/invXMLs/objects", tokenStor, output))
				{
					if(xP.getTLLID().get(elementID).equalsIgnoreCase("target"))
					{
						if(findTargetOfEffects(output, charsUsed, toLinkings))
						{
							xW.xMLWriter(targetOfEffects, filePathOfTarget, xP.getTDTLID().get(elementID), xP.getTCLID().get(elementID), xP.getTSCLID().get(elementID), xP.getTAID().get(elementID), xP.getTStrE().get(elementID), xP.getTBooE().get(elementID), xP.getTIntEEqu().get(elementID), xP.getTIntEAdd().get(elementID), xP.getTIntESub().get(elementID));
						}
					}
					else
					{
					}
					
					if(outputToken.hasMoreTokens())
					{
						continue;
					}
					else
					{
						break;
					}
				}
			}while(outputToken.hasMoreTokens());
		}
		catch(Exception e)
		{
		}
	}
	
	public boolean findEffectInInput(String dir, String token, String output) throws IOException, InterruptedException
	{
		boolean isEffectFound = false;
		
		xP.xMLParser(dir);
		
		for(int x = 0; x < xP.getTStrD().size(); x++)
		{
			try
			{
				for(int y = 0; y < xP.getTEMat().get(x)+1; y++)
				{
					if(y == xP.getTEMat().get(x))
					{
						if(token.equalsIgnoreCase(xP.getTStrD().get(y)))
						{
							isEffectFound = true;
							break;
						}
					}
				}
			}
			catch(Exception e)
			{
			}
			if(isEffectFound)
			{
				elementID = x;
				break;
			}
		}
		return isEffectFound;
	}
	
	public boolean findTargetOfEffects(String output, ArrayList<String> charsUsed, ArrayList<String> toLinkings)
	{
		boolean foundTarget = false;
		
		try
		{
			if(toLinkings.isEmpty())
			{
			}
			else
			{
				for(int x = 0; x < toLinkings.size(); x++)
				{
					for(int y = 0; y < charsUsed.size(); y++)
					{
						if(output.contains(toLinkings.get(x)+ " " + charsUsed.get(y)))
						{
							stringMod sM = new stringMod();
							
							filePathOfTarget = "/charXMLs/";
							targetOfEffects = sM.stringToEffectsTarget(charsUsed.get(y));
							foundTarget = true;
						}
					}
				}
			}
		}
		catch(Exception e)
		{
		}
		return foundTarget;
	}
}
