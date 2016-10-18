package com.mcreceiverdemo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.mcreceiverdemo.exceptions.CustomException;
import com.mcreceiverdemo.mc.ClientService;
import com.mcreceiverdemo.mvcmodels.ApiLoginData;
import com.mcreceiverdemo.security.CustomAuthenticationProvider;
import com.mcreceiverdemo.security.CustomLogoutHandler;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;


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
		if(!CustomLogoutHandler.isLoggedIn() && this.mcClientService != null && this.mcClientService.isETClientObject()) {
			this.mcClientService.logoutClient();
		}
		//model.addAttribute("msg", "this is home");
		if(this.mcClientService.isETClientObject()) {
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
			String accessToken = null;
			if(apiLoginData.isApiLogin()) {
				accessToken = this.mcClientService.initiate(apiLoginData.getKey(), apiLoginData.getSecret());				
			}
			else {
				accessToken = this.mcClientService.login(apiLoginData.getKey(), apiLoginData.getSecret(), apiLoginData.getSoapEndpoint());
				this.mcClientService.setMID(1426297);
			}
			if(accessToken == null || accessToken.isEmpty()) {
				throw new CustomException("login unsuccessful. You shall not pass.");
			}
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(accessToken, "admin");
			//token.setDetails(new WebAuthenticationDetails(request));
	        Authentication authentication = this.authenticationProvider.authenticate(token);
	        logger.debug("Logging in with [{}]", authentication.getPrincipal());
	        			SecurityContextHolder.getContext().setAuthentication(authentication);
	        
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("panel","panel-danger");
			model.addAttribute("isShowForm", true );
			return "home";
		}
		
		model.clear();
        return "redirect:/";
    }
	
	@RequestMapping(value="/", params={"logout"}, method=RequestMethod.POST)
    public String logoutBtn(HttpServletRequest request, HttpServletResponse response) {
		this.doLogout(request, response);
        return "redirect:/";
    }
	
	private void doLogout(HttpServletRequest request, HttpServletResponse response) {
		this.mcClientService.logoutClient();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		SecurityContextHolder.clearContext();
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
