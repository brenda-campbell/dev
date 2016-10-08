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
import com.mcreceiverdemo.mvcmodels.DeData;
import com.mcreceiverdemo.mvcmodels.NameValue;

@Controller
public class HomeController {
	
	//http://salesforce-marketingcloud.github.io/FuelSDK-Java/
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
    private ClientService mcClientService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String index(final ApiLoginData apiLoginData, Model model) {
		logger.info("this is index.");
		
		model.addAttribute("msg", "this is home");
		
		model.addAttribute("isShowForm", (!this.mcClientService.isETClient()) );
		return "home";
    }
	
	@RequestMapping(value="/", params={"initiate"}, method=RequestMethod.POST)
    public String saveDEData(@ModelAttribute("apiLoginData") @Valid final ApiLoginData apiLoginData, final BindingResult bindingResult, final ModelMap model) {
		if(bindingResult.hasErrors()) {
			return "home";
		}
		try {
			this.mcClientService.initiate(apiLoginData.getApiKey(), apiLoginData.getApiSecret());
		} catch (ETSdkException e) {
			model.addAttribute("msg", e.getMessage());
			return "home";
		}
		model.clear();
        return "redirect:/";
    }
	
	@RequestMapping(value="/", params={"logout"}, method=RequestMethod.POST)
    public String logout(final ModelMap model) {
		this.mcClientService.logoutClient();
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
