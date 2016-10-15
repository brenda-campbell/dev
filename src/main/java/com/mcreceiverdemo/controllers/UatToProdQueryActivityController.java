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

import com.mcreceiverdemo.mc.QueryActivityService;
import com.mcreceiverdemo.mvcmodels.MyKey;
import com.mcreceiverdemo.services.MyKeyCollectionService;

@Controller
public class UatToProdQueryActivityController {

	@Autowired
    private MyKeyCollectionService qaKeyCollectionService;
	
	@Autowired
	private QueryActivityService qaService;
	
	public UatToProdQueryActivityController() {
		super();
	}
	
	@RequestMapping(value="/qa/uattoprod", method=RequestMethod.GET)
	public String index(@ModelAttribute("qaKey") final MyKey qaKey) {
		return "qauattoprod";
	}
	
	
	@ModelAttribute("allQAKeys")
    public List<MyKey> populateDEKeys() {
        return this.qaKeyCollectionService.findAll();
    }
	
	@RequestMapping(value="/qa/uattoprod", params={"save"}, method=RequestMethod.POST)
    public String cloqauattoprod(final ModelMap model) throws Exception {
		List<MyKey> list = this.populateDEKeys();
		for (MyKey qaKey : list) {
			for(String key : qaKey.getKeys()) {
				qaService.uatToProd(key);
			}
		}
		this.qaKeyCollectionService.clear();
		model.clear();
        return "redirect:/qa/uattoprod";
    }
	
	@RequestMapping(value="/qa/uattoprod", params={"clearQueue"}, method=RequestMethod.POST)
    public String clearQueue(final ModelMap model) {
		this.qaKeyCollectionService.clear();
		model.clear();
        return "redirect:/qa/uattoprod";
    }
	
	@RequestMapping(value="/qa/uattoprod", params={"addToQueue"}, method=RequestMethod.POST)
    public String addToQueue(@ModelAttribute("qaKey") @Valid final MyKey qaKey, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
            return "qauattoprod";
        }
		this.qaKeyCollectionService.add(qaKey);
        model.clear();
        
        return "redirect:/qa/uattoprod";
    }
	
	@RequestMapping(value="/qa/uattoprod", params={"addKey"})
    public String addKey(@ModelAttribute("qaKey") final MyKey qaKey, final BindingResult bindingResult) {
		qaKey.getKeys().add("");
		return "qauattoprod";
    }
	
	@RequestMapping(value="/qa/uattoprod", params={"removeKey"})
    public String removeKey(@ModelAttribute("qaKey") final MyKey qaKey, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeKey"));
        qaKey.getKeys().remove(rowId.intValue());
        return "qauattoprod";
    }
}
