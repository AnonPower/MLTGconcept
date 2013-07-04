import java.io.IOException;
import java.util.Scanner;

public class InputCollect {
	private Scanner kb;
	private Scanner kb2;
	private final GameMusic gM = new GameMusic();

	// Sets player input.
	public void setCommand() throws IOException, InterruptedException {
		PrintWork pW = new PrintWork();
		InOutTrans iOT = new InOutTrans();
		UInVerify uIV = new UInVerify();

		String commandInput, playerCommand;

		kb = new Scanner(System.in);

		String doer = "player";

		while (true) {
			commandInput = kb.nextLine();
			// convert input to all lowercase for simplified checking.
			commandInput = commandInput.toLowerCase();
			playerCommand = commandInput;
			// checks if player has typed help for a list of available commands
			// to be displayed.
			if (commandInput.equals("help")) {
				pW.printCommands();
				continue;
			} else {
				if (commandInput.equals("system.clear")) {
					pW.clearConsole();
					continue;
				} else {
					if (commandInput.equals("who is here")) {
						pW.printWhoIsHere("defaults/MLTG/invXMLs/characters");
						continue;
					} else {
						if (commandInput.equals("what is here")) {
							pW.printWhatIsHere("defaults/MLTG/invXMLs/objects");
							continue;
						} else {
							if (commandInput.equals("player.change.name")) {
								GameDriver.setPlayerName();
								continue;
							} else {
								if (commandInput.equals("system.play.song")) {
									gM.musicControl("stop");
									gM.initMusic("");
									gM.musicControl("start");
									continue;
								} else {
									if (commandInput.equals("system.stop.song")) {
										gM.musicControl("stop");
										continue;
									} else {
										if (commandInput
												.startsWith("system.quit.game")) {
											System.exit(0);
										} else {
											if (commandInput
													.contains("system.quit.game")) {
												commandInput = commandInput
														.replace(
																"system.quit.game",
																"");
											}

											// sends to checker to verify the
											// player's command exists, or is
											// possible.
											uIV.xMLCompInputInit(commandInput);
										}
									}
								}
							}
						}
					}
				}
			}
			// if the command checker verifies the input it will break the loop.
			// Otherwise will return a message and request another input.
			// tbr
			if (uIV.getIsRealCommand()) {
				break;
			} else {
				// this message is printed and the user is requested to enter
				// input again.
				System.out
						.print("\n\nThat wasn't a valid command."
								+ "\nIf you need the list of available commands type: \"help\".\n");
			}
		}
		iOT.commandTranslate(uIV.getCommandsArray(), uIV.getCharNounsArray(),
				uIV.getObjNounsArray(), uIV.getToTarLinkingArray(),
				uIV.getUseLinkingArray(), playerCommand, doer);
	}

	// when player chooses a dialogue command
	// This will initiate dialogue input collection and translation.
	public void setDialogue(String dialogueType) throws InterruptedException {
		String doer = "player", dialogueTypeStor, dialogueInput;

		dialogueTypeStor = dialogueType;

		InOutTrans cc = new InOutTrans();

		kb2 = new Scanner(System.in);

		if (dialogueTypeStor.equals("speak")) {
			System.out.print("\nSpeak>");
		} else {
			if (dialogueTypeStor.equals("shout")) {
				System.out.print("\nShout>");
			} else {
				if (dialogueTypeStor.equals("whisper")) {
					System.out.print("\nWhisper>");
				} else {
					if (dialogueTypeStor.equals("say")) {
						System.out.print("\nSay>");
					}
				}
			}
		}
		dialogueInput = kb2.nextLine();
		// Like commandInput, is converted to lowercase for checking purposes,
		// but
		// original input is kept for reoutput (to keep input case. however, ie
		// "HeLLo! TroLoLoL!" may need to be converted to proper format.)
		dialogueInput = dialogueInput.toLowerCase();
		cc.dialogueTranslate(dialogueTypeStor, dialogueInput, doer);
	}
}
