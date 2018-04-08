package org.rajat.mahajan.messenger1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.rajat.mahajan.messenger1.database.DatabaseClass;
import org.rajat.mahajan.messenger1.model.Comment;
import org.rajat.mahajan.messenger1.model.ErrorMessage;
import org.rajat.mahajan.messenger1.model.Message;

public class CommentService {

	
private Map<Long, Message> messages = DatabaseClass.getMessage();
	
//	public CommentService(){
//		comments.put(1L, new Comment(1L,"Hey Next","rajat" ));
//		comments.put(2L, new Comment(2L, "Wanna Go on a date today ", "Rajat"));
//		comments.put(3L, new Comment(3L, "I want to kiss you now", "Rajat"));
//		comments.put(4L, new Comment(4L, "Let have coffee in my room", "Rajat"));
//		
//	}
	public List<Comment> getAllComments(long messageId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
//	public List<Comment> getAllMesssageByYear(long year){
//		List<Comment> commentsForYear = new ArrayList<Comment>();
//		Calendar cal = Calendar.getInstance();
//		for(Comment Comment : comments.values()) {
//			cal.setTime(Comment.getCreated());
//			if(cal.get(Calendar.YEAR)==year) {
//				commentsForYear.add(Comment);
//			}
//		}
//		return commentsForYear;
//	}
//	
//	public List<Comment> getAllMesssageBySize(int start,int size){
//		ArrayList<Comment> list  = new ArrayList<Comment>(comments.values());
//		if(start+size>list.size())
//			return new ArrayList<Comment>();
//		return list.subList(start,start+size);
//	}
	
	public Comment getComment(long messageId,long pN) {

		ErrorMessage errorMessage = new ErrorMessage("Lets have sex",404);
		Response response = Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
		
		Message message = messages.get(messageId);
		if(message==null) {
			throw new  WebApplicationException(response);
//			throw new NotFoundException(response);
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment =  comments.get(pN);
		if(comment==null) {
			throw new WebApplicationException(response);
//			throw new NotFoundException(response);
		}
		return comment;
	}
	
	public Comment addComment(long messageId,Comment Comment) {

		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment.setId(comments.size()+1);
		comments.put(Comment.getId(), Comment);
		return Comment;
		
	}
	
	public Comment updateComment(long messageId,Comment Comment) {

		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(Comment.getId()<=0)
			return null;
		comments.put(Comment.getId(),Comment);
		return Comment;
	}
	
	public Comment removeComment(long id) {

		Map<Long, Comment> comments = DatabaseClass.getComments();
		return comments.remove(id);
	}
}
