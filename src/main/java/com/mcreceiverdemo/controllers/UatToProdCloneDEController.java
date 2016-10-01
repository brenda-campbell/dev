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
import com.mcreceiverdemo.mvcmodels.DeKey;
import com.mcreceiverdemo.services.DeKeyCollectionService;

@Controller
public class UatToProdCloneDEController {
	
	@Autowired
    private DeKeyCollectionService deKeyCollectionService;
	
	@Autowired
    private DataExtensionService deService;
	
	public UatToProdCloneDEController() {
        super();
    }
	
	@RequestMapping(value="/de/uattoprod", method=RequestMethod.GET)
	public String index(final DeKey deKey) {
		return "uattoprod";
	}
	
	
	@ModelAttribute("allDEKeys")
    public List<DeKey> populateDEKeys() {
        return this.deKeyCollectionService.findAll();
    }
	
	@RequestMapping(value="/de/uattoprod", params={"save"}, method=RequestMethod.POST)
    public String clodeUatToProd(final ModelMap model) throws ETSdkException {
		List<DeKey> list = this.populateDEKeys();
		for (DeKey deKey : list) {
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
    public String addToQueue(@ModelAttribute("deKey") @Valid final DeKey deKey, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
            return "uattoprod";
        }
		this.deKeyCollectionService.add(deKey);
        model.clear();
        
        return "redirect:/de/uattoprod";
    }
	
	@RequestMapping(value="/de/uattoprod", params={"addKey"})
    public String addKey(final DeKey deKey, final BindingResult bindingResult) {
		deKey.getKeys().add("");
		return "uattoprod";
    }
	
	@RequestMapping(value="/de/uattoprod", params={"removeKey"})
    public String removeKey(final DeKey deKey, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeKey"));
        this.deKeyCollectionService.findAll().remove(rowId.intValue());
        return "uattoprod";
    }
}
