package org.opendevup.metier;

import org.opendevup.dao.MessageRepository1;
import org.opendevup.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageMetier {
	@Autowired
	private MessageRepository1 messageRepository1; 
	public Object findAll()
	{
		return messageRepository1.findAll();
	}
	
	public Message findById(Long id)
	{
		return messageRepository1.findOne(id);
	}
	
	public Message save(Message message)
	{
		return messageRepository1.save(message);
	}
	
	public void delete(Message message)
	{
		messageRepository1.delete(message);
	}
	
}
