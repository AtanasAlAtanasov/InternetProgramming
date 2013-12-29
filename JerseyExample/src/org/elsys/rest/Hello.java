package org.elsys.rest;

import java.util.Date;
import java.util.LinkedList;
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

import org.elsys.entities.Message;

@Path("hello")
public class Hello {
	final static List<Message> messages = new LinkedList<Message>();
	
	@GET
	@Path("test")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Message> getHello() {
		return messages;
	}
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addMessage(Message m) {
		m.setCreated(new Date());
		m.setId(System.currentTimeMillis());
		messages.add(m);
	}
	
	@GET
	@Path("{id}")
	public Message getMessage(@PathParam("id") long id) {
		for (Message next : messages) {
			if (next.getId() == id) {
				return next;
			}
		}
		return null;
	}
	@DELETE
	@Path("{id}")
	public void removeMessage(@PathParam ("id") long id) {
		for (Message next : messages) {
			if (next.getId() == id) {
				messages.remove(next);
				return;
			}
		}
	}

	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateMessage(Message updated) {
		removeMessage(updated.getId());
		//no check if already existing
		messages.add(updated);
	}
}
