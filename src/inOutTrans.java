import java.io.*;
import java.util.ArrayList;

public class inOutTrans
{	
	boolean isSpeak = false;	//for parameter determining whether or not is whisper dialogue
	
	public void commandTranslate(ArrayList<String> commandIn, ArrayList<String> charsIn, ArrayList<String> objIn, ArrayList<String> toLinkingIn, ArrayList<String> useLinkingIn, String input, String doer) throws IOException, InterruptedException
	{	
		inputCollect iC = new inputCollect();
		buildOut bO = new buildOut();
		
		String output = "\n";
		
		int hiCount = 0;
		
		boolean isDialogue = false,
				isInvalidOutput = false;
		
		ArrayList<Integer> lASF = new ArrayList<Integer>();
		
		lASF.add(commandIn.size());
		lASF.add(charsIn.size());
		lASF.add(objIn.size());
		lASF.add(toLinkingIn.size());
		lASF.add(useLinkingIn.size());
		
		for(int i = 0; i < lASF.size(); i++)
		{
			if(lASF.get(i) > hiCount)
			{
				hiCount = lASF.get(i);
			}
		}
		if(commandIn.contains("quit"))
		{
			System.exit(0);
		}
		else
		{
			if(commandIn.contains("speak") || commandIn.contains("shout") || commandIn.contains("whisper") || commandIn.contains("say"))
			{
				isDialogue = true;
				for(int x = 0; x < commandIn.size(); x++)
				{
					if(commandIn.get(x).equals("speak") || commandIn.get(x).equals("shout") || commandIn.get(x).equals("whisper") || commandIn.get(x).equals("say"))
					{
						iC.setDialogue(commandIn.get(x));
					}
				}
			}
			else
			{
				try
				{
					if(toLinkingIn.isEmpty())
					{
						try
						{
							if(useLinkingIn.isEmpty())
							{
								try
								{
									if(objIn.isEmpty())
									{
										try
										{
											if(charsIn.isEmpty())
											{
												//command only
												//print command
												output = bO.buildCommOut(commandIn, output, input, doer);
												//end print command
											}
											else
											{
												//command + char = command effects char
												
												//print command
												output = bO.buildCommOut(commandIn, output, input, doer);
												//end print command
												
												//print char
												output = bO.buildCharOut(charsIn, output, input, doer);
												//end print char
											}
										}
										catch(Exception e)
										{
											//command only
											//print command
											output = bO.buildCommOut(commandIn, output, input, doer);
											//end print command
										}
									}
									else
									{
										if(charsIn.isEmpty())
										{
											//command + obj = command effects object
											
											//print command
											output = bO.buildCommOut(commandIn, output, input, doer);
											//end print command
											
											//print obj
											output = bO.buildObjOut(objIn, output, input);
											//end print obj
										}
										else
										{
											//command + (char + obj) = bigmacnope.wav
											isInvalidOutput = true;
										}
									}
								}
								catch(Exception e)
								{
									try
									{
										if(charsIn.isEmpty())
										{
											//command only
											//print command
											output = bO.buildCommOut(commandIn, output, input, doer);
											//end print command
										}
										else
										{
											//command + char = command effects char
											
											//print command
											output = bO.buildCommOut(commandIn, output, input, doer);
											//end print command
											
											//print char
											output = bO.buildCharOut(charsIn, output, input, doer);
											//end print char
										}
									}
									catch(Exception e11)
									{
										//command only
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
									}
								}
							}
							else
							{
								if(objIn.isEmpty())
								{
									if(charsIn.isEmpty())
									{
										//command + useLinking = command only
										
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
									}
									else
									{
										//command + useLinking + char = command effects char (for now, useLinking ignored, special exceptions to be included, i.e. fuck with twilight sparkle)
										
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
										
										//print char
										output = bO.buildCharOut(charsIn, output, input, doer);
										//end print char
									}
								}
								else
								{
									if(charsIn.isEmpty())
									{
										//command + useLinking + obj = player uses obj with command
										
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
										
										//print use
										output = bO.buildUseOut(useLinkingIn, output, input);
										//end print use
										
										//print obj
										output = bO.buildObjOut(objIn, output, input);
										//end print obj
									}
									else
									{
										//command + char + useLinking + obj = player uses obj with command on char
										
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
										
										//print char
										output = bO.buildCharOut(charsIn, output, input, doer);
										//end print char
										
										//print use
										output = bO.buildUseOut(useLinkingIn, output, input);
										//end print use
										
										//print obj
										output = bO.buildObjOut(objIn, output, input);
										//end print obj
									}
								}
							}
						}
						catch(Exception e)
						{
							try
							{
								if(objIn.isEmpty())
								{
									try
									{
										if(charsIn.isEmpty())
										{
											//command only
											//print command
											output = bO.buildCommOut(commandIn, output, input, doer);
											//end print command
										}
										else
										{
											//command + char = command effects char
											
											//print command
											output = bO.buildCommOut(commandIn, output, input, doer);
											//end print command
											
											//print char
											output = bO.buildCharOut(charsIn, output, input, doer);
											//end print char
										}
									}
									catch(Exception e1)
									{
										//command only
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
									}
								}
								else
								{
									if(charsIn.isEmpty())
									{
										//command + obj = command effects object
										
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
										
										//print obj
										output = bO.buildObjOut(objIn, output, input);
										//end print obj
									}
									else
									{
										//command + (char + obj) = bigmacnope.wav (for now, it is error, will be, which comes first)
										
										//print Computer says, "No."
										isInvalidOutput = true;
									}
								}
							}
							catch(Exception e1)
							{
								try
								{
									if(charsIn.isEmpty())
									{
										//command only
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
									}
									else
									{
										//command + char = command effects char
										
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
										
										//print char
										output = bO.buildCharOut(charsIn, output, input, doer);
										//end print char
									}
								}
								catch(Exception e11)
								{
									//command only
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
								}
							}
						}
					}
					else
					{
						if(useLinkingIn.isEmpty())
						{
							if(objIn.isEmpty())
							{
								if(charsIn.isEmpty())
								{
									//command + toTarLinking = command only
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
								}
								else
								{
									//command + toTarLinking + char = command effects char
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
									
									//print toTar
									output += (" at");
									//end print toTar
									
									//print char
									output = bO.buildCharOut(charsIn, output, input, doer);
									//end print char
								}
							}
							else
							{
								if(charsIn.isEmpty())
								{
									//command + toTarLinking + obj = command effects obj
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
									
									//print toTar
									output += (" at");
									//end print toTar
									
									//print obj
									output = bO.buildObjOut(objIn, output, input);
									//end print obj
								}
								else
								{
									//command + obj + toTarLinking + char = command with obj to char (To be revises with special exceptions i.e. push twilight sparkle into wall)
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
									
									//print obj
									output = bO.buildObjOut(objIn, output, input);
									//end print obj
									
									//print toTar
									output = bO.buildToTarOut(toLinkingIn, output, input);
									//end print toTar
									
									//print char
									output = bO.buildCharOut(charsIn, output, input, doer);
									//end print char
								}
							}
						}
						else
						{
							if(objIn.isEmpty())
							{
								if(charsIn.isEmpty())
								{
									//command + useLinking + toTarLinking = command only
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
								}
								else
								{
									//command + useLinking (+ char) + toTarLinking (+ char) = command on char (multiple char exception to be made, ie push twilight sparkle into rarity)
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
									
									//print char
									output = bO.buildCharOut(charsIn, output, input, doer);
									//end print char
								}
							}
							else
							{
								if(charsIn.isEmpty())
								{
									//command + useLinking (+ obj) + toTarLinking (+ obj) = command on obj (multiple obj exception to be made, ie hit wall with sword)
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
									
									//print obj
									output = bO.buildObjOut(objIn, output, input);
									//end print obj
								}
								else
								{
									//command + useLinking + obj + toTarLinking + char = command using obj on char
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
									
									//print toTar
									output = bO.buildToTarOut(toLinkingIn, output, input);
									//end print toTar
									
									//print char
									output = bO.buildCharOut(charsIn, output, input, doer);
									//end print char
									
									//print useLinking
									output = bO.buildUseOut(useLinkingIn, output, input);
									//end print useLinking
									
									//print obj
									output = bO.buildObjOut(objIn, output, input);
									//end print obj
								}
							}
						}
					}
				}
				catch(Exception e)
				{
					try
					{
						if(useLinkingIn.isEmpty())
						{
							try
							{
								if(objIn.isEmpty())
								{
									try
									{
										if(charsIn.isEmpty())
										{
											//command only
											//print command
											output = bO.buildCommOut(commandIn, output, input, doer);
											//end print command
										}
										else
										{
											//command + char = command effects char
											
											//print command
											output = bO.buildCommOut(commandIn, output, input, doer);
											//end print command
											
											//print char
											output = bO.buildCharOut(charsIn, output, input, doer);
											//end print char
										}
									}
									catch(Exception e1)
									{
										//command only
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
									}
								}
								else
								{
									if(charsIn.isEmpty())
									{
										//command + obj = command effects object
										
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
										
										//print obj
										output = bO.buildObjOut(objIn, output, input);
										//end print obj
									}
									else
									{
										//command + (char + obj) = bigmacnope.wav (for now, it is error, will be, which comes first)
										
										//print Computer says, "No."
										isInvalidOutput = true;
									}
								}
							}
							catch(Exception e1)
							{
								try
								{
									if(charsIn.isEmpty())
									{
										//command only
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
									}
									else
									{
										//command + char = command effects char
										
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
										
										//print char
										output = bO.buildCharOut(charsIn, output, input, doer);
										//end print char
									}
								}
								catch(Exception e11)
								{
									//command only
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
								}
							}
						}
						else
						{
							if(objIn.isEmpty())
							{
								if(charsIn.isEmpty())
								{
									//command + useLinking = command only
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
								}
								else
								{
									//command + useLinking + char = command effects char (for now, useLinking ignored, special exceptions to be included, i.e. fuck with twilight sparkle)
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
									
									//print char
									output = bO.buildCharOut(charsIn, output, input, doer);
									//end print char
								}
							}
							else
							{
								if(charsIn.isEmpty())
								{
									//command + useLinking + obj = player uses obj with command
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
									
									//print use
									output = bO.buildUseOut(useLinkingIn, output, input);
									//end print use
									
									//print obj
									output = bO.buildObjOut(objIn, output, input);
									//end print obj
								}
								else
								{
									//command + char + useLinking + obj = player uses obj with command on char
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
									
									//print char
									output = bO.buildCharOut(charsIn, output, input, doer);
									//end print char
									
									//print use
									output = bO.buildUseOut(useLinkingIn, output, input);
									//end print use
									
									//print obj
									output = bO.buildObjOut(objIn, output, input);
									//end print obj
								}
							}
						}
					}
					catch(Exception e11)
					{
						try
						{
							if(objIn.isEmpty())
							{
								try
								{
									if(charsIn.isEmpty())
									{
										//command only
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
									}
									else
									{
										//command + char = command effects char
										
										//print command
										output = bO.buildCommOut(commandIn, output, input, doer);
										//end print command
										
										//print char
										output = bO.buildCharOut(charsIn, output, input, doer);
										//end print char
									}
								}
								catch(Exception e1)
								{
									//command only
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
								}
							}
							else
							{
								if(charsIn.isEmpty())
								{
									//command + obj = command effects object
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
									
									//print obj
									output = bO.buildObjOut(objIn, output, input);
									//end print obj
								}
								else
								{
									//command + (char + obj) = bigmacnope.wav (for now, it is error, will be, which comes first)
									
									//print Computer says, "No."
									isInvalidOutput = true;
								}
							}
						}
						catch(Exception e1)
						{
							try
							{
								if(charsIn.isEmpty())
								{
									//command only
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
								}
								else
								{
									//command + char = command effects char
									
									//print command
									output = bO.buildCommOut(commandIn, output, input, doer);
									//end print command
									
									//print char
									output = bO.buildCharOut(charsIn, output, input, doer);
									//end print char
								}
							}
							catch(Exception e111)
							{
								//command only
								//print command
								output = bO.buildCommOut(commandIn, output, input, doer);
								//end print command
							}
						}
					}
				}
			}
		}
		if(isDialogue)
		{
		}
		else
		{
			if(isInvalidOutput)
			{
				if(doer.equals("player"))
				{
					System.out.print("\nThat was an invalid command.[temporary]\n");
				}
			}
			else
			{
				effectsOut eO = new effectsOut();
				
				bO.saveOutput(output);
				eO.effectsOutInit(output, charsIn, toLinkingIn);
				System.out.print(output + ".\n");
			}
		}
	}
	
	public void dialogueTranslate(String typeIn, String dInput, String doer) throws InterruptedException
	{
		buildOut bO = new buildOut();
		
		String dInputEdit;
		
		dInput = dInput.trim();
		dInputEdit = (String.valueOf(dInput.charAt(0))).toUpperCase();
		dInput = dInput.replaceFirst(String.valueOf(dInput.charAt(0)), dInputEdit); //capitalize
		
		if(doer == "player")
		{
			if(typeIn.equals("speak") || typeIn.endsWith("say"))
			{
				dInput = dInput.trim();
				bO.saveOutput("\n" + "You say, \"" + dInput + ".\"\n");
				System.out.print("\n" + "You say, \"" + dInput + ".\"\n");
			}
			else
			{
				if(typeIn.equals("shout"))
				{
					dInput = dInput.trim();
					bO.saveOutput("\n" + "You shout, \"" + dInput + "!\"\n");
					System.out.print("\n" + "You shout, \"" + dInput + "!\"\n");
				}
				else
				{
					if(typeIn.equals("whisper"))
					{
						dInput = dInput.trim();
						bO.saveOutput("\n" + "You whisper, \"" + dInput + "...\"\n");
						System.out.print("\n" + "You whisper, \"" + dInput + "...\"\n");
					}
				}
			}
		}
		else
		{
			if(typeIn.equals("speak") || typeIn.equals("say"))
			{
				dInput = dInput.replace(doer + " speak", "");
				dInput = dInput.replace(doer + " say", "");
				if(dInput.endsWith(" ") || dInput.startsWith(" "))
				{
					dInput = dInput.trim();
				}
				if(dInput.isEmpty() == false)
				{
					bO.saveOutput("\n" + doer + " says, \"" + dInput + ".\"\n");
					System.out.print("\n" + doer + " says, \"" + dInput + ".\"\n");
				}
			}
			else
			{
				if(typeIn.equals("shout"))
				{
					dInput = dInput.replace(doer + " shout", "");
					if(dInput.endsWith(" ") || dInput.startsWith(" "))
					{
						dInput = dInput.trim();
					}
					if(dInput.isEmpty() == false)
					{
						bO.saveOutput("\n" + doer + " shouts, \"" + dInput + "!\"\n");
						System.out.print("\n" + doer + " shouts, \"" + dInput + "!\"\n");
					}
				}
				else
				{
					if(typeIn.equals("whisper"))
					{
						dInput = dInput.replace(doer + " whisper", "");
						if(dInput.endsWith(" ") || dInput.startsWith(" "))
						{
							dInput = dInput.trim();
						}
						if(dInput.isEmpty() == false)
						{
							bO.saveOutput("\n" + doer + " whispers, \"" + dInput + "...\"\n");
							System.out.print("\n" + doer + " whispers, \"" + dInput + "...\"\n");
						}
					}
				}
			}
		}
	}
}