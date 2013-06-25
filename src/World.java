import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
public class World {
	File worldFile;
	File worldFolder;
	public void worldInit() {
		worldFolder = new File("/save/worldXMLs");
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
		try {
			if (worldFolder.listFiles().length >= 1) {
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
				
			}
			else
			{
				
			}
		} catch (Exception e) {
		}
	}
}