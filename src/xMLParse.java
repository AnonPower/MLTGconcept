import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class XMLParse {
	int effectsArrayMeasure = 0;

	// Arrays for storing data from target attributes and for data going to.
	ArrayList<Integer> tIntD = new ArrayList<Integer>(),
			tIntEAdd = new ArrayList<Integer>(),
			tIntESub = new ArrayList<Integer>(),
			tEMat = new ArrayList<Integer>(), // List for matching effects with
												// calling data
			tMMat = new ArrayList<Integer>();

	ArrayList<String> tStrD = new ArrayList<String>(),
			tStrE = new ArrayList<String>(),
			tBooD = new ArrayList<String>(), // f==false t==true n==no change
												// (null)
			tBooE = new ArrayList<String>(), // f==false t==true n==no change
												// (null)
			tIntEEqu = new ArrayList<String>(), tID1 = new ArrayList<String>(),
			tID2 = new ArrayList<String>(), tID3 = new ArrayList<String>(),
			tAID = new ArrayList<String>(), tLLID = new ArrayList<String>(),
			tDTLID = new ArrayList<String>(), tCLID = new ArrayList<String>(),
			tSCLID = new ArrayList<String>(), tTagA = new ArrayList<String>(),
			markerID = new ArrayList<String>(),
			markerData = new ArrayList<String>(); // Storage for xml tags

	// Reads in xmls
	public void xMLParser(String dir) throws IOException, InterruptedException {
		xMLArraysWipe();

		String readIn = "lel";

		// counters for certain array element adjustments
		int i = -1;

		File file = new File(dir);
		BufferedReader bis = null;

		if (file.exists()) {
			FileInputStream in = new FileInputStream(dir);
			bis = new BufferedReader(new InputStreamReader(in));
		} else {
			bis = new BufferedReader(new InputStreamReader(getClass()
					.getResourceAsStream(dir)));
		}

		readIn = bis.readLine();
		if (readIn.contains("=") == false && readIn.contains("\"") == false
				&& readIn.contains("</") == false) // list Level
		{
			xMLNextTag(readIn, 0);
			while (readIn.contains(tTagA.get(0)) == false) // loops until readIn
															// = closing tag
			{
				readIn = bis.readLine();
				if (readIn.contains("=") == false
						&& readIn.contains("\"") == false
						&& readIn.contains(tTagA.get(0)) == false) // dataType
																	// Level
				{
					xMLNextTag(readIn, 1);
					while (readIn.contains(tTagA.get(1)) == false) {
						readIn = bis.readLine();
						if (readIn.contains("=") == false
								&& readIn.contains("\"") == false
								&& readIn.contains(tTagA.get(1)) == false
								&& readIn.contains(tTagA.get(0)) == false) // category
																			// Level
						{
							xMLNextTag(readIn, 2);
							while (readIn.contains(tTagA.get(2)) == false) {
								readIn = bis.readLine();
								if (readIn.contains("=") == false
										&& readIn.contains("\"") == false
										&& readIn.contains(tTagA.get(2)) == false
										&& readIn.contains(tTagA.get(1)) == false
										&& readIn.contains(tTagA.get(0)) == false) // subCategory
																					// Level
								{
									xMLNextTag(readIn, 3);
									while (readIn.contains(tTagA.get(3)) == false) {
										readIn = bis.readLine();
										if (readIn.contains("=") == false
												&& readIn.contains("\"") == false
												&& readIn
														.contains(tTagA.get(3)) == false
												&& readIn
														.contains(tTagA.get(2)) == false
												&& readIn
														.contains(tTagA.get(1)) == false
												&& readIn
														.contains(tTagA.get(0)) == false) // iD1
																							// Level
										{
											if (readIn.contains("<markers>")
													&& readIn.contains(tTagA
															.get(3)) == false
													&& readIn.contains(tTagA
															.get(2)) == false
													&& readIn.contains(tTagA
															.get(1)) == false
													&& readIn.contains(tTagA
															.get(0)) == false)// toDo
																				// block
											{
												i = markersParse(dir, readIn,
														i, bis);
											}
											if (readIn.contains("<toDo>")
													&& readIn.contains(tTagA
															.get(3)) == false
													&& readIn.contains(tTagA
															.get(2)) == false
													&& readIn.contains(tTagA
															.get(1)) == false
													&& readIn.contains(tTagA
															.get(0)) == false)// toDo
																				// block
											{
												i = toDoParse(dir, readIn, i,
														bis);
											} else {
												if (readIn
														.contains(tID1.get(0))) {
													xMLNextTag(readIn, 4);
													while (readIn
															.contains(tTagA
																	.get(4)) == false) {
														readIn = bis.readLine();
														if (readIn
																.contains("=") == false
																&& readIn
																		.contains("\"") == false) {
															if (readIn
																	.contains("<markers>")
																	&& readIn
																			.contains(tTagA
																					.get(3)) == false
																	&& readIn
																			.contains(tTagA
																					.get(2)) == false
																	&& readIn
																			.contains(tTagA
																					.get(1)) == false
																	&& readIn
																			.contains(tTagA
																					.get(0)) == false)// toDo
																										// block
															{
																i = markersParse(
																		dir,
																		readIn,
																		i, bis);
															}
															if (readIn
																	.contains("<toDo>")
																	&& readIn
																			.contains(tTagA
																					.get(4)) == false
																	&& readIn
																			.contains(tTagA
																					.get(3)) == false
																	&& readIn
																			.contains(tTagA
																					.get(2)) == false
																	&& readIn
																			.contains(tTagA
																					.get(1)) == false
																	&& readIn
																			.contains(tTagA
																					.get(0)) == false) // iDToDo2
																										// Level
															{
																i = toDoParse(
																		dir,
																		readIn,
																		i, bis);
															} else {
																if (readIn
																		.contains(tID2
																				.get(0))) {
																	xMLNextTag(
																			readIn,
																			5);
																	while (readIn
																			.contains(tTagA
																					.get(5)) == false) {
																		readIn = bis
																				.readLine();
																		if (readIn
																				.contains("=") == false
																				&& readIn
																						.contains("\"") == false) // iD3
																													// Level
																		{
																			if (readIn
																					.contains("<markers>")
																					&& readIn
																							.contains(tTagA
																									.get(3)) == false
																					&& readIn
																							.contains(tTagA
																									.get(2)) == false
																					&& readIn
																							.contains(tTagA
																									.get(1)) == false
																					&& readIn
																							.contains(tTagA
																									.get(0)) == false)// toDo
																														// block
																			{
																				i = markersParse(
																						dir,
																						readIn,
																						i,
																						bis);
																			}
																			if (readIn
																					.contains("<toDo>")
																					&& readIn
																							.contains(tTagA
																									.get(5)) == false
																					&& readIn
																							.contains(tTagA
																									.get(4)) == false
																					&& readIn
																							.contains(tTagA
																									.get(3)) == false
																					&& readIn
																							.contains(tTagA
																									.get(2)) == false
																					&& readIn
																							.contains(tTagA
																									.get(1)) == false
																					&& readIn
																							.contains(tTagA
																									.get(0)) == false) // toDo
																														// block
																			{
																				i = toDoParse(
																						dir,
																						readIn,
																						i,
																						bis);
																			} else {
																				// iD3++
																			}
																		} else {
																			if (readIn
																					.contains("</")
																					|| readIn
																							.contains("_")) {
																			} else {
																				i = tIDParse(
																						readIn,
																						bis,
																						i,
																						5);
																			}
																		}
																	}
																}
															}
														} else {
															if (readIn
																	.contains("</")
																	|| readIn
																			.contains("_")) {
															} else {
																i = tIDParse(
																		readIn,
																		bis, i,
																		4);
															}
														}
													}
												}
											}
										} else {
											if (readIn.contains("</")
													|| readIn.contains("_")) {
											} else {
												i = tIDParse(readIn, bis, i, 3);
											}
										}
									}
								}
							}
						} else {
						}
					}
				} else {
				}
			}
		}
		bis.close();
	}

	public String xMLNextTag(String readInStor, int index) throws IOException {
		String tTag = readInStor;

		tTag = tTag.trim();
		if (tTag.contains("</")) {

		} else {
			tTag = tTag.replace("<", "</");
		}

		try {
			tTagA.set(index, tTag);
		} catch (Exception e) {
			tTagA.add(tTag);
		}

		return tTag;
	}

	public int markersParse(String dir, String readIn, int i, BufferedReader bis)
			throws IOException {
		while (readIn.contains("</markers>") == false) {
			readIn = bis.readLine();
			if (readIn.contains("=") == false && readIn.contains("\"") == false
					|| readIn.contains("</markers>")) // iDToDo1 Level
			{
			} else {
				if (readIn.contains("<") && readIn.contains(">")) {
					readIn = xMLTrim(readIn);

					StringTokenizer st = new StringTokenizer(readIn);

					String tokenStor;

					tokenStor = st.nextToken("\"");

					if (tokenStor.contains("=")) {
						tokenStor = tokenStor.replace("=", "");
					}

					markerID.add(tokenStor);
					markerData.add(st.nextToken("\""));
					tMMat.add(i);
				}
			}
		}
		return i;
	}

	public int toDoParse(String dir, String readIn, int i, BufferedReader bis)
			throws IOException {
		while (readIn.contains("</toDo>") == false) {
			readIn = bis.readLine();
			if (readIn.contains("=") == false && readIn.contains("\"") == false
					&& readIn.contains("_") == false
					|| readIn.contains("</toDo>")) // iDToDo1 Level
			{
			} else {
				if (readIn.contains("<") && readIn.contains(">")) {
					readIn = xMLTrim(readIn);

					StringTokenizer st = new StringTokenizer(readIn);

					if (readIn.contains("+") || readIn.contains("-")) {
						if (readIn.contains("+")
								&& readIn.contains("=") == false) {
							i = grabXMLEffects(st, "+", i);
						} else {
							if (readIn.contains("-")
									&& readIn.contains("=") == false) {
								i = grabXMLEffects(st, "-", i);
							} else {
								if (readIn.contains("-")
										&& readIn.contains("=")) {
									i = grabXMLEffects(st, "-=", i);
								} else {
									if (readIn.contains("+")
											&& readIn.contains("=")) {
										i = grabXMLEffects(st, "+=", i);
									}
								}
							}
						}
					} else {
						if (readIn.contains("$=")) {
							i = grabXMLEffects(st, "$=", i);
						} else {
							if (readIn.contains("?=")) {
								i = grabXMLEffects(st, "?=", i);
							}
						}
					}
				}
			}
		}
		return i;
	}

	public int tIDParse(String readIn, BufferedReader bis, int count,
			int tTagAHi) {
		boolean canProceed = false;

		for (int x = 0; x != tTagAHi; x++) {
			if (readIn.contains(tTagA.get(x))) {
				canProceed = false;
				break;
			} else {
				canProceed = true;
			}
		}
		if (canProceed) {
			if (readIn.contains("<") && readIn.contains(">")) {
				StringTokenizer st = new StringTokenizer(xMLTrim(readIn));
				count = setTIDNum(st, count, tTagAHi);
			}
		}
		return count;
	}

	public int setTIDNum(StringTokenizer st, int count, int tagHi) {
		String strTamp;

		strTamp = st.nextToken("\"");
		if (strTamp.contains("=")) {
			strTamp = strTamp.replace("=", "");
		}
		if (tagHi == 5) {
			tID3.add(strTamp);
			xMLDataGrab(st);
		} else {
			if (tagHi == 4) {
				tID2.add(strTamp);
				xMLDataGrab(st);
			} else {
				if (tagHi == 3) {
					tID1.add(strTamp);
					xMLDataGrab(st);
				}
			}
		}
		count++;
		return count;
	}

	public void xMLDataGrab(StringTokenizer st) {
		if (arrayElementCheck("Integer", 1, tTagA)) {
			tIntD.add(Integer.parseInt(st.nextToken("\""))); // get data
		} else {
			if (arrayElementCheck("String", 1, tTagA)) {
				tStrD.add(st.nextToken("\"")); // get data
			} else {
				if (arrayElementCheck("Boolean", 1, tTagA)) {
					tBooD.add(st.nextToken("\"")); // get data
				}
			}
		}
	}

	public boolean arrayElementCheck(String toCheck, int element,
			ArrayList<String> array) {
		String arrayElemStor = closingXMLTrim(array.get(element));

		boolean doesEqual = false;

		String toCheckAbrv;

		if (toCheck.equalsIgnoreCase(arrayElemStor)) {
			doesEqual = true;
		} else {
			toCheckAbrv = toCheck.substring(0, 2);
			if (toCheckAbrv.equalsIgnoreCase(arrayElemStor)) {
				doesEqual = true;
			}
		}

		return doesEqual;
	}

	public int grabXMLEffects(StringTokenizer st, String token, int i) {
		tLLID.add(st.nextToken("_")); // grab [list] id
		tDTLID.add(st.nextToken("_")); // grab [dataType] id
		tCLID.add(st.nextToken("_")); // grab [category] id
		tSCLID.add(st.nextToken("_")); // grab [subCategory] id
		String strTmp = (st.nextToken("\"")); // grab [iD(1-3)] id
		if (strTmp.contains(token)) {
			strTmp = strTmp.replace(token, "");
		}
		if (strTmp.contains("_")) {
			strTmp = strTmp.replace("_", "");
		}
		tAID.add(strTmp);
		if (token.equals("+")) {
			tIntEAdd.add(Integer.parseInt(st.nextToken("\"")));
		} else {
			tIntEAdd.add(0); // get data
		}
		if (token.equals("-")) {
			tIntESub.add(Integer.parseInt((st.nextToken("\""))) * -1);
		} else {
			tIntESub.add(0);
		}
		if (token.equals("+=")) {
			tIntEEqu.add(st.nextToken("\""));
		} else {
			if (token.equals("-=")) {
				tIntEEqu.add("-" + st.nextToken("\""));
			} else {
				tIntEEqu.add(null);
			}
		}
		if (token.equals("$=")) {
			tStrE.add(st.nextToken("\""));
		} else {
			tStrE.add(null);
		}
		if (token.equals("?=")) {
			tBooE.add(st.nextToken("\""));
		} else {
			tBooE.add(null);
		}
		effectsArrayMeasure++;
		tEMat.add(i);
		return i;
	}

	public String xMLTrim(String readIn) {
		readIn = readIn.trim();
		readIn = readIn.replaceAll("<", "");// kill tags
		readIn = readIn.replaceAll(">", "");

		return readIn;
	}

	public String closingXMLTrim(String readIn) {
		readIn = readIn.trim();
		if (readIn.contains("</")) {
			readIn = readIn.replaceAll("</", "");// kill tags
		} else {
			readIn = readIn.replaceAll("<", "");// kill tags
		}
		readIn = readIn.replaceAll(">", "");

		return readIn;
	}

	public ArrayList<String> getTStrD() {
		return tStrD;
	}

	public ArrayList<Integer> getTIntD() {
		return tIntD;
	}

	public ArrayList<String> getTBooD() {
		return tBooD;
	}

	public ArrayList<String> getTID1() {
		return tID1;
	}

	public ArrayList<String> getTID2() {
		return tID2;
	}

	public ArrayList<String> getTID3() {
		return tID3;
	}

	public ArrayList<Integer> getTEMat() {
		return tEMat;
	}

	public ArrayList<Integer> getTMMat() {
		return tMMat;
	}

	public ArrayList<String> getTLLID() {
		return tLLID;
	}

	public ArrayList<String> getTDTLID() {
		return tDTLID;
	}

	public ArrayList<String> getTCLID() {
		return tCLID;
	}

	public ArrayList<String> getTSCLID() {
		return tSCLID;
	}

	public ArrayList<String> getTAID() {
		return tAID;
	}

	public ArrayList<String> getTStrE() {
		return tStrE;
	}

	public ArrayList<String> getTBooE() {
		return tBooE;
	}

	public ArrayList<String> getTIntEEqu() {
		return tIntEEqu;
	}

	public ArrayList<Integer> getTIntEAdd() {
		return tIntEAdd;
	}

	public ArrayList<Integer> getTIntESub() {
		return tIntESub;
	}

	public ArrayList<String> getMarkerID() {
		return markerID;
	}

	public ArrayList<String> getMarkerData() {
		return markerData;
	}

	public void xMLArraysWipe() {
		// reset arrays
		tIntD.clear();
		tStrD.clear();
		tBooD.clear();
		tIntEEqu.clear();
		tIntEAdd.clear();
		tIntESub.clear();
		tStrE.clear();
		tBooE.clear();
		tID1.clear();
		tID2.clear();
		tID3.clear();
		tAID.clear();
		tLLID.clear();
		tDTLID.clear();
		tCLID.clear();
		tSCLID.clear();
		tEMat.clear();
		markerID.clear();
		markerData.clear();

		// reset array measure
		effectsArrayMeasure = 0;
	}
}