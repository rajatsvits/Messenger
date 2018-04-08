package org.rajat.mahajan.messenger1.resources;

import java.net.URI;
import java.net.URL;
import java.util.List;

//import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.rajat.mahajan.messenger1.model.Message;
import org.rajat.mahajan.messenger1.resources.beans.MessageFilterBeans;
import org.rajat.mahajan.messenger1.service.MessageService;

import com.sun.jersey.api.ParamException.URIParamException;


//import org.rajat.mahajan.messenger1.resources.beans.*;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
@Produces(value = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
public class MessageResource {

//	@GET
//	@Produces("text/plain")
//	public String getMessages() {
//		return "Hello World"; 
	MessageService messageService = new MessageService();
	
	
////	default map to /messages
//	@GET
//	public List<Message> getMessage(@BeanParam MessageFilterBeans filterBeans ){
////		System.out.println(filterBeans.getYear());
//		if(filterBeans.getYear()>0)
//			 return messageService.getAllMesssageByYear(filterBeans.getYear());
//		if(filterBeans.getStart()>=0 &&filterBeans.getSize()>0) {
//			return messageService.getAllMesssageBySize(filterBeans.getStart(), filterBeans.getSize());
//		}
//		return messageService.getAllMessages();
//	}
//	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJSONMessage(@QueryParam("year") int year,
									@QueryParam("start") int start,
									@QueryParam("size") int size ){
		System.out.println("JSON method called");
	if(year>0)
		 return messageService.getAllMesssageByYear(year);
	if(start>=0 &&size>0) {
		return messageService.getAllMesssageBySize(start,size);
	}
	return messageService.getAllMessages();
}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getXMLMessage(@QueryParam("year") int year,
									@QueryParam("start") int start,
									@QueryParam("size") int size ){
		System.out.println("XML method called");
	if(year>0)
		 return messageService.getAllMesssageByYear(year);
	if(start>=0 &&size>0) {
		return messageService.getAllMesssageBySize(start,size);
	}
	return messageService.getAllMessages();
}
	
//	@GET
//	public List<Message> getMessage( ){
//		
//		return messageService.getAllMessages();
//	}
	
//	@POST
//	public ...
	
	
//	@Path("")//I am saying that after messages that if there is an extra element to the path map this method to it.
	
//	@GET
//	@Path("/test")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String test() {
//	// TODO Auto-generated method stub
//	 return "test method";
	
//	@GET
//	@Path("/{messageId}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String test() {
//	// TODO Auto-generated method stub
//	 return "test method";
	
//	@GET
//	@Path("{messageId}")
////	@Produces(MediaType.APPLICATION_JSON)//note url is string so we need to convert it but jersey do it for us automatically 
//	public Message test(@PathParam("messageId") long id) {//This is jersey feature saying whatever value is in path send it over here.
//	return messageService.getMessage(id); 
////	 return "test method "+messageId;
//	}
	
	@GET
	@Path("{messageId}") 
	public Message test(@PathParam("messageId") long id,@Context UriInfo uriInfo ) {
		Message message = messageService.getMessage(id);
//		message.addLinks(uriInfo.getAbsolutePathBuilder().path(id).build(), "self");
//		String uri = getUriToSelf(uriInfo, message);
//	
//		message.addLinks(uri, "self");
		message.addLinks(getUriToSelf(uriInfo, message), "self");
		message.addLinks(getUriToProfile(uriInfo, message), "profile");
//		message.addLinks(getUriToComments(uriInfo, message), "comments");
		return message;
	}

//	private String getUriToComments(UriInfo uriInfo, Message message) {
//	// TODO Auto-generated method stub
//		String uri = uriInfo.getBaseUriBuilder()
//				.path(MessageResource.class)
//				.path(Long.toString(message.getId()))
//				.path(CommentResource.class)
//				.build()
//				.toString();
//	return uri;
//}
	
	private String getUriToComments(UriInfo uriInfo, Message message) {
		// TODO Auto-generated method stub
			URI uri = uriInfo.getAbsolutePathBuilder()
					.path(MessageResource.class)
					.path(MessageResource.class, "getComments()")
					.path(CommentResource.class)
					.build()
					;
		return uri.toString();
	}

//	private String getUriToProfile(UriInfo uriInfo, Message message) {
//		String uri = uriInfo.getBaseUriBuilder()
//				.path(ProfileResource.class)
//				.path(message.getAuthorProfileName())
//				.resolveTemplate("messageId",message.getId())
//				.build()
//				.toString();
//				return uri;
//}

	private String getUriToProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthorProfileName())
				.build()
				.toString();
				return uri;
}

	private String getUriToSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
		.path(MessageResource.class)
		.path(Long.toString(message.getId()))
		.build()
		.toString();
		return uri;
	}
	
	
//	@POST
//	@Produces("text/plain")
//	public String addMessage() {
//		return "post works";
//	}
	
//	@POST
////	@Consumes(MediaType.APPLICATION_JSON)//Since this method consume message we have to add the consumes annotations
////	@Produces(MediaType.APPLICATION_JSON)
//	public Message addMessage(Message m) {
//		return messageService.addMessage(m);
////		return "post works";
//	}
	
//	@POST
//	public Response addMessage(Message m) {//Response is class which let us return a response itself.
//		Message newMessage = messageService.addMessage(m);
//		return Response.status(Status.CREATED)
//				.entity(newMessage)
//				.build();//We use build to build the instance of response as reponse here is a class
////		return messageService.addMessage(m);
//	}
	
//	@POST
//	public Response addMessage(Message m) throws URISyntaxException {
//		Message newMessage = messageService.addMessage(m);
//		return Response.created(new URI("/messager1/webresources/messages/"+newMessage.getId())) 
//				.entity(newMessage)
//				.build();
////		return messageService.addMessage(m);
//	}
	
	@POST
	public Response addMessage(Message m, @Context UriInfo uriInfo) throws URIParamException {//Response is class which let us return a response itself.

		Message newMessage = messageService.addMessage(m);
		System.out.println(uriInfo.getAbsolutePath());
		String newId = String.valueOf(newMessage.getId());
		System.out.println(newId);
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri) //this uri take server pakage as root package
				.entity(newMessage)
				.build();
	}
	
	@PUT
	@Path("{messageId}")
//	@Consumes(MediaType.APPLICATION_JSON)//Since this method consume message we have to add the consumes annotations
//	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id,Message m) {
		m.setId(id);
		return messageService.updateMessage(m);
//		return "put works";
	}
	
	@DELETE
	@Path("{messageId}")
//	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id) {
//		m.setId(id);
		messageService.removeMessage(id);
//		return "put works";
	}
	
	
	@Path("/{messageId}/comments")//We are passing the resonsiblilty to commentResource when ever we encounter this url.
	public CommentResource getComments() {
		return new CommentResource();
	}
}
