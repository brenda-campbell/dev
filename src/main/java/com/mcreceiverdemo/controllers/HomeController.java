package com.mcreceiverdemo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.mc.ClientService;
import com.mcreceiverdemo.mc.DataExtensionService;
import com.mcreceiverdemo.mvcmodels.ApiLoginData;
import com.mcreceiverdemo.mvcmodels.MyData;
import com.mcreceiverdemo.mvcmodels.NameValue;

import com.mcreceiverdemo.security.CustomAuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;


@Controller
public class HomeController {
	
	//http://salesforce-marketingcloud.github.io/FuelSDK-Java/
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
    private ClientService mcClientService;

	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String index(final ApiLoginData apiLoginData, Model model) {
		logger.info("this is index.");
		
		//model.addAttribute("msg", "this is home");
		if(this.mcClientService.isETClient()) {
			model.addAttribute("isShowForm", false );
			model.addAttribute("panel","panel-success");
		}
		else {
			model.addAttribute("isShowForm", true );
		}
		return "home";
    }
	
	@RequestMapping(value="/", params={"initiate"}, method=RequestMethod.POST)
    public String initiateApiAccess(@ModelAttribute("apiLoginData") @Valid final ApiLoginData apiLoginData, final BindingResult bindingResult, final ModelMap model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("panel","panel-danger");
			model.addAttribute("isShowForm", true );
			return "home";
		}
		try {
			if(apiLoginData.isApiLogin()) {
				this.mcClientService.initiate(apiLoginData.getKey(), apiLoginData.getSecret());
			}
			else {
				this.mcClientService.login(apiLoginData.getKey(), apiLoginData.getSecret(), apiLoginData.getSoapEndpoint());
			}
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("admin", "admin");
	        //token.setDetails(new WebAuthenticationDetails(request));
	        Authentication authentication = this.authenticationProvider.authenticate(token);
	        logger.debug("Logging in with [{}]", authentication.getPrincipal());
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        
		} catch (ETSdkException e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("panel","panel-danger");
			model.addAttribute("isShowForm", true );
			return "home";
		}
		model.clear();
        return "redirect:/";
    }
	
	@RequestMapping(value="/", params={"logout"}, method=RequestMethod.POST)
    public String logout(final ModelMap model) {
		this.mcClientService.logoutClient();
		SecurityContextHolder.clearContext();
        return "redirect:/";
    }
	
	
	@RequestMapping(value="/de", method=RequestMethod.GET)
    public String de(Model model) {
		logger.info("this is de landing.");
		return "delanding";
    }
	
	@RequestMapping(value="/qa", method=RequestMethod.GET)
    public String qa(Model model) {
		logger.info("this is qa landing.");
		return "qalanding";
    }
	
	@RequestMapping(value="/folder", method=RequestMethod.GET)
    public String folder(Model model) {
		logger.info("this is folder landing.");
		return "folderlanding";
    }
	
	@RequestMapping(value="/testError", method=RequestMethod.GET)
	public String testError(Model model) {
		throw new UnsupportedOperationException("not supported");
	}
	
}
