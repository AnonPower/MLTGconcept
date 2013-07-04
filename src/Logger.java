import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
public class Logger {
	PrintWriter pw;
	FileWriter fw;
	File logFile;
	public void writeToLog(String output) throws IOException{
		logFile = new File("data/MLTG/save/log");
		try {
			if(logFile.exists()){
				fw = new FileWriter(logFile, true);
				fw.write(output);
				fw.close();
			}else{
				pw = new PrintWriter(logFile);
				logFile.mkdirs();
				logFile.createNewFile();
				pw.write(output);
				pw.close();
			}
		} catch (Exception logNotFoundWr) {
			pw = new PrintWriter(logFile);
			logFile.mkdirs();
			logFile.createNewFile();
			pw.write(output);
			pw.close();
		}
	}
	public void autoSave() throws IOException{
		logFile = new File("data/MLTG/save/log");
		String readIn;
		BufferedReader bis = null;
		try {
			if(logFile.exists()){
				bis = new BufferedReader(new InputStreamReader(
						new FileInputStream(logFile)));
				do{
					readIn = bis.readLine();
				}while(readIn.equals(null) == false);
			}
		} catch (Exception logNotFoundSa) {}
		bis.close();
	}
}