import java.io.IOException;
import java.util.Scanner;

public class GameDriver {
	static String savedOutput = "", playerName = "";

	final static Window win = new Window();

	private static Scanner kb;

	public static void main(String[] args) throws IOException,
			InterruptedException {
		win.windowInit();

		NPCBrain nB = new NPCBrain();
		TitleInit tI = new TitleInit();
		InputCollect iC = new InputCollect();
		UInVerify uIV = new UInVerify();
		XMLParse xP = new XMLParse();
		World w = new World();
		Logger log = new Logger();
		
		w.worldInit();

		xP.xMLParser("/invXMLs/attributesList");

		setPlayerName();

		tI.startTitle();
		System.out
				.print("\nAt anytime you may enter \"help\" for the list of available commands.\n");
		
		int turnCounter = 0;

		while (true) {
			win.scrollUpdate();
			iC.setCommand();
			uIV.inputVariablesWipe();
			nB.setNPCCommand();
			log.writeToLog(savedOutput);
			savedOutput = "";
			turnCounter++;
			if(turnCounter == 10){
				log.autoSave();
			}
		}
	}

	public static void setPlayerName() {
		PrintWork pW = new PrintWork();

		kb = new Scanner(System.in);

		do {
			pW.clearConsole();

			System.out.print("\nWhat is your name?\n"
					+ "\nMax Length: 24 chars\n");

			playerName = kb.nextLine();

			if (playerName.length() <= 24) {
				break;
			}
		} while (true);
	}

	public static String getPlayerName() {
		return playerName;
	}
}