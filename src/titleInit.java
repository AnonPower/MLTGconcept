import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class titleInit
{
	String titleGen;
	
    public void startTitle() throws IOException
    {
    	outManipulation oM = new outManipulation();
    	
		//set and fetch title.
		setTitleGen("/titleGen/titleList");
		System.out.print(getTitleGen());
		oM.pauseTime(2);
		
		System.out.print("\n\n\n");
    }
    //Using the random number generator, picks a random line
    //from the titlelist.txt to pick a portion of the title to be displayed
    //each time the game is executed.
	public void setTitleGen(String dir) throws IOException
	{
		fileWork fW = new fileWork();
		ranGen rG = new ranGen();
		
		int ranNum,
			counter = 0;
		
		BufferedReader bis = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(dir)));
		
		//calls on the generator with parameter of 4 (being 4 different title possibilities in the .txt document)
		rG.setRanNum(fW.fileLineCount(dir));
		ranNum = rG.getRanNum();
		//ranNum = the number that was generated based on the parameter
		//a line is read each loop, and loops until the counter is equivalent to the randomly generated number.
		//which determines the same line in the .txt document that will be used for the title.
		while(counter != ranNum)
		{	
			counter++;
			titleGen = bis.readLine();
		}
		bis.close();
	}
	
	public String getTitleGen()
	{
		return titleGen;
	}
}