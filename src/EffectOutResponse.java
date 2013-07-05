import java.io.IOException;
/*v0.3
 * EffectOutResponse class
 * 	Responsibility: Carry responses from characters from the XML
 */
public class EffectOutResponse {
	XMLParse xP = new XMLParse();

	/*
	 * Response Initiation
	 * #String
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
	public String responseInit(	String 	doer,
                              String 	target,
                              String 	targetAttribute,
                              String 	tStrE,
                              String 	tBooE,
                              String	tIntEEqu,
                              int 		tIntEAdd,
                              int 		tIntESub)
	throws IOException, InterruptedException {
		xP.xMLArraysWipe();

		xP.xMLParser("defaults/MLTG/invXMLs/attributesList");

		String responseOut = "";

		ValueWork vW 	= new ValueWork();

		Relations rel = new Relations();
		
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
		String response;

		if (doer.equals(target)) {
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
	
	//serious 		response	--	new
	//angry 			response	--	bad or good or neutral
	//sad					response	--	bad or good or neutral
	//happy				response	--	bad or good or neutral
	//proud				response	--	bad or good or neutral
	//naive				response	--	bad or good or neutral
	//determined	response	--
	
}
