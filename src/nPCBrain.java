import java.io.IOException;
import java.util.*;


public class NPCBrain
{	
	String focusID = null;
	int focusIntData = -1;
	
	Attributes attributeClass = new Attributes();
	//random AI process
	//to be revised to more procedural based on various parameters
	public void setNPCCommand() throws IOException, InterruptedException
	{
		XMLParse xP = new XMLParse();
		StringMod sM = new StringMod();
		XMLDataGrabber xDG = new XMLDataGrabber();
		RanGen rG = new RanGen();
		
		ArrayList<String> selectedCommandsList = new ArrayList<String>(),
				  tarCharacterList = new ArrayList<String>(),
				  objsUsedList = new ArrayList<String>(),
				  useLinkingsUsedList = new ArrayList<String>(),
				  toTarLinkingsUsedList = new ArrayList<String>();

		ArrayList<String> toRemove = new ArrayList<String>();
		
		String doer = null,
			   input = "";
		
		xP.xMLParser("/invXMLs/characters");
		ArrayList<String> characterList = new ArrayList<String>(xP.getTStrD());
		toRemove.add("me");
		toRemove.add("myself");
		toRemove.add("self");
		characterList = sM.stringArrayCutter(characterList, toRemove);
		
		xP.xMLParser("/invXMLs/commands");
		ArrayList<String> commandList = new ArrayList<String>(xP.getTStrD());
		toRemove.add("system.clear");
		toRemove.add("system.quit");
		toRemove.add("what is here");
		toRemove.add("who is here");
		toRemove.add("player.change name");
		toRemove.add("system.play.song");
		toRemove.add("system.stop.song");
		commandList = sM.stringArrayCutter(commandList,toRemove);
		toRemove.clear();
		
		xP.xMLParser("/invXMLs/objects");
		ArrayList<String>objList = new ArrayList<String>(xP.getTStrD());
		
		xP.xMLParser("/invXMLs/toTarLinking");
		ArrayList<String> toTarLinkingList = new ArrayList<String>(xP.getTStrD());
		
		xP.xMLParser("/invXMLs/useLinking");
		ArrayList<String> useLinkingList = new ArrayList<String>(xP.getTStrD());
		
		for(int x = 0; x < characterList.size(); x++)
		{
			input = "";
			
			//reset Arrays for each doer
			selectedCommandsList.clear();
			tarCharacterList.clear();
			objsUsedList.clear();
			toTarLinkingsUsedList.clear();
			useLinkingsUsedList.clear();
			
			try
			{
				if(characterList.get(x).equalsIgnoreCase("player"))
				{
				}
				else
				{
					doer = characterList.get(x);
					attributeClass.findCharacterHappiness(doer);
					input = doer;
					int d = 0;
					while(d != 2)
					{
						//?command level
						input = xDG.grabCommand(commandList, selectedCommandsList, input, doer, d);
						//?command level end
						d++;
					}
					
					if(selectedCommandsList.isEmpty() == false)
					{
						int actionType;
						
						rG.setRanNum(5);
						actionType = rG.getRanNum();
						
						if(actionType == 1)
						{
							//doing something to something with something
							//!toTar level
							input = xDG.grabToTar(toTarLinkingList, toTarLinkingsUsedList, input, 1);
							//?tarChar level
							input = xDG.grabTarChar(characterList, tarCharacterList, input, 2, doer);
							//!obj level
							try
							{
								if(tarCharacterList.isEmpty())
								{
									input = xDG.grabObj(objList, objsUsedList, input, 1);
								}
								else
								{
								}
							}
							catch(Exception e)
							{
								input = xDG.grabObj(objList, objsUsedList, input, 1);
							}
							//!use level
							input = xDG.grabUse(useLinkingList, useLinkingsUsedList, input, 1);
							//?tarChar level
							input = xDG.grabTarChar(characterList, tarCharacterList, input, 2, doer);
							//!obj level
							try
							{
								if(tarCharacterList.isEmpty())
								{
									input = xDG.grabObj(objList, objsUsedList, input, 1);
								}
								else
								{
								}
							}
							catch(Exception e)
							{
								input = xDG.grabObj(objList, objsUsedList, input, 1);
							}
							//!obj level end
							//?tarChar level end
							//!use level end
							//!obj level end
							//?tarChar level end
							//!toTar level end
							//doing something to something with something end
						}
						else
						{
							if(actionType == 2)
							{
								//doing something with something to something
								//!use level
								input = xDG.grabUse(useLinkingList, useLinkingsUsedList, input, 1);
								//?tarChar
								input = xDG.grabTarChar(characterList, tarCharacterList, input, 2, doer);
								//!obj level
								try
								{
									if(tarCharacterList.isEmpty())
									{
										input = xDG.grabObj(objList, objsUsedList, input, 1);
									}
									else
									{
									}
								}
								catch(Exception e)
								{
									input = xDG.grabObj(objList, objsUsedList, input, 1);
								}
								//!toTar level
								input = xDG.grabToTar(toTarLinkingList, toTarLinkingsUsedList, input, 1);
								//?tarChar level
								input = xDG.grabTarChar(characterList, tarCharacterList, input, 2, doer);
								//!obj level
								try
								{
									if(tarCharacterList.isEmpty())
									{
										input = xDG.grabObj(objList, objsUsedList, input, 1);
									}
									else
									{
									}
								}
								catch(Exception e)
								{
									input = xDG.grabObj(objList, objsUsedList, input, 1);
								}
								//!obj level end
								//?tarChar level end
								//!toTar level end
								//!obj level end
								//?tarChat end
								//!use level end
								//doing something with something to something end
							}
							else
							{
								if(actionType == 3)
								{
									//doing something to something
									//!toTar
									input = xDG.grabToTar(toTarLinkingList, toTarLinkingsUsedList, input, 1);
									//?tarChar level
									input = xDG.grabTarChar(characterList, tarCharacterList, input, 2, doer);
									//!obj level
									try
									{
										if(tarCharacterList.isEmpty())
										{
											input = xDG.grabObj(objList, objsUsedList, input, 1);
										}
										else
										{
										}
									}
									catch(Exception e)
									{
										input = xDG.grabObj(objList, objsUsedList, input, 1);
									}
									//!obj level end
									//?tarChar level end
									//!toTar
									//doing something to something end
								}
								else
								{
									
									if(actionType == 4)
									{
										//doing something with something
										//!use level
										input = xDG.grabUse(useLinkingList, useLinkingsUsedList, input, 1);
										//?tarChar level
										input = xDG.grabTarChar(characterList, tarCharacterList, input, 2, doer);
										//!obj level
										try
										{
											if(tarCharacterList.isEmpty())
											{
												input = xDG.grabObj(objList, objsUsedList, input, 1);
											}
											else
											{
											}
										}
										catch(Exception e)
										{
											input = xDG.grabObj(objList, objsUsedList, input, 1);
										}
										//!obj level end
										//?tarChar level end
										//!use level
										//doing something with something end
									}
									else
									{
										if(actionType == 5)
										{
											//doing something
											//doing something end
										}
										else
										{
										}
									}
								}
							}
						}
					}
					else
					{
					}
				try
				{
					if(selectedCommandsList.isEmpty())
					{
					}
					else
					{
						InOutTrans iOT = new InOutTrans();
						
						if(selectedCommandsList.contains("speak") || selectedCommandsList.contains("say"))
						{
							iOT.dialogueTranslate("speak", input, doer);
						}
						else
						{
							if(selectedCommandsList.contains("shout"))
							{
								iOT.dialogueTranslate("shout", input, doer);
							}
							else
							{
								if(selectedCommandsList.contains("whisper"))
								{
									iOT.dialogueTranslate("whisper", input, doer);
								}
								else
								{
									iOT.commandTranslate(selectedCommandsList, tarCharacterList, objsUsedList, toTarLinkingsUsedList, useLinkingsUsedList, input, doer);
								}
							}
						}
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
	}
}
