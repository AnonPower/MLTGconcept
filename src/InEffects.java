import java.io.IOException;

public class InEffects {
	boolean commandHasEffect = false;
	int commandEffectsCounter = 0;

	public void checkEffects(String dir, String input) throws IOException,
			InterruptedException {
		XMLParse xP = new XMLParse();

		xP.xMLParser(dir);

		for (int x = 0; x != xP.getTEMat().size(); x++) {
			if (xP.getTStrD().get(xP.tEMat.get(x)).equals(input))
				;
			{
				commandHasEffect = true;
				commandEffectsCounter++;
			}
		}
	}

	public boolean getCommandHasEffect() {
		return commandHasEffect;
	}

	public int getCommandEffectsCounter() {
		return commandEffectsCounter;
	}
}
