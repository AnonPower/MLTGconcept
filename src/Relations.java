import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Relations {
	ArrayList<String> characterRelationIDList = new ArrayList<String>();

	ArrayList<Integer> characterRelationIntDataList = new ArrayList<Integer>();

	public void fetchRelations(String relationsOf) throws IOException,
			InterruptedException {
		clearRelationArrays();

		StringMod sM = new StringMod();
		XMLParse xP = new XMLParse();

		String relationsOfPath = "save/charXMLs/relationXMLs/"
				+ sM.stringToEffectsTarget(relationsOf) + "_relations";
		String relationsDirectory = "save/charXMLs/relationXMLs";

		File directory = new File(relationsDirectory);

		if (directory.exists()) {
		} else {
			directory.mkdirs();
		}

		File file = new File(relationsOfPath);

		try {
			if (file.exists()) {
				xP.xMLParser(relationsOfPath);

				for (int x = 0; x < xP.getTID1().size(); x++) {
					try {
						if (xP.getTID1().get(x).isEmpty()) {
						} else {
							try {
								if (xP.getTIntD().get(x).toString().isEmpty()) {
								} else {
									characterRelationIDList.add(xP.getTID1()
											.get(x));
									characterRelationIntDataList.add(xP
											.getTIntD().get(x));
								}
							} catch (Exception e) {
							}
						}
					} catch (Exception e) {
					}
				}
			} else {
				constructFileForRelationOf(file,
						sM.stringToEffectsTarget(relationsOf));
			}
		} catch (Exception e) {
			constructFileForRelationOf(file,
					sM.stringToEffectsTarget(relationsOf));
		}
	}

	public ArrayList<String> getCharacterRelationIDList() {
		return characterRelationIDList;
	}

	public ArrayList<Integer> getCharacterRelationIntDataList() {
		return characterRelationIntDataList;
	}

	public void constructFileForRelationOf(File relationFileToMake,
			String relationsOfFormatted) throws IOException {
		String readIn;

		File directory = new File("save/charXMLs/relationXMLs");

		if (directory.exists()) {
		} else {
			directory.mkdirs();
		}

		relationFileToMake.createNewFile();

		PrintWriter pW = new PrintWriter(relationFileToMake);

		BufferedReader bis = new BufferedReader(new InputStreamReader(this
				.getClass().getResourceAsStream("invXMLs/_relations")));
		do {
			try {
				readIn = bis.readLine();
			} catch (Exception e) {
				break;
			}
			try {
				if (readIn.contains("_relations")) {
					readIn = readIn.replace("_relations", relationsOfFormatted
							+ "_relations");
				}
				if (readIn
						.contains("</" + relationsOfFormatted + "_relations>")) {
					pW.write(readIn);
				} else {
					pW.write(readIn + "\n");
				}
			} catch (Exception e) {
				break;
			}
		} while (readIn.equals(null) == false);
		pW.close();
		bis.close();
	}

	public void setRelationOf(String relationsOf, String targetRelation,
			int change) throws IOException, InterruptedException {
		StringMod sM = new StringMod();

		fetchRelations(relationsOf);

		if (characterRelationIDList.contains(targetRelation)) {
			for (int x = 0; x < characterRelationIDList.size(); x++) {
				if (characterRelationIDList.get(x).equalsIgnoreCase(
						targetRelation)
						|| characterRelationIDList.get(x).equalsIgnoreCase(
								sM.stringToEffectsTarget(targetRelation))) {
					writeRelationChanges(relationsOf,
							characterRelationIDList.get(x),
							characterRelationIntDataList.get(x),
							characterRelationIntDataList.get(x) + change);
				}
			}
		} else {
			characterRelationIDList.add(targetRelation);
			characterRelationIntDataList.add(change);
			writeRelationChanges(relationsOf, targetRelation, 0, change);
		}
	}

	public void writeRelationChanges(String relationsOf, String targetRelation,
			int oldIntData, int newIntData) throws IOException,
			InterruptedException {
		StringMod sM = new StringMod();
		FileWork fW = new FileWork();

		String changesToWrite, readIn;

		File relationFile = new File("save/charXMLs/relationXMLs/"
				+ sM.stringToEffectsTarget(relationsOf) + "_relations");
		File relationFileWithChanges = new File("save/charXMLs/relationXMLs/"
				+ sM.stringToEffectsTarget(relationsOf) + "_relations_tmp");

		changesToWrite = prepWriting(relationFile, relationsOf, targetRelation,
				oldIntData, newIntData, sM);

		try {
			if (changesToWrite.equals(null)) {
				addRelationToFile(relationsOf, targetRelation, newIntData,
						relationFile, relationFileWithChanges, sM);
			} else {
				relationFileWithChanges.createNewFile();

				PrintWriter pW = new PrintWriter(relationFileWithChanges);

				BufferedReader bis = new BufferedReader(new InputStreamReader(
						new FileInputStream(relationFile)));
				do {
					readIn = bis.readLine();

					try {
						if (readIn.contains(targetRelation)) {
							readIn = changesToWrite;
						}

						if (readIn.contains("</"
								+ sM.stringToEffectsTarget(relationsOf)
								+ "_relations>")) {
							pW.write(readIn);
						} else {
							pW.write(readIn + "\n");
						}
					} catch (NullPointerException npe) {
						break;
					}
				} while (readIn.equals(null) == false);
				pW.close();
				bis.close();
			}
		} catch (Exception e) {
			addRelationToFile(relationsOf, targetRelation, newIntData,
					relationFile, relationFileWithChanges, sM);
		}

		fW.fileOverwrite(
				"save/charXMLs/relationXMLs/"
						+ sM.stringToEffectsTarget(relationsOf) + "_relations",
				"save/charXMLs/relationXMLs/"
						+ sM.stringToEffectsTarget(relationsOf)
						+ "_relations_tmp");
	}

	public void addRelationToFile(String relationOf, String targetRelation,
			int intData, File relationFile, File relationFileWithChanges,
			StringMod sM) throws IOException {
		String toAdd = "\t\t\t\t<" + targetRelation + "=\""
				+ String.valueOf(intData) + "\">";

		String readIn;

		relationFileWithChanges.createNewFile();

		PrintWriter pW = new PrintWriter(relationFileWithChanges);

		BufferedReader bis = new BufferedReader(new InputStreamReader(
				new FileInputStream(relationFile)));
		do {
			readIn = bis.readLine();
			try {
				if (readIn.contains("<relation>")) {
					pW.write(readIn + "\n");
					pW.write(toAdd + "\n");
				} else {
					if (readIn.contains("</"
							+ sM.stringToEffectsTarget(relationOf)
							+ "_relations>")) {
						pW.write(readIn);
					} else {
						pW.write(readIn + "\n");
					}
				}
			} catch (NullPointerException npe) {
				break;
			}
		} while (readIn.equals(null) == false);
		pW.close();
		bis.close();
	}

	public String prepWriting(File relationFile, String relationsOf,
			String targetRelation, int oldIntData, int newIntData, StringMod sM)
			throws IOException {
		ValueWork vW = new ValueWork();

		String readIn, toWrite = null;

		try {
			if (relationFile.exists()) {
				BufferedReader bis = new BufferedReader(new InputStreamReader(
						new FileInputStream(relationFile)));

				try {
					do {
						readIn = bis.readLine();

						if (readIn.contains("<" + targetRelation + "=")) {
							if (newIntData >= 2000 || newIntData <= -2000) {
								toWrite = readIn.replace(String
										.valueOf(oldIntData), String.valueOf(vW
										.relationValueVerify(newIntData)));
							} else {
								toWrite = readIn.replace(
										String.valueOf(oldIntData),
										String.valueOf(newIntData));
							}
							break;
						}
					} while (readIn.equals(null) == false);
				} catch (NullPointerException npe) {
				}
				bis.close();
			} else {
				constructFileForRelationOf(relationFile,
						sM.stringToEffectsTarget(relationsOf));
			}
		} catch (Exception e) {
			constructFileForRelationOf(relationFile,
					sM.stringToEffectsTarget(relationsOf));
		}
		return toWrite;
	}

	public void clearRelationArrays() {
		characterRelationIDList.clear();
		characterRelationIntDataList.clear();
	}
}
