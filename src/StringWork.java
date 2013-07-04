import java.io.IOException;

public class StringWork {
	public String findRangeTypeOfAttribute(String attribute)
			throws IOException, InterruptedException {
		XMLParse xP = new XMLParse();
		xP.xMLParser("defaults/MLTG/invXMLs/attributesList");

		if (xP.getTStrD().contains(attribute)) {
			for (int x = 0; x < xP.getTMMat().size(); x++) {
				if (xP.getTStrD().get(xP.getTMMat().get(x))
						.equalsIgnoreCase(attribute)) {
					int y = x;
					do {
						try {
							if (xP.getMarkerID().get(y)
									.equalsIgnoreCase("range")) {
								if (xP.getMarkerData().get(y)
										.equalsIgnoreCase("bad-good")) {
									return "bad-good";
								} else {
									if (xP.getMarkerData().get(y)
											.equalsIgnoreCase("good-bad")) {
										return "good-bad";
									}
								}
							}
						} catch (NullPointerException npe) {
							break;
						}
						y++;
					} while (xP.getTMMat().get(y) == xP.getTMMat().get(x));
				}
			}
		}
		return null;
	}
}
