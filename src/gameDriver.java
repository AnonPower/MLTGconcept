import java.io.IOException;

public class gameDriver
{
	static String savedOutput = "";
	
	public static void main(String [] args) throws IOException, InterruptedException
	{
		nPCBrain nB = new nPCBrain();
		titleInit tI = new titleInit();
		inputCollect iC = new inputCollect();
		uInVerify uIV = new uInVerify();
		
		tI.startTitle();
		System.out.print("\nAt anytime you may enter \"help\" for the list of available commands.\n");
		
		while(true)
		{
			iC.setCommand();
			uIV.inputVariablesWipe();
			nB.setNPCCommand();
			savedOutput = "";
		}
	}
}