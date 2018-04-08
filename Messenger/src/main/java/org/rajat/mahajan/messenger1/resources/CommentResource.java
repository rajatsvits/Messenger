package org.rajat.mahajan.messenger1.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.rajat.mahajan.messenger1.model.Comment;
import org.rajat.mahajan.messenger1.service.CommentService;

@Path("/")//Default value as we importing path form message resource
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

	CommentService CommentService = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return CommentService.getAllComments(messageId);
	}
	
//	@GET
//	@Path("{messageId}")
//	public Comment getComment(@PathParam("messageId") long id) {
//		String commentId = Comment.getcommentId();
//		return CommentService.getComment(commentId);
//	}
	
	@GET
	@Path("{commentId}")
	public Comment getComment(@PathParam("commentId") long pN, @PathParam("messageId") long messageId) {
		return CommentService.getComment(messageId,pN);
	}
	
	@POST
	public Comment addComment(Comment Comment,@PathParam("messageId") long messageId) {
		return CommentService.addComment(messageId,Comment);
		
	}
	
	@PUT
	@Path("{commentId}")
	public Comment updateComment(@PathParam("commentId") long commentId,@PathParam("messageId") long messageId ) {
		Comment Comment = CommentService.getComment(messageId,commentId);
		CommentService.updateComment(messageId,Comment);
		return Comment;
	}
	
	@DELETE
	@Path("{commentId}")
	public void deleteComment(@PathParam("commentId") long id) {
		CommentService.removeComment(id);
	}
}
