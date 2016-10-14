package com.mcreceiverdemo.controllers;

import java.util.ArrayList;
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

import com.mcreceiverdemo.et.APIObjectExtended;
import com.mcreceiverdemo.et.ETRetrieveDataExtensionObject;
import com.mcreceiverdemo.mc.FolderSearchService;
import com.mcreceiverdemo.mvcmodels.MyData;
import com.mcreceiverdemo.mvcmodels.MyKey;
import com.mcreceiverdemo.services.MyDataCollectionService;
import com.mcreceiverdemo.services.MyKeyCollectionService;

@Controller
public class FolderSearchController {
	
	@Autowired
    private MyKeyCollectionService folderPathCollectionService;
	
    private List<APIObjectExtended> apiList;
	
	@Autowired
    private FolderSearchService folderSearchService;
	
	public FolderSearchController(){
		super();
		this.apiList = new ArrayList<APIObjectExtended>();
	}
	
	@ModelAttribute("allAPIObjects")
    public List<APIObjectExtended> populateETObjects() {
        return this.apiList;
    }
	
	@ModelAttribute("allFolders")
    public List<MyKey> populateFolders() {
        return this.folderPathCollectionService.findAll();
    }
	
	@RequestMapping(value="/folder/search", method=RequestMethod.GET)
    public String index(@ModelAttribute("folderPath") final MyKey folderPath) {
		return "foldersearch";
    }
	
	
	@RequestMapping(value="/folder/search", params={"search"}, method=RequestMethod.POST)
    public String search(@ModelAttribute("folderPath") @Valid final MyKey folderPath, final BindingResult bindingResult, final ModelMap model) throws Exception {
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
            return "foldersearch";
        }
		this.folderPathCollectionService.add(folderPath);
        model.clear();
        List<MyKey> l = this.folderPathCollectionService.findAll();
        for(MyKey bunch: l) {
        	for(String path : bunch.getKeys()) {        		
        		this.apiList = this.folderSearchService.retrieveApiObjectsInFolder(path);
        	}
        }
        return "redirect:/folder/search";
    }
	
	@RequestMapping(value="/folder/search", params={"addPath"})
    public String addKey(@ModelAttribute("folderPath") final MyKey folderPath, final BindingResult bindingResult) {
		folderPath.getKeys().add("");
		return "foldersearch";
    }
	
	@RequestMapping(value="/folder/search", params={"removePath"})
    public String removeKey(@ModelAttribute("folderPath") final MyKey folderPath, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removePath"));
        folderPath.getKeys().remove(rowId.intValue());
        return "foldersearch";
    }
}
