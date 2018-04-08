package org.rajat.mahajan.messenger1.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.rajat.mahajan.messenger1.database.DatabaseClass;
import org.rajat.mahajan.messenger1.exception.DataNotFoundException;
import org.rajat.mahajan.messenger1.model.Message;
public class MessageService {


//	version one
//	public List<Message> getAllMessages(){
//		Message m1 = new Message(1L, "Hey", "Rajat");
//		Message m2 = new Message(2L, "Wanna Go on a date", "Rajat");
//		List<Message> list =new ArrayList<Message>();
//		list.add(m1);
//		list.add(m2);
//		return list;
//		
//	}
	
	
	
	private Map<Long,Message> messages = DatabaseClass.getMessage();
	
	
	
	public MessageService(){
		messages.put(1L, new Message(1L, "Hey", "Rajat","Rj_mahajan"));
		messages.put(2L, new Message(2L, "Wanna Go on a date", "Rajat","Rj_mahajan"));
		messages.put(3L, new Message(3L, "I want to kiss you", "Rajat","Rj_mahajan"));
		messages.put(4L, new Message(4L, "Let have a coffee", "Rajat","Rj_mahajan"));
		
	}
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMesssageByYear(int year){
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMesssageBySize(int start,int size){
		ArrayList<Message> list  = new ArrayList<Message>(messages.values());
		if(start+size>list.size()) 
			return new ArrayList<Message>();
		return list.subList(start,start+size);
	}
	
	public Message getMessage(long id) {
//		return messages.get(id);
		Message message = messages.get(id);
		if(message==null) {
			throw new DataNotFoundException("Message with id "+id+" not found");
		}
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
		
	}
	
	public Message updateMessage(Message message) {
		if(message.getId()<=0)
			return null;
		messages.put(message.getId(),message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
