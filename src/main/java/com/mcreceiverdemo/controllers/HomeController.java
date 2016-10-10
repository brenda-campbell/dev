package com.mcreceiverdemo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
import com.mcreceiverdemo.mvcmodels.DeData;
import com.mcreceiverdemo.mvcmodels.NameValue;
import com.mcreceiverdemo.security.CustomAuthenticationProvider;

@Controller
public class HomeController {
	
	//http://salesforce-marketingcloud.github.io/FuelSDK-Java/
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
    private ClientService mcClientService;

	//@Autowired
	//private CustomAuthenticationProvider authenticationProvider;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String index(final ApiLoginData apiLoginData, Model model) {
		logger.info("this is index.");
		
		model.addAttribute("msg", "this is home");
		
		model.addAttribute("isShowForm", (!this.mcClientService.isETClient()) );
		return "home";
    }
	
	@RequestMapping(value="/", params={"initiate"}, method=RequestMethod.POST)
    public String initiateApiAccess(@ModelAttribute("apiLoginData") @Valid final ApiLoginData apiLoginData, final BindingResult bindingResult, final ModelMap model) {
		if(bindingResult.hasErrors()) {
			return "home";
		}
		try {
			this.mcClientService.initiate(apiLoginData.getApiKey(), apiLoginData.getApiSecret());
			//UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("admin", "admin");
	        //token.setDetails(new WebAuthenticationDetails(request));
	        //Authentication authentication = this.authenticationProvider.authenticate(token);
	        //logger.debug("Logging in with [{}]", authentication.getPrincipal());
	        //SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (ETSdkException e) {
			model.addAttribute("msg", e.getMessage());
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
	
	@RequestMapping(value="/testError", method=RequestMethod.GET)
	public String testError(Model model) {
		throw new UnsupportedOperationException("not supported");
	}
	
}
