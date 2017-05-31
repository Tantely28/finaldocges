package org.opendevup.controller.serveur;


import org.opendevup.dao.MessageRepository;
import org.opendevup.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/Message")
public class MessageController {
	
	//@autowired -> inject dependency : when spring search an object implements this interface 
	@Autowired
	private MessageRepository messageRepository;  
	
	//@RequestParam(name="page")int p){ : search me a param page on affect p=0 default
	
	// result:create view Message when you write url "/Index", he call method Index: traitment and name view
	@RequestMapping(value="/Index")
	public String Index(Model model,@RequestParam(name="page",defaultValue="0")int p,@RequestParam(name="motCle",defaultValue="")String mc){
		//Page<Message> pageMessage=messageRepository.findAll(new PageRequest(p,5));
		
		Page<Message> pageMessage=messageRepository.searchMessage("%"+mc+"%", new PageRequest(p,5));
		
		//number of page controlleur
		int pagescount=pageMessage.getTotalPages();
		int[] pages=new int[pagescount];
		for(int i=0 ;i<pagescount;i++) pages[i]=i;
		//stocker dans le model
		model.addAttribute("pages",pages);
		
		//before to go views , you stock message in model
		model.addAttribute("pageMessage",pageMessage);
		model.addAttribute("pageCourante",p);
		
		//you stock mc in db
		model.addAttribute("motCle",mc);
		return "message";
	}

}
