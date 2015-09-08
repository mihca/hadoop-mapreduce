package com.msg.xt.hadoop.acs.model;

import java.util.HashMap;
import java.util.Map;

public class StateResolver {

	private Map<String,State> states = new HashMap<String,State>();
	
	public StateResolver () {
		states.put("01", new State("01", "Alabama", "AL"));
		states.put("02", new State("02", "Alaska", "AK"));                               
		states.put("04", new State("04", "Arizona", "AZ"));                              
		states.put("05", new State("05", "Arkansas", "AR"));                           
		states.put("06", new State("06", "California", "CA"));           
		states.put("08", new State("08", "Colorado", "CO"));                             
		states.put("09", new State("09", "Connecticut", "CT"));                          
		states.put("10", new State("10", "Delaware", "DE"));                             
		states.put("11", new State("11", "District of Columbia", "DC"));                 
		states.put("12", new State("12", "Florida", "FL"));
		states.put("13", new State("13", "Georgia", "GA"));                              
		states.put("15", new State("15", "Hawaii", "HI"));                               
		states.put("16", new State("16", "Idaho", "ID"));                                
		states.put("17", new State("17", "Illinois", "IL"));                             
		states.put("18", new State("18", "Indiana", "IN"));                             
		states.put("19", new State("19", "Iowa", "IA"));                             
		states.put("20", new State("20", "Kansas", "KS"));                             
		states.put("21", new State("21", "Kentucky", "KY"));                             
		states.put("22", new State("22", "Louisiana", "LA"));                             
		states.put("23", new State("23", "Maine", "ME"));                             
		states.put("24", new State("24", "Maryland", "MD"));                             
		states.put("25", new State("25", "Massachusetts", "MA"));                             
		states.put("26", new State("26", "Michigan", "MI"));                             
		states.put("27", new State("27", "Minnesota", "MN"));                             
		states.put("28", new State("28", "Mississippi", "MS"));                             
		states.put("29", new State("29", "Missouri", "MO"));                             
		states.put("30", new State("30", "Montana", "MT"));                             
		states.put("31", new State("31", "Nebraska", "NE"));                             
		states.put("32", new State("32", "Nevada", "NV"));                             
		states.put("33", new State("33", "New Hampshire", "NH"));                             
		states.put("34", new State("34", "New Jersey", "NJ"));                             
		states.put("35", new State("35", "New Mexico", "NM"));                             
		states.put("36", new State("36", "New York", "NY"));                             
		states.put("37", new State("37", "North Carolina", "NC"));                             
		states.put("38", new State("38", "North Dakota", "ND"));                             
		states.put("39", new State("39", "Ohio", "OH"));                             
		states.put("40", new State("40", "Oklahoma", "OK"));                             
		states.put("41", new State("41", "Oregon", "OR"));                             
		states.put("42", new State("42", "Pennsylvania", "PA"));                             
		states.put("44", new State("44", "Rhode Island", "RI"));                             
		states.put("45", new State("45", "South Carolina", "SC"));                             
		states.put("46", new State("46", "South Dakota", "SD"));                             
		states.put("47", new State("47", "Tennessee", "TN"));                             
		states.put("48", new State("48", "Texas", "TX"));                             
		states.put("49", new State("49", "Utah", "UT"));                             
		states.put("50", new State("50", "Vermont", "VT"));                             
		states.put("51", new State("51", "Virginia", "VA"));                             
		states.put("53", new State("53", "Washington", "WA"));                             
		states.put("54", new State("54", "West Virginia", "WV"));                             
		states.put("55", new State("55", "Wisconsin", "WI"));                             
		states.put("56", new State("56", "Wyoming", "WY"));                             
		states.put("72", new State("72", "Puerto Rico", "PR"));                             
	}
	
	public String getStateText (String code) {
		if (states.get(code) != null)
			return states.get(code).getText();
		else
			return null;
	}

	public String getStateSign (String code) {
		if (states.get(code) != null)
			return states.get(code).getSign();
		else
			return null;
	}
}
