package org.opendevup.controller.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.opendevup.dao.MessageRepository;
import org.opendevup.entities.Message;
import org.opendevup.metier.MessageMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRestService {
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MessageMetier messageMetier;
	
	//method par defaut GET
	@Secured(value="ROLE_ADMIN")
	@RequestMapping(value="/message/nouveauMessage", method=RequestMethod.POST)
	//@RequestBody -> send data format Json, validation @Valid, BindinResult -> message d'erreur  
	public Object saveMessage(@RequestBody @Valid Message e, BindingResult bindingResult){
		//if errors exist 
		e.setDate_message(new Date());
		if(bindingResult.hasErrors()){
			Map<String,Object> errors=new HashMap<>();
			errors.put("erros", true);
			for(FieldError fe:bindingResult.getFieldErrors()){
				errors.put(fe.getField(), fe.getDefaultMessage());
			}
			return errors;
		}
		return messageRepository.save(e);
	}
	
	@Secured(value={"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="/message")
	public Page<Message> listMessages(int page,int size)
	{
		//return messageRepository.findAll(new PageRequest(page,size));	
		return messageRepository.listMessage(new PageRequest(page,size));	
	}
	
	//take user authentific
	@RequestMapping(value="/getLogedUser")
	public Map<String, Object> getLogedUser(HttpServletRequest httpServletRequest){
		HttpSession httpSession=httpServletRequest.getSession();
		SecurityContext securityContext=(SecurityContext)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username=securityContext.getAuthentication().getName();
		List<String> roles=new ArrayList<>();
		for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()){
			roles.add(ga.getAuthority());
		}
		Map<String, Object> params=new HashMap<>();
		params.put("username", username);
		params.put("roles", roles);
		return params;
	}
	

	//uSE METIER
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="message/deleteMessage/{id}")
	public String delete(@PathVariable long id){
		try {
			Message message = messageMetier.findById(id);
			messageMetier.delete(message);
		} catch (Exception e) {
			// TODO: handle exception 
			return "Error deleting the message : "+e.toString();
		}
		return "Message succefully delete ";
	}
	

	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="message/updateMessage/{id}",method=RequestMethod.PUT )
	public String update(@RequestBody Message message,@PathVariable long id){
		try {
			message.setId(id);
			messageMetier.save(message);
		} catch (Exception e) {
			// TODO: handle exception 
			return "Error updating the message : "+e.toString();
		}
		return "Message succefully updated ";
	}
	
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="message/editMessage/{id}",method=RequestMethod.GET)
	public Message getMessage(@PathVariable ("id")Long id){
		return messageRepository.findOne(id);
	}
	
}
