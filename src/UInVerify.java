import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class UInVerify {
	ArrayList<String> commandsArray = new ArrayList<String>(),
			charNounsArray = new ArrayList<String>(),
			objNounsArray = new ArrayList<String>(),
			toTarLinkingArray = new ArrayList<String>(),
			useLinkingArray = new ArrayList<String>();

	boolean isRealCommand;

	// used to check user input with recognized definitions
	// other checker methods resemble the commandChecker (ie charNounChecker,
	// toTarLinkingChecker, ect.)
	// the methods pass the original input in between each other.
	public void xMLCompInputInit(String input) throws IOException,
			InterruptedException {
		inputVariablesWipe();

		String dir = ("defaults/MLTG/invXMLs/commands");
		commandsArray.addAll(inXMLFind(dir, input));

		if (commandsArray.isEmpty()) {
			isRealCommand = false;
		} else {
			isRealCommand = true;
		}

		if (isRealCommand) {
			dir = ("defaults/MLTG/invXMLs/characters");
			charNounsArray.addAll(inXMLFind(dir, input));

			dir = ("defaults/MLTG/invXMLs/objects");
			objNounsArray.addAll(inXMLFind(dir, input));

			dir = ("defaults/MLTG/invXMLs/toTarLinking");
			toTarLinkingArray.addAll(inXMLFind(dir, input));

			dir = ("defaults/MLTG/invXMLs/useLinking");
			useLinkingArray.addAll(inXMLFind(dir, input));
		}
	}
	public boolean getIsRealCommand() {
		return isRealCommand;
	}
	public ArrayList<String> getCommandsArray() {
		return commandsArray;
	}
	public ArrayList<String> getCharNounsArray() {
		return charNounsArray;
	}
	public ArrayList<String> getObjNounsArray() {
		return objNounsArray;
	}
	public ArrayList<String> getToTarLinkingArray() {
		return toTarLinkingArray;
	}
	public ArrayList<String> getUseLinkingArray() {
		return useLinkingArray;
	}
	public ArrayList<String> inXMLFind(String dir, String input)
			throws IOException, InterruptedException {
		XMLParse xP = new XMLParse();
		InEffects iE = new InEffects();

		ArrayList<String> splitCheckerArray = new ArrayList<String>(), inputVerified = new ArrayList<String>(), verifiedElementsInInput = new ArrayList<String>();

		String stringBuilder = "", checkStor, splitStor;

		xP.xMLParser(dir);
		try {
			if (xP.getTStrD().isEmpty()) {
				System.exit(101);
			} else {
				for (int x = 0; x < xP.getTStrD().size(); x++) {
					StringTokenizer st2 = new StringTokenizer(input);

					while (st2.hasMoreTokens()) {
						checkStor = st2.nextToken();

						if (xP.getTStrD().get(x).contains(" ")) {
							StringTokenizer splitter = new StringTokenizer(xP
									.getTStrD().get(x));

							while (splitter.hasMoreTokens()) {
								splitStor = splitter.nextToken();

								if (splitStor.equalsIgnoreCase(checkStor)) {
									splitCheckerArray.add(splitStor);
								}
							}
						} else {
							if (splitCheckerArray.isEmpty()) {
								if (xP.getTStrD().get(x)
										.equalsIgnoreCase(checkStor)) {
									inputVerified.add(xP.getTStrD().get(x));
									iE.checkEffects(dir, xP.getTStrD().get(x));
								}
							}
						}
					}
				}
				if (splitCheckerArray.isEmpty() == false) {
					for (int b = 0; b < splitCheckerArray.size(); b++) {
						if (b == splitCheckerArray.size()) {
							stringBuilder += (splitCheckerArray.get(b));
						} else {
							stringBuilder += (splitCheckerArray.get(b) + " ");
						}
					}
					stringBuilder = stringBuilder.trim();

					boolean stringBuilderIsValid = false;
					for (int x = 0; x < xP.getTStrD().size(); x++) {
						if (stringBuilder
								.equalsIgnoreCase(xP.getTStrD().get(x))) {
							stringBuilderIsValid = true;
						}
					}
					if (stringBuilderIsValid) {
						iE.checkEffects(dir, stringBuilder);
						inputVerified.add(stringBuilder);
					}
				}
			}
		} catch (Exception e) {
		}
		verifiedElementsInInput.addAll(findXMLElementInInput(inputVerified,
				input));
		return verifiedElementsInInput;
	}

	public ArrayList<String> findXMLElementInInput(ArrayList<String> arrayIn,
			String input) {
		ArrayList<String> elementsFoundInInput = new ArrayList<String>();

		for (int x = 0; x < arrayIn.size(); x++) {
			if (input.toLowerCase().contains(arrayIn.get(x).toLowerCase())) {
				elementsFoundInInput.add(arrayIn.get(x));
			}
		}
		return elementsFoundInInput;
	}

	public void inputVariablesWipe() {
		InEffects iE = new InEffects();

		// resets all variables for new command
		commandsArray.clear();
		charNounsArray.clear();
		objNounsArray.clear();
		toTarLinkingArray.clear();
		useLinkingArray.clear();

		isRealCommand = false;
		iE.commandHasEffect = false;
		iE.commandEffectsCounter = 0;
	}
}
