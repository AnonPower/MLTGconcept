import java.io.IOException;


public class EffectOutResponse
{
	XMLParse xP = new XMLParse();
	
	public String responseInit(String doer, String target, String targetAttribute, String tStrE, String tBooE, String tIntEEqu, int tIntEAdd, int tIntESub) throws IOException, InterruptedException
	{
		xP.xMLArraysWipe();
		
		xP.xMLParser("/invXMLs/attributesList");
		
		String responseOut = "";
		
		int markerCounter = 0;
		
		do
		{
			try
			{
				if(xP.getMarkerID().get(xP.getTMMat().get(markerCounter)).equals("range") == false)
				{
					do
					{
						markerCounter++;
						if(xP.getMarkerID().get(xP.getTMMat().get(markerCounter)).equals("range"))
						{
							break;
						}
					}while(xP.getMarkerID().get(xP.getTMMat().get(markerCounter)).equals("range") == false);
				}
			}
			catch(Exception e)
			{
				break;
			}
			
			if(xP.getTStrD().get(xP.getTMMat().get(markerCounter)).equals(targetAttribute))
			{
				if(xP.getMarkerData().get(xP.getTMMat().get(markerCounter)).equalsIgnoreCase("good-bad"))
				{
					if(tIntEAdd != 0)
					{
						responseOut = badResponse(doer, target, targetAttribute, tStrE, tBooE, tIntEEqu, tIntEAdd, tIntESub);
						break;
					}
					else
					{
						if(tIntESub != 0)
						{
							responseOut = goodResponse(doer, target, targetAttribute, tStrE, tBooE, tIntEEqu, tIntEAdd, tIntESub);
							break;
						}
						else
						{
							responseOut = neutralResponse(doer, target);
							break;
						}
					}
				}
				else
				{
					if(xP.getMarkerData().get(xP.getTMMat().get(markerCounter)).equalsIgnoreCase("bad-good"))
					{
						if(tIntEAdd != 0)
						{
							responseOut = goodResponse(doer, target, targetAttribute, tStrE, tBooE, tIntEEqu, tIntEAdd, tIntESub);
							break;
						}
						else
						{
							if(tIntESub != 0)
							{
								responseOut = badResponse(doer, target, targetAttribute, tStrE, tBooE, tIntEEqu, tIntEAdd, tIntESub);
								break;
							}
							else
							{
								responseOut = neutralResponse(doer, target);
								break;
							}
						}
					}
				}
			}
			markerCounter++;
		}while(xP.getMarkerID().get(markerCounter).equals("range"));
		if(responseOut.equals(""))
		{
			return responseOut;
		}
		else
		{
			return ("\n" + responseOut + "\n");
		}
	}
	
	public String goodResponse(String doer, String target, String targetAttribute, String tStrE, String tBooE, String tIntEEqu, int tIntEAdd, int tIntESub)
	{
		String response = "";
		
		if(doer.equals(target))
		{
			response = (target + " makes themself happy.");
		}
		else
		{
			if(doer.equals("player"))
			{
				doer = "you";
			}
			response = (target + " hugs " + doer + ".");
		}
		
		return response;
	}
	
	public String badResponse(String doer, String target, String targetAttribute, String tStrE, String tBooE, String tIntEEqu, int tIntEAdd, int tIntESub)
	{
		String response = "";
		
		if(doer.equals(target))
		{
			response = (target + " makes themself sad.");
		}
		else
		{
			if(doer.equals("player"))
			{
				doer = GameDriver.getPlayerName();
			}
			response = (target + " says, \"Why would you do that to me " + doer + "?!\" and then " + target + " frowns.");
		}
		
		return response;
	}
	
	public String neutralResponse(String doer, String target)
	{
		String response = "";
		
		if(doer.equals(target))
		{
			response = (target + " and doesn't give a fuck.");
		}
		else
		{
			if(doer.equals("player"))
			{
				doer = GameDriver.getPlayerName();
			}
			response = (target + " says, \"Do you think this is a fucking game " + doer + "?!\".");
		}
		
		return ("\n" + response + "\n");
	}
}
