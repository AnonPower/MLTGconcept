import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class World {
	private File worldFile;
	File worldFolder;
	String worldName = "",
	regionName = "",
	areaName = "",
	localName = "";
	String[][] locationInfo = new String[100][3];
	public String worldDesc = null,
			   regionDesc = null,
			   areaDesc = null,
			   localDesc = null;
	public ArrayList<String> whoIsHere = new ArrayList<String>(),
									connections = new ArrayList<String>(),
									staticObjects = new ArrayList<String>(),
									dynamicObjects = new ArrayList<String>();
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
		File characterFile = new File("data/MLTG/save/charXMLs/" + charDir);
		try {
			if(characterFile.exists()){
				//debug information
				//TODO: With location information, find relevant information from worldName_w file such as:
				//objects in location, characters in location, description of loc, traits of loc, name of loc, ect.
				GameDriver.characterLoc = findLocation(characterFile);
				locationAvailability(GameDriver.characterLoc);
				exportLocationInfo();
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
		//Sets location variables for related character.
		if(locString.equals("") == false){
			StringTokenizer locST = new StringTokenizer(locString);
			try {
				setWorldName(locST.nextToken("_")); //should get worldName
				setRegionName(locST.nextToken("_")); //should get regionName
				setAreaName(locST.nextToken("_")); //should get areaName
				setLocalName(locST.nextToken("_")); //should get localName
			} catch (NullPointerException npe) {
				System.out.print("\nError loading world, null locString pointer.\n");
				System.exit(004);
			}
		}
		//returns a string containing all the variable information.
		return locString;
	}
	public File getWorldFile() {
		return worldFile;
	}
	public void setWorldFile(File fileInc){
		worldFile = fileInc;
	}
	/*
	 * 	Pnoy
	 * 	locationAvailability()
	 *		//Used to determine and find what is avaialble to the related character. 
	 * 
	 * 	@param charLoc			//The location string of character being referred to.
	 * 
	 */
	public void locationAvailability(String charLoc) throws IOException{
		try {
			clearArrays();
		} catch (NullPointerException npe) {
		}
		try {
			BufferedReader bis = new BufferedReader(new InputStreamReader(
					new FileInputStream(worldFile)));
			String readIn = null;
			StringTokenizer worldST;
			int counter = 0;
			boolean isLocalDone = false;
			do{
				readIn = bis.readLine();
				//world information parse
				if(readIn.contains(getWorldName()
						+ "_w")
						&& readIn.contains("cTo") == false
						&& readIn.contains("</") == false){
					do{
						readIn = bis.readLine();
						if(readIn.contains("<String>")){
							break;
						}
						readIn = readIn.trim();
						if(readIn.startsWith("<")){
							readIn = readIn.replaceFirst("<", "");
						}
						if(readIn.endsWith(">")){
							readIn = readIn.replace(">", "");
						}
						worldST = new StringTokenizer(readIn);
						locationInfo[counter][0] = worldST.nextToken("="); //tag name get
						worldST.nextToken("\""); //scrap
						locationInfo[counter][1] = worldST.nextToken("\""); //tag data get
						locationInfo[counter][2] = "_w";
						counter++;
					}while(readIn.contains("<String>") == false);
				}
				//region information parse
				if(readIn.contains(getRegionName()
						+ "_r")
						&& readIn.contains("cTo") == false
						&& readIn.contains("</") == false){
					do{
						readIn = bis.readLine();
						if(readIn.contains("_a")){
							break;
						}
						readIn = readIn.trim();
						if(readIn.startsWith("<")){
							readIn = readIn.replaceFirst("<", "");
						}
						if(readIn.endsWith(">")){
							readIn = readIn.replace(">", "");
						}
						worldST = new StringTokenizer(readIn);
						locationInfo[counter][0] = worldST.nextToken("="); //tag name get
						worldST.nextToken("\""); //scrap
						locationInfo[counter][1] = worldST.nextToken("\""); //tag data get
						locationInfo[counter][2] = "_r";
						counter++;
					}while(readIn.contains("_a") == false);
				}
				//area information parse
				if(readIn.contains(getAreaName()
						+ "_a")
						&& readIn.contains("cTo") == false
						&& readIn.contains("</") == false){
					do{
						readIn = bis.readLine();
						if(readIn.contains("_l")){
							break;
						}
						readIn = readIn.trim();
						if(readIn.startsWith("<")){
							readIn = readIn.replaceFirst("<", "");
						}
						if(readIn.endsWith(">")){
							readIn = readIn.replace(">", "");
						}
						worldST = new StringTokenizer(readIn);
						locationInfo[counter][0] = worldST.nextToken("="); //tag name get
						worldST.nextToken("\""); //scrap
						locationInfo[counter][1] = worldST.nextToken("\""); //tag data get
						locationInfo[counter][2] = "_a";
						counter++;
					}while(readIn.contains("_l") == false);
				}
				//local information parse
				if(readIn.contains(getLocalName()
						+ "_l")
						&& readIn.contains("cTo") == false
						&& readIn.contains("</") == false){
					do{
						readIn = bis.readLine();
						if(readIn.contains("</"
								+ getLocalName()
								+ "_l>")){
							break;
						}
						readIn = readIn.trim();
						if(readIn.startsWith("<")){
							readIn = readIn.replaceFirst("<", "");
						}
						if(readIn.endsWith(">")){
							readIn = readIn.replace(">", "");
						}
						worldST = new StringTokenizer(readIn);
						locationInfo[counter][0] = worldST.nextToken("="); //tag name get
						worldST.nextToken("\""); //scrap
						locationInfo[counter][1] = worldST.nextToken("\""); //tag data get
						locationInfo[counter][2] = "_l";
						counter++;
					}while(readIn.contains("</"
							+ getLocalName()
							+ "_l>") == false);
					isLocalDone = true;
				}
				if(isLocalDone){
					break;
				}
			}while(readIn.equals(null) == false);
			bis.close();
		} catch (FileNotFoundException fnfe) {
			System.out.print("\nWorld loading error\n");
			System.exit(003);
		}
	}
	/*
	 * 	Pnoy
	 * 	exportLocationInfo()
	 * 	//Used to load location availability information for calling class or instance.
	 */
	public void exportLocationInfo(){
		for(int x = 0; x != 100; x++){
			try {
				if(locationInfo[x][0].equals(null)){
					break;
				}
			} catch (NullPointerException npe) {
				break;
			}
			switch(locationInfo[x][2]){
				case "_w":{
					if(locationInfo[x][0].equals("description")){
						worldDesc = locationInfo[x][1];
					}
					break;
				}
					
				case "_r":{
					if(locationInfo[x][0].equals("description")){
						regionDesc = locationInfo[x][1];
					}
					break;
				}
					
				case "_a":{
					if(locationInfo[x][0].equals("description")){
						areaDesc = locationInfo[x][1];
					}
					break;
				}
					
				case "_l":{
					if(locationInfo[x][0].equals("description")){
						localDesc = locationInfo[x][1];
						continue;
					}
					if(locationInfo[x][0].equals("cTo")){
						parseElement(locationInfo[x][1], "&", 0);
						continue;
					}
					if(locationInfo[x][0].equals("what-dyn")){
						parseElement(locationInfo[x][1], "_", 1);
						continue;
					}
					if(locationInfo[x][0].equals("what-sta")){
						parseElement(locationInfo[x][1], "_", 2);
						continue;
					}
					if(locationInfo[x][0].equals("whoIs")){
						parseElement(locationInfo[x][1], "_", 3);
						continue;
					}
					break;
				}
				default:break;
			}
		}
	}
	/*
	 * 	Pnoy
	 * 	parseElement()
	 * 	//Used to parse data strings from xmls that may contain tokens
	 * 
	 * 	@param		String inc		//the data string read in from the XML to be parsed
	 * 	@param		String token	//the delim for the tokenizer
	 * 	@param		int type	//used to determine which array to be stored in, to be revised
	 */
	public void parseElement(String inc, String token, int type){
		switch(type){
			case 0:{
				if(inc.contains(token)){
					StringTokenizer st = new StringTokenizer(inc);
					try {
						while(st.hasMoreTokens()){
							connections.add(st.nextToken(token));
						}
					} catch (NullPointerException npe) {}
				}else{
					connections.add(inc);
				}
				break;
			}
			case 1:{
				if(inc.contains(token)){
					StringTokenizer st = new StringTokenizer(inc);
					try {
						while(st.hasMoreTokens()){
							dynamicObjects.add(st.nextToken(token));
						}
					} catch (NullPointerException npe) {}
				}else{
					dynamicObjects.add(inc);
				}
				break;
			}
			case 2:{
				if(inc.contains(token)){
					StringTokenizer st = new StringTokenizer(inc);
					try {
						while(st.hasMoreTokens()){
							staticObjects.add(st.nextToken(token));
						}
					} catch (NullPointerException npe) {}
				}else{
					staticObjects.add(inc);
				}
				break;
			}
			case 3:{
				if(inc.contains(token)){
					StringTokenizer st = new StringTokenizer(inc);
					try {
						while(st.hasMoreTokens()){
							whoIsHere.add(st.nextToken(token));
						}
					} catch (NullPointerException npe) {}
				}else{
					whoIsHere.add(inc);
				}
				break;
			}
			default:break;
		}
	}
	public void setWorldName(String inc){
		worldName = inc;
	}
	public void setRegionName(String inc){
		regionName = inc;
	}
	public void setAreaName(String inc){
		areaName = inc;
	}
	public void setLocalName(String inc){
		localName = inc;
	}
	public String getWorldName(){
		return worldName;
	}
	public String getRegionName(){
		return regionName;
	}
	public String getAreaName(){
		return areaName;
	}
	public String getLocalName(){
		return localName;
	}
	public String getWorldDesc(){
		return worldDesc;
	}
	public String getRegionDesc(){
		return regionDesc;
	}
	public String getAreaDesc(){
		return areaDesc;
	}
	public String getLocalDesc(){
		return localDesc;
	}
	public ArrayList<String> getConnections(){
		return connections;
	}
	public ArrayList<String> getDynamicObjects(){
		return dynamicObjects;
	}
	public ArrayList<String> getStaticObjects(){
		return staticObjects;
	}
	public ArrayList<String> getWhoIsHere(){
		return whoIsHere;
	}
	public void clearArrays(){
		locationInfo = new String [100][3];
		connections = new ArrayList<String>();
		dynamicObjects = new ArrayList<String>();
		staticObjects = new ArrayList<String>();
		whoIsHere = new ArrayList<String>();
	}
}