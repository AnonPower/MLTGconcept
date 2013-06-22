import java.io.*;
import java.util.StringTokenizer;


public class XMLWrite
{
	//writes to xmls
	public void xMLWriter(String tLLID, String filePathOfTarget, String tDTLID, String tCLID, String tSCLID, String tAID, String tStrE, String tBooE, String tIntEEqu, int tIntEAdd, int tIntESub) throws IOException
	{
		FileWork fW = new FileWork();
		
		String oldDir = (filePathOfTarget + tLLID),
			   tmpDir = ( "save" + filePathOfTarget + tLLID),
			   readIn = " ",
			   readInStor = null,
			   readInToken = null,
			   tag1C,
			   tag2C,
			   tag3C,
			   tag4C;
			
		String oldData = null,
			   newData = null;
			
		int oldDataConv,
			dataChange;
		
		BufferedReader bis;
		
		File file = new File(tmpDir);
		
		if(file.exists())
		{
			bis = new BufferedReader(new FileReader(tmpDir));
		}
		else
		{
			bis = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(oldDir)));
			new File(tmpDir).mkdirs();
		}
		
		PrintWriter pw = new PrintWriter(tmpDir + "_tmp");
	
		try
		{
			while(readIn.equals(null) == false)
			{
				readIn = bis.readLine();
				try
				{
					if(readIn.equals(null))
					{
					}
					else
					{
						pw.write(readIn + "\n");
					}
					if(readIn.contains("<" + tLLID + ">"))
					{
						tag1C = ("</" + tLLID + ">");
						while(readIn.contains(tag1C) == false)
						{
							readIn = bis.readLine();
							if(readIn.contains(tag1C))
							{
								pw.write(readIn);
							}
							else
							{
								pw.write(readIn + "\n");
							}
							if(readIn.contains("<" + tDTLID + ">"))
							{
								tag2C = ("</" + tDTLID + ">");
								while(readIn.contains(tag2C) == false)
								{
									readIn = bis.readLine();
									pw.write(readIn + "\n");
									if(readIn.contains("<" + tCLID + ">"))
									{
										tag3C = ("</" + tCLID + ">");
										while(readIn.contains(tag3C) == false)
										{
											readIn = bis.readLine();
											pw.write(readIn + "\n");
											if(readIn.contains("<" + tSCLID + ">"))
											{
												tag4C = ("</" + tSCLID + ">");
												while(readIn.contains(tag4C) == false)
												{
													readIn = bis.readLine();
													if(readIn.contains(tAID))
													{
														if(readIn.contains("=") && readIn.contains("\""))
														{
															if(tag2C.contains("Integer") || tag2C.contains("integer") || tag2C.contains("Int") || tag2C.contains("int"))
															{
																try
																{
																	if(tIntEEqu.isEmpty())
																	{
																		try
																		{
																			if(tIntEAdd == 0)
																			{
																				try
																				{
																					if(tIntESub == 0)
																					{
																						
																					}
																					else
																					{
																						try
																						{
																							if(readIn.contains("<") || readIn.contains(">"))
																							{
																								readInStor = readIn.trim();
																								readInStor = readInStor.replace("<", "");
																								readInStor = readInStor.replace(">", "");
																							}
																							StringTokenizer st = new StringTokenizer(readInStor);
																							readInToken = st.nextToken("\"");
																							if(readInToken.endsWith("="))
																							{
																								readInToken = readInToken.replace("=", "");
																							}
																							
																							if(readInToken.equals(tAID))
																							{
																								oldData = st.nextToken("\"");
																								oldDataConv = Integer.parseInt(oldData);
																								dataChange = tIntESub;
																								newData = String.valueOf(oldDataConv + dataChange);
																								
																								try
																								{
																									readIn = readIn.replace("\"" + oldData + "\"", "\"" + newData + "\"");
																								}
																								catch(Exception e1)
																								{
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
																			else
																			{
																				if(readIn.contains("<") || readIn.contains(">"))
																				{
																					readInStor = readIn.trim();
																					readInStor = readInStor.replace("<", "");
																					readInStor = readInStor.replace(">", "");
																				}
																				StringTokenizer st = new StringTokenizer(readInStor);
																				readInToken = st.nextToken("\"");
																				if(readInToken.endsWith("="))
																				{
																					readInToken = readInToken.replace("=", "");
																				}
																				
																				if(readInToken.equals(tAID))
																				{
																					oldData = st.nextToken("\"");
																					oldDataConv = Integer.parseInt(oldData);
																					dataChange = tIntEAdd;
																					newData = String.valueOf(oldDataConv + dataChange);
																					
																					try
																					{
																						readIn = readIn.replace("\"" + oldData + "\"", "\"" + newData + "\"");
																					}
																					catch(Exception e)
																					{
																					}
																				}
																			}
																		}
																		catch(Exception e)
																		{
																		}
																	}
																	else
																	{
																		if(readIn.contains("<") || readIn.contains(">"))
																		{
																			readInStor = readIn.trim();
																			readInStor = readInStor.replace("<", "");
																			readInStor = readInStor.replace(">", "");
																		}
																		StringTokenizer st = new StringTokenizer(readInStor);
																		readInToken = st.nextToken("\"");
																		if(readInToken.endsWith("="))
																		{
																			readInToken = readInToken.replace("=", "");
																		}
																		
																		if(readInToken.equals(tAID))
																		{
																			oldData = st.nextToken("\"");
																			oldDataConv = Integer.parseInt(oldData);
																			dataChange = Integer.parseInt(tIntEEqu);
																			newData = String.valueOf(dataChange);
																			
																			try
																			{
																				readIn = readIn.replace("\"" + oldData + "\"", "\"" + newData + "\"");
																			}
																			catch(Exception e)
																			{
																			}
																		}
																	}
																}
																catch(Exception e)
																{
																	try
																	{
																		if(tIntEAdd == 0)
																		{
																			try
																			{
																				if(tIntESub == 0)
																				{
																					
																				}
																				else
																				{
																					try
																					{
																						if(readIn.contains("<") || readIn.contains(">"))
																						{
																							readInStor = readIn.trim();
																							readInStor = readInStor.replace("<", "");
																							readInStor = readInStor.replace(">", "");
																						}
																						StringTokenizer st = new StringTokenizer(readInStor);
																						readInToken = st.nextToken("\"");
																						if(readInToken.endsWith("="))
																						{
																							readInToken = readInToken.replace("=", "");
																						}
																						
																						if(readInToken.equals(tAID))
																						{
																							oldData = st.nextToken("\"");
																							oldDataConv = Integer.parseInt(oldData);
																							dataChange = tIntESub;
																							newData = String.valueOf(oldDataConv + dataChange);
																							
																							try
																							{
																								readIn = readIn.replace("\"" + oldData + "\"", "\"" + newData + "\"");
																							}
																							catch(Exception e1)
																							{
																							}
																						}
																					}
																					catch(Exception e1)
																					{
																					}
																				}
																			}
																			catch(Exception e1)
																			{
																			}
																		}
																		else
																		{
																			if(readIn.contains("<") || readIn.contains(">"))
																			{
																				readInStor = readIn.trim();
																				readInStor = readInStor.replace("<", "");
																				readInStor = readInStor.replace(">", "");
																			}
																			StringTokenizer st = new StringTokenizer(readInStor);
																			readInToken = st.nextToken("\"");
																			if(readInToken.endsWith("="))
																			{
																				readInToken = readInToken.replace("=", "");
																			}
																			
																			if(readInToken.equals(tAID))
																			{
																				oldData = st.nextToken("\"");
																				oldDataConv = Integer.parseInt(oldData);
																				dataChange = tIntEAdd;
																				newData = String.valueOf(oldDataConv + dataChange);
																				
																				try
																				{
																					readIn = readIn.replace("\"" + oldData + "\"", "\"" + newData + "\"");
																				}
																				catch(Exception e1)
																				{
																				}
																			}
																		}
																	}
																	catch(Exception e1)
																	{
																		try
																		{
																			if(tIntESub == 0)
																			{
																				
																			}
																			else
																			{
																				try
																				{
																					if(readIn.contains("<") || readIn.contains(">"))
																					{
																						readInStor = readIn.trim();
																						readInStor = readInStor.replace("<", "");
																						readInStor = readInStor.replace(">", "");
																					}
																					StringTokenizer st = new StringTokenizer(readInStor);
																					readInToken = st.nextToken("\"");
																					if(readInToken.endsWith("="))
																					{
																						readInToken = readInToken.replace("=", "");
																					}
																					
																					if(readInToken.equals(tAID))
																					{
																						oldData = st.nextToken("\"");
																						oldDataConv = Integer.parseInt(oldData);
																						dataChange = tIntESub;
																						newData = String.valueOf(oldDataConv + dataChange);
																						
																						try
																						{
																							readIn = readIn.replace("\"" + oldData + "\"", "\"" + newData + "\"");
																						}
																						catch(Exception e11)
																						{
																						}
																					}
																				}
																				catch(Exception e11)
																				{
																				}
																			}
																		}
																		catch(Exception e11)
																		{
																		}
																	}
																}
															}
															else
															{
																if(tag2C.contains("String") || tag2C.contains("string") || tag2C.contains("Str") || tag2C.contains("str"))
																{
																	try
																	{
																		if(tStrE.isEmpty())
																		{
																		}
																		else
																		{
																			if(readIn.contains("<") || readIn.contains(">"))
																			{
																				readInStor = readIn.trim();
																				readInStor = readInStor.replace("<", "");
																				readInStor = readInStor.replace(">", "");
																			}
																			StringTokenizer st = new StringTokenizer(readInStor);
																			readInToken = st.nextToken("\"");
																			if(readInToken.endsWith("="))
																			{
																				readInToken = readInToken.replace("=", "");
																			}
																			
																			if(readInToken.equals(tAID))
																			{
																				oldData = st.nextToken("\"");
																				
																				try
																				{
																					readIn = readIn.replace("\"" + oldData + "\"", "\"" + tStrE + "\"");
																				}
																				catch(Exception e1)
																				{
																				}
																			}
																		}
																	}
																	catch(Exception e)
																	{
																	}
																}
																else
																{
																	if(tag2C.contains("Boolean") || tag2C.contains("boolean") || tag2C.contains("Boo") || tag2C.contains("boo"))
																	{
																		try
																		{
																			if(tBooE.isEmpty())
																			{
																			}
																			else
																			{
																				if(readIn.contains("<") || readIn.contains(">"))
																				{
																					readInStor = readIn.trim();
																					readInStor = readInStor.replace("<", "");
																					readInStor = readInStor.replace(">", "");
																				}
																				StringTokenizer st = new StringTokenizer(readInStor);
																				readInToken = st.nextToken("\"");
																				if(readInToken.endsWith("="))
																				{
																					readInToken = readInToken.replace("=", "");
																				}
																				
																				if(readInToken.equals(tAID))
																				{
																					oldData = st.nextToken("\"");
																					
																					try
																					{
																						readIn = readIn.replace("\"" + oldData + "\"", "\"" + tBooE + "\"");
																					}
																					catch(Exception e1)
																					{
																					}
																				}
																			}
																		}
																		catch(Exception e)
																		{
																		}
																	}
																}
															}
														}
													}
													pw.write(readIn + "\n");
												}
											}
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
				pw.close();
			}
		}
		catch(Exception e)
		{
		}
		bis.close();
		fW.fileOverwrite(tmpDir, tmpDir + "_tmp");
	}
}
