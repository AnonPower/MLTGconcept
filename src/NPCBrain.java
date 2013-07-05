import java.io.IOException;
import java.util.ArrayList;

public class NPCBrain {
	String focusID = null;
	int focusIntData = -1;

	Attributes attributeClass = new Attributes();
	// random AI process
	// to be revised to more procedural based on various parameters
	public void setNPCCommand() throws IOException, InterruptedException {
		XMLParse xP = new XMLParse();
		StringMod sM = new StringMod();
		RanGen rG = new RanGen();
		Relations rel = new Relations();
		ToLinkingWork tLW = new ToLinkingWork();

		ArrayList<String> selectedCommandsList = new ArrayList<String>(),
				tarCharacterList = new ArrayList<String>(),
				objsUsedList = new ArrayList<String>(),
				useLinkingsUsedList = new ArrayList<String>(),
				toTarLinkingsUsedList = new ArrayList<String>();

		ArrayList<String> toRemove = new ArrayList<String>();

		String doer = null, input = "";

		xP.xMLParser("defaults/MLTG/invXMLs/characters");
		ArrayList<String> characterList = new ArrayList<String>(xP.getTStrD());
		toRemove.add("me");
		toRemove.add("myself");
		toRemove.add("self");
		characterList = sM.stringArrayCutter(characterList, toRemove);

		xP.xMLParser("defaults/MLTG/invXMLs/commands");
		ArrayList<String> commandList = new ArrayList<String>(xP.getTStrD());
		toRemove.add("system.clear");
		toRemove.add("system.quit");
		toRemove.add("what is here");
		toRemove.add("who is here");
		toRemove.add("player.change name");
		toRemove.add("system.play.song");
		toRemove.add("system.stop.song");
		commandList = sM.stringArrayCutter(commandList, toRemove);
		toRemove.clear();

		xP.xMLParser("defaults/MLTG/invXMLs/objects");
		ArrayList<String> objList = new ArrayList<String>(xP.getTStrD());

		xP.xMLParser("defaults/MLTG/invXMLs/useLinking");
		ArrayList<String> useLinkingList = new ArrayList<String>(xP.getTStrD());

		for (int x = 0; x < characterList.size(); x++) {
			input = "";

			// reset Arrays for each doer
			selectedCommandsList.clear();
			tarCharacterList.clear();
			objsUsedList.clear();
			toTarLinkingsUsedList.clear();
			useLinkingsUsedList.clear();

			ArrayList<String> commandType = null;
			ArrayList<String> commandType2 = null;
			ArrayList<String> useableCommandsList = new ArrayList<String>();

			ArrayList<String> toTarLinkingList = null;
			ArrayList<String> toTarLinkingList2 = null;

			try {
				if (characterList.get(x).equalsIgnoreCase("player")) {
				} else {
					// !Who is doing?
					// Goes through list of characters
					doer = characterList.get(x);
					input = doer;
					// end !Who is doing?

					if (attributeClass.getFocusIntData() >= 2000) {
						attributeClass.setFocusData(1999);
					}

					if (attributeClass.getFocusIntData() < 2000) {
						// !What are they doing?
						// Commands fetched based on why they are doing.
						xP.xMLParser("/invXMLs/commands");

						// ?Why are they doing?
						// For now determined by happiness
						attributeClass.findCharacterHappiness(doer);

						for (int y = 0; y < xP.tEMat.size(); y++) {
							if (xP.getTAID().get(y)
									.equalsIgnoreCase("happiness")) {
								useableCommandsList.add(commandList
										.get(xP.tEMat.get(y)));
							}
						}
						// end ?Why are they doing?

						rG.setRanNum(useableCommandsList.size());
						input += " " + useableCommandsList.get(rG.getRanNum());
						selectedCommandsList.add(useableCommandsList.get(rG
								.getRanNum()));
						// end !What are they doing?

						// get toLinking for commands
						for (int y = 0; y < selectedCommandsList.size(); y++) {
							try {
								if (selectedCommandsList.get(y).equals(null)) {
									break;
								} else {
									if (y > 0) {
										toTarLinkingList2 = new ArrayList<String>(
												tLW.getToLinkingForCommand(selectedCommandsList
														.get(y)));
										if (toTarLinkingList.contains("false")) {
										} else {
											commandType2 = new ArrayList<String>(
													tLW.getCommandType(selectedCommandsList
															.get(y)));
										}
									} else {
										toTarLinkingList = new ArrayList<String>(
												tLW.getToLinkingForCommand(selectedCommandsList
														.get(y)));
										if (toTarLinkingList.contains("false")) {
										} else {
											commandType = new ArrayList<String>(
													tLW.getCommandType(selectedCommandsList
															.get(y)));
										}
									}
								}
							} catch (NullPointerException npe) {
								break;
							}
						}
						// end get toLinking for commands
						// !Who to?[to include or what]
						// Determined by relations doer has, who is available
						// and [desire to improve relations, COMPLEX, TO BE
						// IMPLEMENTED]
						rel.fetchRelations(doer);
						int highestRelation = -999999999;
						boolean relationFound = false;
						String targetOfCommand = null;
						for (int y = 0; y < rel.getCharacterRelationIDList()
								.size(); y++) {
							if (rel.getCharacterRelationIntDataList().get(y) > highestRelation) {
								highestRelation = rel
										.getCharacterRelationIntDataList().get(
												y);
								relationFound = true;
							}
						}
						if (relationFound) {
							for (int y = 0; y < rel
									.getCharacterRelationIDList().size(); y++) {
								if (rel.getCharacterRelationIntDataList()
										.get(y) == highestRelation) {
									input += " "
											+ rel.getCharacterRelationIDList()
													.get(y);
									tarCharacterList.add(rel
											.getCharacterRelationIDList()
											.get(y));
									targetOfCommand = rel
											.getCharacterRelationIDList()
											.get(y);
								}
							}
						} else {
							input += " " + doer;
							tarCharacterList.add(doer);
						}
						// end !Who to?

						try {
							if (selectedCommandsList.isEmpty()
									|| toTarLinkingList.contains("false2")) {
							} else {
								// ?Are we doing the command with something?
								// Determined by, will anything available help?
								xP.xMLParser("defaults/MLTG/invXMLs/objects");

								try {
									if (commandType.contains("effective")
											|| commandType.contains("kinetic")) {
										rG.setRanNum(objList.size());
										input += " "
												+ objList.get(rG.getRanNum());
										objsUsedList.add(objList.get(rG
												.getRanNum()));
									}
								} catch (NullPointerException npe) {
								}
								if (toTarLinkingList.contains("false")) {
									rG.setRanNum(objList.size() * 2);
									try {
										if (objList.get(rG.getRanNum()).equals(
												null)) {
										} else {
											input += " "
													+ objList.get(rG
															.getRanNum());
											objsUsedList.add(objList.get(rG
													.getRanNum()));
										}
									} catch (NullPointerException npe) {
									}
								}
								try {
									if (objsUsedList.isEmpty()
											|| objsUsedList.get(0).equals(null)) {
									} else {
										// ?Do we need use linking?
										// Determined by command type
										try {
											if (toTarLinkingList.equals(null)) {
												rG.setRanNum(useLinkingList
														.size());
												input += " "
														+ useLinkingList.get(rG
																.getRanNum());
												useLinkingsUsedList
														.add(useLinkingList.get(rG
																.getRanNum()));
											} else {
												if (commandType
														.contains("effective")) {
													rG.setRanNum(useLinkingList
															.size());
													input += " "
															+ useLinkingList
																	.get(rG.getRanNum());
													useLinkingsUsedList
															.add(useLinkingList.get(rG
																	.getRanNum()));
												}
											}
										} catch (Exception e1) {
											rG.setRanNum(useLinkingList.size());
											input += " "
													+ useLinkingList.get(rG
															.getRanNum());
											useLinkingsUsedList
													.add(useLinkingList.get(rG
															.getRanNum()));
										}
										// end ?Do we need use linking?
										if (objsUsedList.size() == 1) {
											input += " " + objsUsedList.get(0);
										} else {
											rG.setRanNum(objsUsedList.size());
											input += " "
													+ objsUsedList.get(rG
															.getRanNum());
											int y = 0;
											do {
												try {
													if (objsUsedList
															.get(y)
															.equals(objsUsedList.get(rG
																	.getRanNum()))) {
													} else {
														objsUsedList.remove(y);
													}
												} catch (Exception e) {
												}
												y++;
											} while (objsUsedList.get(y)
													.equals(null) == false);
										}
									}
								} catch (Exception e) {
								}
								// end ?Are we doing the command with something?

								// !toLinking!
								// Determined by... ?
								try {
									if (targetOfCommand.equals(null)) {
									} else {
										String toLinkingUsed = null, toLinkingUsed2 = null;

										toLinkingUsed = tLW.setToLinkingToUse(
												commandType, toTarLinkingList);
										try {
											if (toLinkingUsed.equals(null)) {
											} else {
												toTarLinkingsUsedList
														.add(toLinkingUsed);
											}
										} catch (NullPointerException npe) {
										}
										if (selectedCommandsList.size() > 1) {
											toLinkingUsed2 = tLW
													.setToLinkingToUse(
															commandType2,
															toTarLinkingList2);
											try {
												if (toLinkingUsed2.equals(null)) {
												} else {
													toTarLinkingsUsedList
															.add(toLinkingUsed2);
												}
											} catch (NullPointerException npe) {
											}
										}

										try {
											if (toLinkingUsed.equals(null)) {
											} else {
												input = input
														.replace(
																targetOfCommand,
																toLinkingUsed
																		+ " "
																		+ targetOfCommand);
												if (useLinkingsUsedList
														.isEmpty()) {
													input = input.replace(" "
															+ toLinkingUsed
															+ " "
															+ targetOfCommand,
															"");
													input += " "
															+ toLinkingUsed
															+ " "
															+ targetOfCommand;
												} else {
												}
											}
										} catch (NullPointerException npe) {
										}
									}
								} catch (NullPointerException npe) {
								}
								// end !toLinking!
							}
						} catch (Exception e) {
						}
					} else {
					}

					try {
						if (selectedCommandsList.isEmpty()) {
						} else {
							InOutTrans iOT = new InOutTrans();

							if (selectedCommandsList.contains("speak")
									|| selectedCommandsList.contains("say")) {
								iOT.dialogueTranslate("speak", input, doer);
							} else {
								if (selectedCommandsList.contains("shout")) {
									iOT.dialogueTranslate("shout", input, doer);
								} else {
									if (selectedCommandsList
											.contains("whisper")) {
										iOT.dialogueTranslate("whisper", input,
												doer);
									} else {
										iOT.commandTranslate(
												selectedCommandsList,
												tarCharacterList, objsUsedList,
												toTarLinkingsUsedList,
												useLinkingsUsedList, input,
												doer);
									}
								}
							}
						}
					} catch (Exception e) {
					}
				}
			} catch (Exception e) {
			}
		}
	}
}
