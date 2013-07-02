import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ToLinkingWork {
	public ArrayList<String> getToLinkingForCommand(String command)
			throws IOException, InterruptedException {
		XMLParse xP = new XMLParse();

		xP.xMLParser("/invXMLs/commands");

		ArrayList<String> toLinkingsForCommand = new ArrayList<String>();

		for (int x = 0; x < xP.getTMMat().size(); x++) {
			try {
				if (toLinkingsForCommand.isEmpty() == false) {
					break;
				}
			} catch (NullPointerException npe) {
			}
			if (xP.getTStrD().contains(command)) {
				if (xP.getTStrD().get(x).equalsIgnoreCase(command)) {
					try {
						if (toLinkingsForCommand.isEmpty() == false) {
							break;
						}
					} catch (NullPointerException npe) {
					}
					int x2 = x;
					if (xP.getTStrD().get(xP.getTMMat().get(x2))
							.equalsIgnoreCase(command)) {
					} else {
						do {
							x2++;
						} while ((xP.getTStrD().get(xP.getTMMat().get(x2))
								.equalsIgnoreCase(command) == false));
					}
					int y = x2;
					do {
						if (xP.getMarkerID().get(y)
								.equalsIgnoreCase("toLinking")) {
							try {
								if (xP.getMarkerData().get(y).equals(null)) {
									break;
								} else {
									if (xP.getMarkerData().get(y).contains("_")) {
										StringTokenizer st = new StringTokenizer(
												xP.getMarkerData().get((y)));

										while (st.hasMoreTokens()) {
											try {
												toLinkingsForCommand.add(st
														.nextToken("_"));
											} catch (NullPointerException npe) {
												break;
											}
										}
									} else {
										toLinkingsForCommand.add(xP
												.getMarkerData().get((y)));
									}
								}
							} catch (NullPointerException npe) {
								break;
							}
						}
						try {
							if (toLinkingsForCommand.isEmpty() == false) {
								break;
							}
						} catch (NullPointerException npe) {
						}
						y++;
					} while (xP.getTStrD().get(xP.getTMMat().get(y))
							.equalsIgnoreCase(command));
				}
			} else {
				break;
			}
		}
		return toLinkingsForCommand;
	}

	public ArrayList<String> getCommandType(String command) throws IOException,
			InterruptedException {
		XMLParse xP = new XMLParse();

		xP.xMLParser("/invXMLs/commands");

		ArrayList<String> typeOfCommand = new ArrayList<String>();

		for (int x = 0; x < xP.getTMMat().size(); x++) {
			try {
				if (typeOfCommand.isEmpty() == false) {
					break;
				}
			} catch (NullPointerException npe) {
			}
			if (xP.getTStrD().contains(command)) {
				if (xP.getTStrD().get(x).equalsIgnoreCase(command)) {
					try {
						if (typeOfCommand.isEmpty() == false) {
							break;
						}
					} catch (NullPointerException npe) {
					}
					int x2 = x;
					if (xP.getTStrD().get(xP.getTMMat().get(x2))
							.equalsIgnoreCase(command)) {
					} else {
						do {
							x2++;
						} while ((xP.getTStrD().get(xP.getTMMat().get(x2))
								.equalsIgnoreCase(command) == false));
					}
					int y = x2;
					do {
						if (xP.getMarkerID().get(y).equalsIgnoreCase("type")) {
							try {
								if (xP.getMarkerData().get(y).equals(null)) {
									break;
								} else {
									if (xP.getMarkerData().get(y).contains("_")) {
										StringTokenizer st = new StringTokenizer(
												xP.getMarkerData().get((y)));

										while (st.hasMoreTokens()) {
											try {
												typeOfCommand.add(st
														.nextToken("_"));
											} catch (NullPointerException npe) {
												break;
											}
										}
									} else {
										typeOfCommand.add(xP.getMarkerData()
												.get((y)));
									}
								}
							} catch (NullPointerException npe) {
								break;
							}
						}
						try {
							if (typeOfCommand.isEmpty() == false) {
								break;
							}
						} catch (NullPointerException npe) {
						}
						y++;
					} while (xP.getTStrD().get(xP.getTMMat().get(y))
							.equalsIgnoreCase(command));
				}
			} else {
				break;
			}
		}
		return typeOfCommand;
	}

	public String setToLinkingToUse(ArrayList<String> commandTypes,
			ArrayList<String> toLinkings) throws IOException,
			InterruptedException {
		RanGen rG = new RanGen();

		XMLParse xP = new XMLParse();

		xP.xMLParser("/invXMLs/toTarLinking");

		ArrayList<String> useableLinkings = new ArrayList<String>();

		String linkingToUse = null;

		for (int x = 0; x < xP.getTStrD().size(); x++) {
			for (int y = 0; y < toLinkings.size(); y++) {
				if (xP.getTStrD().get(x).equalsIgnoreCase(toLinkings.get(y))) {
					int x2 = x;
					while (xP.getTStrD().get(xP.getTMMat().get(x2))
							.equalsIgnoreCase(toLinkings.get(y)) == false) {
						x2++;
					}
					int z = x2;
					do {
						if (xP.getMarkerID().get(z).equalsIgnoreCase("type")) {
							if (xP.getMarkerData().get(z).contains("_")) {
								StringTokenizer st = new StringTokenizer(xP
										.getMarkerData().get(z));
								String toCheck;

								while (st.hasMoreTokens()) {
									toCheck = st.nextToken("_");
									if (commandTypes.contains(toCheck)) {
										useableLinkings.add(toLinkings.get(y));
									}
									try {
										if (st.hasMoreTokens() == false) {
											break;
										}
									} catch (Exception e) {
										break;
									}
								}
							} else {
								if (commandTypes.contains(xP.getMarkerData()
										.get(z))) {
									useableLinkings.add(toLinkings.get(y));
								}
							}
						}
						z++;
					} while (xP.getTStrD().get(xP.getTMMat().get(z))
							.equalsIgnoreCase(toLinkings.get(y)));
				}
			}
		}
		if (useableLinkings.size() > 1) {
			rG.setRanNum(useableLinkings.size());
			linkingToUse = useableLinkings.get(rG.getRanNum());
		} else {
			linkingToUse = useableLinkings.get(0);
		}
		return linkingToUse;
	}
}