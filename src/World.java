import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class World {
	File worldFile;
	File worldFolder;
	public void worldInit() {
		worldFolder = new File("/save/worldXMLs/");
		// scan folder for world files
		// Static "Equestria" variable for now. TO CHANGE
		if (isWorldFound(worldFolder, "Equestria")) {
			//TASK : What to do if world files found.
			loadPlayer(worldFile);
		} else {
			createNewWorld();
		}
	}
	public boolean isWorldFound(File worldFolder, String worldName) {
		boolean worldFound = false;
		File[] worldFiles = worldFolder.listFiles(); //Was a test to try to see what files were returned to the array, it was null
		try {
			if (worldFolder.listFiles().length != 0) {
				worldFile = new File(worldFolder
						+ "/"
						+ worldName
						+ "_w");
				try {
					if(worldFile.exists()){
						worldFound = true;
					}
				} catch (Exception worldNotFound) {}
			}
		} catch (Exception noWorldsFound) {}
		return worldFound;
	}
	//to be done
	public void createNewWorld() {
		BufferedReader bis = new BufferedReader(new InputStreamReader(
				getClass().getResourceAsStream("/invXMLs/_w")));
	}
	public void loadPlayer(File worldDir) {
		//load information from world file to game.
		File playerFile = new File("/save/charXMLs/player");
		try {
			if(playerFile.exists()){
				System.out.println(findLocation(playerFile));
			}else{
				//If player needs to be created
			}
		} catch (Exception e) {
		}
	}
	public String findLocation(File playerFile) throws IOException{
		BufferedReader bis;
		String readIn = null;
		StringTokenizer st;
		int counter = 0;
		String locString = "";
		bis = new BufferedReader(new InputStreamReader(
				new FileInputStream(playerFile)));
		do{
			readIn = bis.readLine();
			if(readIn.contains("worldName")
					|| readIn.contains("regionName")
					|| readIn.contains("areaName")
					|| readIn.contains("localName")){
				st = new StringTokenizer(readIn);
				st.nextToken("\""); //Tokenizer scrap
				locString += (st.nextToken("\"")
						+ "_"); //grab xml data
				counter++;
				if(counter >= 4){
					break;
				}
			}
		}while(readIn.equals(null) == false);
		bis.close();
		return locString;
	}
}