import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ValueWork {
	ArrayList<Integer> sortedBadGoodArray = new ArrayList<Integer>(),
			sortedGoodBadArray = new ArrayList<Integer>();

	public ArrayList<Integer> getSortedBadGoodArray() {
		return sortedBadGoodArray;
	}

	public ArrayList<Integer> getSortedGoodBadArray() {
		return sortedGoodBadArray;
	}

	public void sortBadGoodValues(ArrayList<Integer> badGoodArray) {
		Collections.sort(badGoodArray);
		Collections.reverse(badGoodArray);
		sortedBadGoodArray.addAll(badGoodArray);
	}

	public void sortGoodBadValues(ArrayList<Integer> goodBadArray) {
		Collections.sort(goodBadArray);
		sortedGoodBadArray.addAll(goodBadArray);
	}

	public int determineRelationChange(String targetAttribute, String tStrE,
			String tBooE, String tIntEEqu, int tIntEAdd, int tIntESub)
			throws IOException, InterruptedException {
		StringWork sW = new StringWork();

		int relationChange = 0;

		if (tIntESub == 0) {
			if (tIntEAdd == 0) {
				if (tIntEEqu.equals(null)) {
					if (tBooE.equals(null)) {
						if (tStrE.equals(null)) {
						} else {
							// to determine if desired change
							// or good change
						}
					} else {
						// to determine if desired change
						// of good change
					}
				} else {
					String rangeType = sW
							.findRangeTypeOfAttribute(targetAttribute);

					try {
						if (rangeType.equals(null)) {
						} else {
							if (rangeType.equalsIgnoreCase("bad-good")) {
								// if old value was lower or higher to determine
								// relation change
							} else {
								if (rangeType.equals("good-bad")) {
									// if old value was lower or higher to
									// determine relation change
								}
							}
						}
					} catch (Exception e) {
					}
				}
			} else {
				String rangeType = sW.findRangeTypeOfAttribute(targetAttribute);

				try {
					if (rangeType.equals(null)) {
					} else {
						if (rangeType.equalsIgnoreCase("bad-good")) {
							relationChange = tIntEAdd * 10;
						} else {
							if (rangeType.equals("good-bad")) {
								relationChange = tIntEAdd * -10;
							}
						}
					}
				} catch (Exception e) {
				}
			}
		} else {
			String rangeType = sW.findRangeTypeOfAttribute(targetAttribute);

			try {
				if (rangeType.equals(null)) {
				} else {
					if (rangeType.equalsIgnoreCase("bad-good")) {
						relationChange = tIntESub * 10;
					} else {
						if (rangeType.equals("good-bad")) {
							relationChange = tIntESub * -10;
						}
					}
				}
			} catch (Exception e) {
			}
		}
		return relationChange;
	}

	public int valueVerify(int value) {
		if (value >= 2000) {
			value = 1999;
		} else {
			if (value <= 0) {
				value = 1;
			}
		}
		return value;
	}

	public int relationValueVerify(int value) {
		int fixed = 1999;
		if (value > fixed) {
			return fixed;
		} else if ((value) < (-1 * (fixed))) {
			return (-1 * (fixed));
		}
		return value;
	}
}
