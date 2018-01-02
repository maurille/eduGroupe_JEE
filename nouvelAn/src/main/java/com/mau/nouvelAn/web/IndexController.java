package com.mau.nouvelAn.web;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mau.nouvelAn.metier.Message;
import com.mau.nouvelAn.repositories.IMessageDepot;

@Controller
@RequestMapping(value="/")
public class IndexController {
	
	@Autowired
	private IMessageDepot messageDepot;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirectToIndex() {
		return "redirect:/Index";
	}

	
	@RequestMapping(value = "/Index", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "bonjour depuis spring 3 mvc");
		return "bonjour";

	}

	@RequestMapping(value = "/bonjour/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("bonjour");
		model.addObject("message", "bonjour " + name);

		return model;

	}
	
	@RequestMapping(value="/messages")
	public ModelAndView liste() {
		// on affichera via la page messages.jsp
		ModelAndView model = new ModelAndView("messages");
		
		// on transmet a cette page la  collection des messages
		model.addObject("messages", messageDepot.findAll());
		
		return model;	
	}
	
	@RequestMapping(value="/addMessage", method=RequestMethod.POST)
	public String addMessage(@RequestParam("titre") String titre, @RequestParam("corps")  String corps) {
		
		Message m= new Message(0, titre, corps);
		messageDepot.save(m);
		return "redirect:/messages";
	}

}