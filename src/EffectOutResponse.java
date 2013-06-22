import java.io.IOException;

public class EffectOutResponse {
	XMLParse xP = new XMLParse();

	public String responseInit(String doer, String target,
			String targetAttribute, String tStrE, String tBooE,
			String tIntEEqu, int tIntEAdd, int tIntESub) throws IOException,
			InterruptedException {
		xP.xMLArraysWipe();

		xP.xMLParser("/invXMLs/attributesList");

		String responseOut = "";

		ValueWork vW = new ValueWork();

		Relations rel = new Relations();
		int change = vW.determineRelationChange(targetAttribute, tStrE, tBooE,
				tIntEEqu, tIntEAdd, tIntESub);
		if (change == 0) {
			responseOut = neutralResponse(doer, target);
		} else {
			if (change > 0) {
				responseOut = goodResponse(doer, target, targetAttribute,
						tStrE, tBooE, tIntEEqu, tIntEAdd, tIntESub);
			} else {
				if (change < 0) {
					responseOut = badResponse(doer, target, targetAttribute,
							tStrE, tBooE, tIntEEqu, tIntEAdd, tIntESub);
				}
			}
		}
		rel.setRelationOf(doer, target, (change / 2));
		rel.setRelationOf(target, doer, change);

		if (responseOut.equals("")) {
			return responseOut;
		} else {
			return ("\n" + responseOut + "\n");
		}
	}

	public String goodResponse(String doer, String target,
			String targetAttribute, String tStrE, String tBooE,
			String tIntEEqu, int tIntEAdd, int tIntESub) throws IOException,
			InterruptedException {
		String response = "";

		if (doer.equals(target)) {
			response = (target + " makes themself happy.");
		} else {
			if (doer.equals("player")) {
				doer = GameDriver.getPlayerName();
			}
			response = (target + " says, \"Thanks " + doer + "!\" and smiles.");
		}
		return response;
	}

	public String badResponse(String doer, String target,
			String targetAttribute, String tStrE, String tBooE,
			String tIntEEqu, int tIntEAdd, int tIntESub) {
		String response = "";

		if (doer.equals(target)) {
			response = (target + " makes themself sad.");
		} else {
			if (doer.equals("player")) {
				doer = GameDriver.getPlayerName();
			}
			response = (target + " says, \"Why would you do that to me " + doer
					+ "?!\" and then " + target + " frowns.");
		}

		return response;
	}

	public String neutralResponse(String doer, String target) {
		String response = "";

		if (doer.equals(target)) {
			response = (target + " and doesn't give a care.");
		} else {
			if (doer.equals("player")) {
				doer = GameDriver.getPlayerName();
			}
			response = (target
					+ " says, \"Do you think this is a game " + doer + "?!\".");
		}

		return (response);
	}
}
