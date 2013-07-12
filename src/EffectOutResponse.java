import java.io.IOException;
/*
 * EffectOutResponse class
 * 	Responsibility: Carry responses from characters from the XML
 */
import java.util.ArrayList;
public class EffectOutResponse {
	XMLParse 	xP	= new XMLParse();
	Attributes 	attrib 	= new Attributes();
	static StringBuilder	sB	= new StringBuilder();
	static private 	String 	expt;

	/*
	 * Response Initiation
	 * 	#String
	 * 		@param 'doer' 			player or character taking an action
	 * 		@param 'target'			player or character being taken acting upon
	 * 		@param 'targetAttribute'target's XML attribute
	 * 		@param 'tStrE'			String value from XML
	 * 		@param 'tBooE'			Boolean value from XML ('t','f')
	 * 		@param 'tIntEEqu'		String value from XML of Integer Equals
	 * 	#Integer
	 * 		@param 'tIntEAdd'		Apply addition
	 * 		@param 'tIntESub'		Apply substitution
	 */
	public 	String responseInit(String 	doer,
					String 	target,
					String 	targetAttribute,
					String 	tStrE,
					String 	tBooE,
					String	tIntEEqu,
					int 		tIntEAdd,
					int 		tIntESub)
	throws IOException, InterruptedException {
		xP.xMLArraysWipe();

		xP.xMLParser("/invXMLs/attributesList");

		String 		responseOut = "";
		ValueWork 	vW 			= new ValueWork();
		Relations 	rel 		= new Relations();
		
		int change = vW.determineRelationChange(targetAttribute,
									tStrE,
									tBooE,
									tIntEEqu,
									tIntEAdd, 
									tIntESub);
		if (change == 0) {
			//If nothing is changed, give out a neutral response
			responseOut = neutralResponse(doer, target);
		} else {
			if (change > 0) {
				//If there is a positive change, Give a good response!
				responseOut = goodResponse(	doer,
								target,
								targetAttribute,
								tStrE,
								tBooE,
								tIntEEqu,
								tIntEAdd,
								tIntESub);
			} else {
				if (change < 0) {	
					//If there is a negative change, Give a bad response!
					responseOut = badResponse(	doer,
									target,
									targetAttribute,
									tStrE,
									tBooE,
									tIntEEqu, 
									tIntEAdd,
									tIntESub);
				}
			}
		}
		//player/causer to the target gets half of the change modified
		rel.setRelationOf(doer, target, (change / 2));
		//chracter to the player/causer gets raw change
		rel.setRelationOf(target, doer, change);

		if (responseOut.equals("")) {
			return responseOut;
		} else {
			return (responseOut + "\n");
		}
	}

	/*
	 * Good Response. Gives a positive response to the player
	 * or another NPC/character interacting with the doer.
	 * 	#String
	 * 		@param 'doer'				Either the player or a NPC taking the action
	 * 		@param 'target'				Player or NPC forwarding the action to a 
	 * 									character or player
	 * 		@param 'targetAttribute'	Target's attribute from XML
	 * 		@param 'tStrE'				target String Equation from XML
	 * 		@param 'tBooE'				target Boolean Equation from XML
	 * 		@param 'tIntEEqu'			target Integer Equation from XML
	 * 	#Integer
	 * 		@param 'tIntEAdd'			target Integer Addition in XML
	 * 		@param 'tIntSub'			target Integer Substitution in XML
	 * 
	 * 	@return String 'response' 		[String]/response said/taken action upon 
	 * 									by player or NPC
	 */
	public String goodResponse(	String 	doer,
					String 	target,
					String 	targetAttribute,
					String 	tStrE,
					String 	tBooE,
					String 	tIntEEqu,
					int 		tIntEAdd,
					int 		tIntESub )
	throws IOException, InterruptedException {
		float calc;
		String response;
		if (doer.equals(target)) {
			//System.out.print(attrib.findCharacterGender(target));
			response = (target + " makes themself happy.");
		} else {
			if (doer.equals("player")) {
				doer = GameDriver.getPlayerName();
			}
			response = (target + " says, \"Thanks " + doer + "!\" and smiles.");
		}
		return response;
	}
	
	/*
	 * Bad Response. Gives a negative response to the player
	 * or another NPC/character interacting with the doer.
	 * 	#String
	 * 		@param 'doer'				Either the player or a NPC taking the action
	 * 		@param 'target'				Player or NPC forwarding the action to a 
	 * 									character or player
	 * 		@param 'targetAttribute'	Target's attribute from XML
	 * 		@param 'tStrE'				target String Equation from XML
	 * 		@param 'tBooE'				target Boolean Equation from XML
	 * 		@param 'tIntEEqu'			target Integer Equation from XML
	 * 	#Integer
	 * 		@param 'tIntEAdd'			target Integer Addition in XML
	 * 		@param 'tIntSub'			target Integer Substitution in XML
	 * 
	 * 	@return String 'response' 		[String]/response said/taken action upon 
	 * 									by player or NPC
	 */
	public String badResponse(	String 	doer,
					String 	target,
					String 	targetAttribute,
					String 	tStrE,
					String 	tBooE,
					String 	tIntEEqu,
					int 		tIntEAdd,
					int 		tIntESub ) 
	throws IOException, InterruptedException {
		String response;
		if (doer.equals(target)) {
			response = (target + " makes themself sad.");
		} else {
			if (doer.equals("player")) {
				doer = GameDriver.getPlayerName();
			}
			response = (target + " says, \"Why would you do that to me " + doer
					+ "?!\" and then " + target + " frowns.");
		}

		return response;
	}

	/*
	 * Neutral Response. returns a neutral response to the
	 * target by the doer:{player/NPC}
	 * 	#String
	 * 		@param 'doer' 				Player or NPC taking action upon
	 * 		@param 'target'				Player or NPC action being taken upon
	 * 
	 *	@return String 'response' 		[String]/response said/taken action upon 
	 * 									by player or NPC
	 */
	public String neutralResponse(String doer, String target) {
		String response = "";

		if (doer.equals(target)) {
			response = (target + " and doesn't care.");
		} else {
			if (doer.equals("player")) {
				doer = GameDriver.getPlayerName();
			}
			response =
				(target + " says, \"Do you think this is a game " + doer + "?!\".");
		}

		return response;
	}
	/*
	 * Word decisions
	 * 	#String
	 * 		@param	'input'			OPTIONAL if it's not a question ask something
	 * 		@param	'question'		IF a question, answer it with a value
	 * 		@param	'effectiveWord'	A single effective word that has a higher value in
	 * 								modifying the bad-good for the context/sentence mood
	 * 		@param	'context'		Arrayed, separated words by every single space.
	 */
	public void wordChoice(String input,String question,String effectiveWord,String context){
		//simplify
		String 	in 		= input;
		String 	ques	= question;
		String 	eW		= effectiveWord;
		String 	cox 	= context;
		
		boolean isQ 		= false;
		float 	relation 	= (float) 0.0;	//bad-good type of determination
		
		if(relation>50.0){
			if(relation>75.0){
				relation = (float) ((float) relation / (float)2.0);
			}
		}else if(relation<50.0){
			if(relation<25.0){
				relation += (float) 0.10;
			}
		}else{
			relation = (float) 0.0;
		}
		
		if(question != null){
			input.toLowerCase();		//fix typos
			if(	input.contains("what")
			||	input.contains("who")
			||	input.contains("why")
			||	input.contains("when")
			||	input.contains("which")
			||	input.contains("how")){
				isQ = true;
			}else{
				isQ = false;
			}
			//if there's no question, ask something
				//If does not have
					//WHO
					//WHAT
					//WHY
					//WHEN
					//WHICH
					//HOW
			//it is not a question. Exceptions may varie in a library of question-like
			//sarting words, or if ends with a "?"
		}else{
			//if it's a question, answer with something related.
			
			//determine if the question is good/bad
			//Use from the library
					//WHO
					//WHAT
					//WHY
					//WHEN
					//WHICH
					//HOW
			//Relate to what will happen after getting these, and generate a sentence
			//related to the context of these words.
				//If some other form of question, use a different method of answering.
			//NOTICE: If ends with a "?" it is a question. Exceptions may varie.
		}
	}
	/*v0.1
	 * Mood detection of words
	 * 	#String
	 * 		@param 'context'	Takes in the paragraph and determines how the
	 * 							mood is going good or bad on a scale of bad-good.
	 * 	#Integer
	 * 		@param 'length'		The length of the paragraph.
	 * 		
	 */
	public void moodDetection(String context, int length){
		length = context.length();
		//context = toWords(context);	
	}
	
	///////////////////////////////////////////////////////
	//////Other programs can use these functions too.//////
	///////////////////////////////////////////////////////
	
	/*
	 * Sets a string into an array
	 * Name inspiration by PHP
	 * 	#String
	 * 		@param	'str'		Input string
	 * 		@param	'exception'	Input exception to split
	 * 
	 * 		@return	'explode'	Returns the final split array
	 */
	public static String[] explode(String str,String exception){
		expt = exception;
		String[] explode = str.split(exception);
		for(int i =0;i< explode.length;i++){
			sB.append(explode[i]);
			if(i!=explode.length-1){
				sB.append(" ");
			}
		}
		return explode;
	}

	/*
	 * Sets an array into a string
	 * Name inspiration by PHP
	 * 	#String
	 * 		@param[]'arr'		Input array
	 * 		@param	'exception'	Exception from global var 'expt' stored by explode()
	 * 
	 * 		@return	'implode'	Returns the final appended string
	 */
	public static String implode(String[] arr,String exception){
		//Get String from global var. If not safe, please make a new method!
		//Functions would be eh, but this is OK for now.
		exception = expt;
		if((exception==null)
		|| (exception=="")){
			//Set to [space] spacing if there is nothing.
			exception=" ";
		}
		String implode="";
		for(int i=0;i<arr.length;++i){
			if(i==arr.length-1){
				implode+=arr[i];
				break;
			}
			implode += arr[i] + exception;
		}
		return implode;
		
	}
	
<<<<<<< HEAD
	//serious 			response	--	new
	//angry 				response	--	bad or good or neutral
	//sad					response	--	bad or good or neutral
	//happy				response	--	bad or good or neutral
	//proud				response	--	bad or good or neutral
	//naive				response	--	bad or good or neutral
	//determined		response	--
=======
	//serious 		response	--	new
	//angry 		response	--	bad or good or neutral
	//sad			response	--	bad or good or neutral
	//happy			response	--	bad or good or neutral
	//proud			response	--	bad or good or neutral
	//naive			response	--	bad or good or neutral
	//determined	response	--
>>>>>>> d9c24faf6ffcf5e87cccd55faaf513f10b8ee9e5
	
}
