package org.example.resources;

import org.example.model.Message;
import org.example.resources.beans.MessageFilterBean;
import org.example.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@QueryParam("year") int year,
                                     @QueryParam("start") int start,
                                     @QueryParam("size") int size) {
        if (year > 0) {
            return messageService.getAllMessagesForYear(year);
        }
        if (start >=0 && size > 0) {
            return messageService.getAllMessagesPaginated(start, size);
        }
        return messageService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long id) {
        return messageService.getMessage(id);
    }

    @POST
    public Message addMessage(Message message) {
        return messageService.addMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long id, Message message) {
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long id) {
        messageService.removeMessage(id);
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }



//    вариант с BeanParam. все аннотации в классе MessageFilterBean в директории beans
//    @GET
//    public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
//        if (filterBean.getYear() > 0) {
//            return messageService.getAllMessagesForYear(filterBean.getYear());
//        }
//        if (filterBean.getStart() >=0 && filterBean.getSize() > 0) {
//            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
//        }
//        return messageService.getAllMessages();
//    }
}
