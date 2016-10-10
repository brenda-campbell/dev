package com.mcreceiverdemo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.mc.DataExtensionService;
import com.mcreceiverdemo.mvcmodels.MyKey;
import com.mcreceiverdemo.services.MyKeyCollectionService;

@Controller
public class UatToProdCloneDEController {
	
	@Autowired
    private MyKeyCollectionService deKeyCollectionService;
	
	@Autowired
    private DataExtensionService deService;
	
	public UatToProdCloneDEController() {
        super();
    }
	
	@RequestMapping(value="/de/uattoprod", method=RequestMethod.GET)
	public String index(@ModelAttribute("deKey") final MyKey deKey) {
		return "deuattoprod";
	}
	
	
	@ModelAttribute("allDEKeys")
    public List<MyKey> populateDEKeys() {
        List<MyKey> keys = this.deKeyCollectionService.findAll();
        return keys;
    }
	
	@RequestMapping(value="/de/uattoprod", params={"save"}, method=RequestMethod.POST)
    public String clodeUatToProd(final ModelMap model) throws Exception {
		List<MyKey> list = this.populateDEKeys();
		for (MyKey deKey : list) {
			for(String key : deKey.getKeys()) {
				deService.uatToProd(key);
			}
		}
		this.deKeyCollectionService.clear();
		model.clear();
        return "redirect:/de/uattoprod";
    }
	
	@RequestMapping(value="/de/uattoprod", params={"clearQueue"}, method=RequestMethod.POST)
    public String clearQueue(final ModelMap model) {
		this.deKeyCollectionService.clear();
		model.clear();
        return "redirect:/de/uattoprod";
    }
	
	@RequestMapping(value="/de/uattoprod", params={"addToQueue"}, method=RequestMethod.POST)
    public String addToQueue(@ModelAttribute("deKey") @Valid final MyKey deKey, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
            return "deuattoprod";
        }
		this.deKeyCollectionService.add(deKey);
        model.clear();
        
        return "redirect:/de/uattoprod";
    }
	
	@RequestMapping(value="/de/uattoprod", params={"addKey"})
    public String addKey(@ModelAttribute("deKey") final MyKey deKey, final BindingResult bindingResult) {
		deKey.getKeys().add("");
		return "deuattoprod";
    }
	
	@RequestMapping(value="/de/uattoprod", params={"removeKey"})
    public String removeKey(@ModelAttribute("deKey") final MyKey deKey, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeKey"));
        this.deKeyCollectionService.findAll().remove(rowId.intValue());
        return "deuattoprod";
    }
}
