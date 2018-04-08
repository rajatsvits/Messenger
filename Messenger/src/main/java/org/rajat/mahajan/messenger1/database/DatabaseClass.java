package org.rajat.mahajan.messenger1.database;

import org.rajat.mahajan.messenger1.model.*;
import java.util.HashMap;
import java.util.Map;
public class DatabaseClass {

	//this is ideally the JDBC or hibernate class that ideally connect to database
	//It is static so that every object can access it.
	
	//One thing to note it is not threat safe because there is no concurrency protection over here but we are doing it under the 
	//assumption that there is one person and one developer who is learning jersey on your machine who is connecting to this class 
	private static Map<Long,Message> messages = new HashMap<Long, Message>();
	private static Map<String,Profile> profiles = new HashMap<String,Profile>();
	private static Map<Long, Comment> comments = new HashMap<Long, Comment>();
	
	public static Map<Long,Message> getMessage(){
		return messages;
	}
	
	public static Map<String,Profile> getProfiles(){
		return profiles;
	}
	
	public static Map<Long, Comment> getComments(){
		return comments;
	}
}
