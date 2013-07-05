import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class World {
	private static File worldFile;
	static File worldFolder;
	public void worldInit() throws IOException {
		worldFolder = new File("data/MLTG/save/worldXMLs");
		//If specified world is found, load world data from it
		//If false, either create new world or try loading different one depending on
		//if any other worlds are available.
		if (isWorldFound(worldFolder, "Equestria")) {
			//TODO FINISH: What to do if world files found.
			//TODO: Include dynamic character parameter for use by NPCs, not just player
			loadCharacter(getWorldFile(), "player");
		} else {
			//Call method to generate new world in save/worldXMLs creating a worldName_w file
			createNewWorld();
		}
	}
	//Returns true if the specified world is found inside the save/worldXMLs folder
	//returns false if it is not found
	public boolean isWorldFound(File worldFolder, String worldName) {
		boolean worldFound = false;
		try {
			//if there are files in the worldXMLs directory, try to find specified world
			//otherwise, false
			if (worldFolder.listFiles().length != 0) {
				setWorldFile(new File(worldFolder
						+ "/"
						+ worldName
						+ "_w"));
				try {
					//method returns true if worldFile is found, this determines whether file exists or not.
					if(getWorldFile().exists()){
						worldFound = true;
					}
				} catch (Exception worldNotFound) {}
			}
		} catch (Exception noWorldsFound) {}
		return worldFound;
	}
	//TODO: generate a new world based on using worldName parameter for the name of the New world.
	public void createNewWorld() throws IOException {
		BufferedReader bis = new BufferedReader(new InputStreamReader(
				getClass().getResourceAsStream("defaults/MLTG/XMLTemplates/_w")));
		bis.close();
	}
	//This method will be used to call functions and methods that are relevant to loading
	//information that refers to the player.
	//TODO: Availability of objects and characters to the doer of a command based on locations
	//TODO: Access to other locations from a location
	//TODO: Location specific attributes, traits and other information that can be accessed
	public void loadCharacter(File worldDir, String charDir) throws IOException {
		//Character xml object: the doer.
		File playerFile = new File("data/MLTG/save/charXMLs/" + charDir);
		try {
			if(playerFile.exists()){
				//debug information
				//TODO: With location information, find relevant information from worldName_w file such as:
				//objects in location, characters in location, description of loc, traits of loc, name of loc, ect.
				GameDriver.playerLoc = findLocation(playerFile);
			}else{
				//TODO: If player file is missing or needs to be created.
			}
		} catch (FileNotFoundException fnfe) {
			//TODO: If playerfile is missing or needs to be created.
		}
	}
	//Find the locations variable information for the character that is requested
	//Returns a string with all the variable's names seperated by underscores.
	//variables: worldName, regionName, areaName, localName.
	public String findLocation(File playerFile) throws IOException{
		BufferedReader bis;
		String readIn = null;
		StringTokenizer st;
		int counter = 0;
		String locString = "";
		//we already checked if playerfile exists, since it does, we will need to fetch <placement> from it
		bis = new BufferedReader(new InputStreamReader(
				new FileInputStream(playerFile)));
		do{
			readIn = bis.readLine();
			//if a line contains either of the four variable names, then we tokenize the variable data and read it in.
			if(readIn.contains("worldName")
					|| readIn.contains("regionName")
					|| readIn.contains("areaName")
					|| readIn.contains("localName")){
				st = new StringTokenizer(readIn);
				st.nextToken("\""); //Tokenizer scrap
				locString += (st.nextToken("\"")
						+ "_"); //grabs xml data, appends an underscore after variable data to be tokenized
				//from the string it is being appended to later.
				counter++;
				if(counter >= 4){
					break;
				}
			}
		}while(readIn.equals(null) == false);
		bis.close();
		//returns a string containing all the variable information.
		return locString;
	}
	public File getWorldFile() {
		return worldFile;
	}
	/*
	 * 
	 * 	@param charLoc			//The location string of character being referred to.
	 * 
	 * 
	 */
	//Used to determine and find what is avaialble to the related character.
	public void locationAvailability(String charLoc, ){
		
	}
}