import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class FileWork
{
	public int fileLineCount(String dir) throws IOException
	{
		BufferedReader bis = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(dir)));
		
		String readIn;
		int counter = 0;
		
		try
		{
			do
			{
				readIn = bis.readLine();
				if(readIn.equals(null))
				{
				}
				else
				{
					counter++;
				}
			}while(readIn != null);
		}
		catch(Exception e)
		{
		}
		bis.close();
		return counter;
	}
	
	public BufferedReader fileSearch(String searchFor, BufferedReader bis) throws IOException
	{
		String readIn = bis.readLine();
		
		while(readIn.contains(searchFor) == false)
		{
			if(readIn.contains(searchFor))
			{
				break;
			}
			else
			{
				readIn = bis.readLine();
			}
		}
		return bis;
	}

	public void fileOverwrite(String oldFileDir, String newFileDir)
	{
		File oldFile = new File(oldFileDir);
		File newFile = new File(newFileDir);
		
		try
		{
			oldFile.delete();
			newFile.renameTo(oldFile);
		}
		catch(Exception e)
		{
		}
	}
}