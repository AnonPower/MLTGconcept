import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class EffectsOut
{
	XMLParse xP = new XMLParse();
	
	int elementID;
	
	String targetOfEffects,
		   filePathOfTarget;
	
	String unModTarget,
		   responseOut;
	
	public String effectsOutInit(String output, ArrayList<String> commandIn, ArrayList<String> charsUsed, ArrayList<String> toLinkings, String doer)
	{
		XMLWrite xW = new XMLWrite();
		EffectOutResponse eOR = new EffectOutResponse();
		StringMod sM = new StringMod();
		
		String tokenStor;
		
		if(output.contains(doer))
		{
			if(doer.equals("player") == false)
			{
				output = output.replaceFirst(doer + " ", "");
			}
		}
		
		if(output.contains("\n"))
		{
			output = output.replace("\n", "");
		}
		
		if(doer.equals("player"))
		{
		}
		else
		{
			if(commandIn.get(0).endsWith("s") || commandIn.get(0).endsWith("h") && commandIn.get(0).endsWith("gh") == false || commandIn.get(0).endsWith("x"))
			{
				 output = output.replace(commandIn.get(0) + "es", commandIn.get(0));
			}
			else
			{
				if(commandIn.get(0).endsWith("y"))
				{
					if(sM.ifStartsWithVowel(String.valueOf(commandIn.get(0).charAt(commandIn.get(0).length()-2))))
					{
						output = output.replace(commandIn.get(0) + "s", commandIn.get(0));
					}
					else
					{
						commandIn.set(0, commandIn.get(0).replace(commandIn.get(0).charAt(commandIn.get(0).length()-2) + "y", String.valueOf(commandIn.get(0).charAt(commandIn.get(0).length()-2)) + ""));
						output = output.replace(commandIn.get(0) + "ies", commandIn.get(0) + "y");
					}
				}
				else
				{
					output = output.replace(commandIn.get(0) + "s", commandIn.get(0));
				}
			}
		}
		
		StringTokenizer outputToken = new StringTokenizer(output);
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
						if(findTargetOfEffects(output, commandIn, charsUsed, toLinkings))
						{
							xW.xMLWriter(targetOfEffects, filePathOfTarget, xP.getTDTLID().get(elementID), xP.getTCLID().get(elementID), xP.getTSCLID().get(elementID), xP.getTAID().get(elementID), xP.getTStrE().get(elementID), xP.getTBooE().get(elementID), xP.getTIntEEqu().get(elementID), xP.getTIntEAdd().get(elementID), xP.getTIntESub().get(elementID));
							responseOut = eOR.responseInit(doer, getUnModTarget(), xP.getTAID().get(elementID), xP.getTStrE().get(elementID), xP.getTBooE().get(elementID), xP.getTIntEEqu().get(elementID), xP.getTIntEAdd().get(elementID), xP.getTIntESub().get(elementID));
							break;
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
						if(findTargetOfEffects(output, commandIn, charsUsed, toLinkings))
						{
							xW.xMLWriter(targetOfEffects, filePathOfTarget, xP.getTDTLID().get(elementID), xP.getTCLID().get(elementID), xP.getTSCLID().get(elementID), xP.getTAID().get(elementID), xP.getTStrE().get(elementID), xP.getTBooE().get(elementID), xP.getTIntEEqu().get(elementID), xP.getTIntEAdd().get(elementID), xP.getTIntESub().get(elementID));
							responseOut = eOR.responseInit(doer, getUnModTarget(), xP.getTAID().get(elementID), xP.getTStrE().get(elementID), xP.getTBooE().get(elementID), xP.getTIntEEqu().get(elementID), xP.getTIntEAdd().get(elementID), xP.getTIntESub().get(elementID));
							break;
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
						if(findTargetOfEffects(output, commandIn, charsUsed, toLinkings))
						{
							xW.xMLWriter(targetOfEffects, filePathOfTarget, xP.getTDTLID().get(elementID), xP.getTCLID().get(elementID), xP.getTSCLID().get(elementID), xP.getTAID().get(elementID), xP.getTStrE().get(elementID), xP.getTBooE().get(elementID), xP.getTIntEEqu().get(elementID), xP.getTIntEAdd().get(elementID), xP.getTIntESub().get(elementID));
							responseOut = eOR.responseInit(doer, getUnModTarget(), xP.getTAID().get(elementID), xP.getTStrE().get(elementID), xP.getTBooE().get(elementID), xP.getTIntEEqu().get(elementID), xP.getTIntEAdd().get(elementID), xP.getTIntESub().get(elementID));
							break;
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
				
				if(outputToken.hasMoreTokens())
				{
				}
				else
				{
					if(findTargetOfEffects(output, commandIn, charsUsed, toLinkings))
					{
						responseOut = eOR.neutralResponse(doer, getUnModTarget());
					}
				}
			}while(outputToken.hasMoreTokens());
		}
		catch(Exception e)
		{
		}
		return responseOut;
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
	
	public boolean findTargetOfEffects(String output, ArrayList<String> commandIn, ArrayList<String> charsUsed, ArrayList<String> toLinkings)
	{
		boolean foundTarget = false;
		
		try
		{
			if(toLinkings.isEmpty())
			{
				StringMod sM = new StringMod();
				
				for(int y = 0; y < charsUsed.size(); y++)
				{
					for(int z = 0; z < commandIn.size(); z++)
					{
						if(output.contains(commandIn.get(z) + " " + charsUsed.get(y)))
						{
							filePathOfTarget = "/charXMLs/";
							unModTarget = charsUsed.get(y);
							targetOfEffects = sM.stringToEffectsTarget(charsUsed.get(y));
							foundTarget = true;
							break;
						}
					}
				}
			}
			else
			{
				for(int x = 0; x < toLinkings.size(); x++)
				{
					for(int y = 0; y < charsUsed.size(); y++)
					{
						StringMod sM = new StringMod();
						
						if(output.contains(toLinkings.get(x)+ " " + charsUsed.get(y)))
						{
							filePathOfTarget = "/charXMLs/";
							unModTarget = charsUsed.get(y);
							targetOfEffects = sM.stringToEffectsTarget(charsUsed.get(y));
							foundTarget = true;
							break;
						}
						else
						{
							for(int z = 0; z < commandIn.size(); z++)
							{
								if(output.contains(commandIn.get(z) + " " + charsUsed.get(y)))
								{
									filePathOfTarget = "/charXMLs/";
									unModTarget = charsUsed.get(y);
									targetOfEffects = sM.stringToEffectsTarget(charsUsed.get(y));
									foundTarget = true;
									break;
								}
							}
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
	
	public String getUnModTarget()
	{
		return unModTarget;
	}
}
