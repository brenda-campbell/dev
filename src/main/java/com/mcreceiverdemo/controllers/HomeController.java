package com.mcreceiverdemo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETDataExtension;
import com.exacttarget.fuelsdk.ETDataExtensionRow;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETResponse;


@Controller
public class HomeController {
	
	//http://salesforce-marketingcloud.github.io/FuelSDK-Java/
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String index(Model model) {
		logger.info("this is index.");
		try {
			ETClient etClient = new ETClient();
			String clientId = etClient.getClientId();
			String accessToken = etClient.getAccessToken();
			model.addAttribute("clientId", clientId);
			model.addAttribute("accessToken", accessToken);
			
			//ETFilter filter = new ETFilter("Name", ETFilter.Operator.EQUALS, "products");
			
			ETDataExtension de = new ETDataExtension();  //client.retrieveDataExtension(filter);
			de.setKey("8B1A73D6-8EFA-4A7D-AB43-88663EB9AD28");
			de.setClient(etClient);
			logger.info(de.getName());
			
			//add a row to the data extension "products"
			ETDataExtensionRow row = new ETDataExtensionRow();
			row.setColumn("email", "alinahid+api1@gmail.com");
			row.setColumn("name", "ali api");
			ETResponse response = de.insert(row);
			
			model.addAttribute("msg", "Response : " + response.getResponseCode() + " : " + response.getResponseMessage());
			
		}catch(Exception e) {
			String fullStackTrace = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
			logger.error("etClientX-----> ", fullStackTrace);
			
			model.addAttribute("msg", "Error : " + fullStackTrace);
		}
		
        return "home";
    }
}
