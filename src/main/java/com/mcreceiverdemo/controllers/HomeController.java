package com.mcreceiverdemo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	//http://salesforce-marketingcloud.github.io/FuelSDK-Java/
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String index(Model model) {
		logger.info("this is index.");
		
		model.addAttribute("msg", "this is home");
        return "home";
    }
	
	@RequestMapping(value="/de", method=RequestMethod.GET)
    public String de(Model model) {
		logger.info("this is de landing.");
		return "delanding";
    }
	
	@RequestMapping(value="/testError", method=RequestMethod.GET)
	public String testError(Model model) {
		throw new UnsupportedOperationException("not supported");
	}
	
}
