package org.opendevup;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class DocGesMVCConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//correspondance entre controller et views
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout").setViewName("login");
	}
	
	//equivalent un controleur 
	/*@Controller
	 * public class PersonnelController{
	 * @RequestMapping("/login")
	 * public String login(){
	 * 	return "login";
	 * }
	 * }*/
}
