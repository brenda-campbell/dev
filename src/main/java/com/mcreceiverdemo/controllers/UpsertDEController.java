package com.mcreceiverdemo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.mcreceiverdemo.mvcmodels.MyData;
import com.mcreceiverdemo.mvcmodels.NameValue;
import com.mcreceiverdemo.services.MyDataCollectionService;

@Controller
public class UpsertDEController {
	
	@Autowired
    private MyDataCollectionService deDataCollectionService;
	
	@Autowired
    private DataExtensionService deService;
	
	public UpsertDEController() {
        super();
    }
	
	@ModelAttribute("allDEData")
    public List<MyData> populateDEData() {
        return this.deDataCollectionService.findAll();
    }
	
	@RequestMapping(value="/de/upsertde", method=RequestMethod.GET)
	public String dynamicForm(@ModelAttribute("deData") final MyData deData) {
		return "upsertde";
	}
	
	
	@RequestMapping(value="/de/upsertde", params={"addToQueue"}, method=RequestMethod.POST)
    public String addToQueue(@ModelAttribute("deData") @Valid final MyData deData, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "upsertde";
        }
        this.deDataCollectionService.add(deData);
        model.clear();
        return "redirect:/de/upsertde";
    }
	
	
	@RequestMapping(value="/de/upsertde", params={"save"}, method=RequestMethod.POST)
    public String saveDEData(final ModelMap model) throws ETSdkException {
		List<MyData> list = this.populateDEData();
		for (MyData deData : list) {
			Map<String,String> data = new HashMap<String, String>();
			for(NameValue pair : deData.getNameValues()) {
				data.put(pair.getName(), pair.getValue());
			}
		    deService.upsert(deData.getKey(), data );
		}
		this.deDataCollectionService.clear();
		model.clear();
        return "redirect:/de/upsertde";
    }
	
	@RequestMapping(value="/de/upsertde", params={"clearQueue"}, method=RequestMethod.POST)
    public String clearQueue(final ModelMap model) throws ETSdkException {
		this.deDataCollectionService.clear();
		model.clear();
        return "redirect:/de/upsertde";
    }
	
	
	@RequestMapping(value="/de/upsertde", params={"addNameValue"})
    public String addRow(@ModelAttribute("deData") final MyData deData, final BindingResult bindingResult) {
        deData.getNameValues().add(new NameValue());
        return "upsertde";
    }
    
    
    @RequestMapping(value="/de/upsertde", params={"removeNameValue"}, method=RequestMethod.POST)
    public String removeRow(@ModelAttribute("deData") final MyData deData, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeNameValue"));
        deData.getNameValues().remove(rowId.intValue());
        return "upsertde";
    }
	
}
