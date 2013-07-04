import java.io.*;
public class TitleInit {
	String titleGen;
	public void startTitle() throws IOException, InterruptedException {
		OutManipulation oM = new OutManipulation();
		PrintWork pW = new PrintWork();
		GameMusic gM = new GameMusic();

		System.out.println("May play loud music...");

		//oM.pauseTime(4);
		pW.clearConsole();

		gM.initMusic("");
		gM.musicControl("start");

		// set and fetch title.
		setTitleGen("defaults/MLTG/titleGen/titleList");
		System.out.print(getTitleGen() + "\n\n\n");

		//oM.pauseTime(5);

		oM.waitForEnter();

		gM.musicControl("stop");
	}
	// Using the random number generator, picks a random line
	// from the titlelist.txt to pick a portion of the title to be displayed
	// each time the game is executed.
	public void setTitleGen(String dir) throws IOException {
		FileWork fW = new FileWork();
		RanGen rG = new RanGen();

		int ranNum;

		BufferedReader bis = new BufferedReader(new InputStreamReader(
				getClass().getResourceAsStream(dir)));

		// calls on the generator with parameter of 4 (being 4 different title
		// possibilities in the .txt document)
		rG.setRanNum(fW.fileLineCount(dir));
		ranNum = rG.getRanNum();
		// ranNum = the number that was generated based on the parameter
		// a line is read each loop, and loops until the counter is equivalent
		// to the randomly generated number.
		// which determines the same line in the .txt document that will be used
		// for the title.
		if (ranNum == 0) {
			titleGen = bis.readLine();
		} else {
			for (int x = 0; x < ranNum; x++) {
				bis.readLine();
			}
			titleGen = bis.readLine();
		}
		bis.close();
	}
	public String getTitleGen() {
		return titleGen;
	}
}