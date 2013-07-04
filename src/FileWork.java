import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileWork {
	public int fileLineCount(String dir) throws IOException {
		BufferedReader bis = new BufferedReader(new InputStreamReader(
				getClass().getResourceAsStream(dir)));

		String readIn;
		int counter = 0;

		try {
			do {
				readIn = bis.readLine();
				if (readIn.equals(null)) {
				} else {
					counter++;
				}
			} while (readIn != null);
		} catch (Exception e) {
		}
		bis.close();
		return counter;
	}

	public BufferedReader fileSearch(String searchFor, BufferedReader bis)
			throws IOException {
		String readIn = bis.readLine();

		while (readIn.contains(searchFor) == false) {
			if (readIn.contains(searchFor)) {
				break;
			} else {
				readIn = bis.readLine();
			}
		}
		return bis;
	}

	public void fileOverwrite(String oldFileDir, String newFileDir) {
		File oldFile = new File(oldFileDir);
		File newFile = new File(newFileDir);

		try {
			oldFile.delete();
			newFile.renameTo(oldFile);
		} catch (Exception e) {
		}
	}
	public void fileCopy(String origFileDir, String fileDestDir) {
		File orig = new File(origFileDir);
		File dest = new File(fileDestDir);

		try {
			if (orig.exists()) {
				String readIn;
				PrintWriter pw;
				BufferedReader bis = new BufferedReader(new InputStreamReader(
						new FileInputStream(orig)));
				try {
					if(dest.exists()){
						File dest2 = new File(fileDestDir + "_2");
						pw = new PrintWriter(dest2);
					}else{
						pw = new PrintWriter(dest);
					}
				} catch (Exception destNotFound) {
					pw = new PrintWriter(dest);
				}
				readIn = bis.readLine();
				pw.write(readIn);
				try {
					while(readIn.equals(null)== false){
						readIn = bis.readLine();
						try {
							if(readIn.equals(null)){
								break;
							}
						} catch (Exception e) {
							break;
						}
						pw.write(readIn);
					}
					pw.close();
					bis.close();
				} catch (Exception e) {}
			}
		} catch (Exception origNotFound) {}
	}
	public void findInInventories(String toFind, String type)
			throws IOException, InterruptedException {
		XMLParse xP = new XMLParse();

		if (type.equalsIgnoreCase("noun")) {
			xP.xMLParser("defaults/MLTG/invXMLs/characters");

			if (xP.tID1.contains(toFind) || xP.tID2.contains(toFind)
					|| xP.tID3.contains(toFind)) {

			}
		} else {
			if (type.equalsIgnoreCase("any")) {

			}
		}
	}
}