package com.mcreceiverdemo.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.TransformerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.et.APIObjectExtended;
import com.mcreceiverdemo.exceptions.CustomException;
import com.mcreceiverdemo.mc.FolderSearchService;
import com.mcreceiverdemo.mvcmodels.MyData;
import com.mcreceiverdemo.mvcmodels.MyKey;
import com.mcreceiverdemo.mvcmodels.NameValue;
import com.mcreceiverdemo.services.MyDataCollectionService;
import com.mcreceiverdemo.services.MyKeyCollectionService;

@Controller
public class FolderSearchController {
	
	@Autowired
    private MyKeyCollectionService folderPathCollectionService;
	
	@Autowired
    private MyDataCollectionService myDataCollectionService;
	
    @Autowired
    private FolderSearchService folderSearchService;
	
	public FolderSearchController(){
		super();
	}
	
	@ModelAttribute("allAPIObjects")
    public List<MyData> populateETObjects() {
        return this.myDataCollectionService.findAll();
    }
	
	@ModelAttribute("allFolders")
    public List<MyKey> populateFolders() {
        return this.folderPathCollectionService.findAll();
    }
	
	@RequestMapping(value="/folder/search", method=RequestMethod.GET)
    public String index(@ModelAttribute("folderPath") final MyKey folderPath, @ModelAttribute("apiObjectData") final MyData apiObjectData) {
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
        	MyData data = new MyData();
        	data.setKey(bunch.getBunchName());
        	for(String path : bunch.getKeys()) {        		
        		List<APIObjectExtended> apiList = this.folderSearchService.retrieveApiObjectsInFolder(path);
        		for(APIObjectExtended apiObj : apiList) {
        			data.getNameValues().add(new NameValue(){ {name=apiObj.getName(); value=apiObj.getObjectType(); key=apiObj.getCustomerKey();}});
        		}
        	}
        	this.myDataCollectionService.add(data);
        }
        return "redirect:/folder/search";
    }
	
	@RequestMapping(value="/folder/search", params={"addPath"}, method=RequestMethod.POST)
    public String addKey(@ModelAttribute("folderPath") final MyKey folderPath, final BindingResult bindingResult) {
		folderPath.getKeys().add("");
		return "foldersearch";
    }
	
	@RequestMapping(value="/folder/search", params={"removePath"}, method=RequestMethod.POST)
    public String removeKey(@ModelAttribute("folderPath") final MyKey folderPath, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removePath"));
        folderPath.getKeys().remove(rowId.intValue());
        return "foldersearch";
    }
	
	
	@RequestMapping(value="/folder/search", params={"clearQ"}, method=RequestMethod.POST)
    public String clearQ(final ModelMap model) {
		this.myDataCollectionService.clear();
		model.clear();
        return "redirect:/folder/search";
    }
	
	@RequestMapping(value="/folder/search", params={"queue"}, method=RequestMethod.POST)
    public String queue(@ModelAttribute("apiObjectData") final MyData apiObjectData, final BindingResult bindingResult) {
		
		Collection<NameValue> packagedData= apiObjectData.getNameValues().stream().filter(com.mcreceiverdemo.utils.CustomPredicates.distinctByKey(NameValue::getName)).collect(Collectors.toList());
		Collection<String> packageNames = CollectionUtils.collect(packagedData, TransformerUtils.invokerTransformer("getName")); 
		List<String> selectedCustomerKeys = apiObjectData.getNameValues().stream().filter(t->t.getKey() != null ).map(NameValue::getKey).collect(Collectors.toList());
		
		for(String p : packageNames) {
			MyData dataObj = this.myDataCollectionService.findAll().stream().filter(t->t.getKey().equals(p)).findFirst().get();
			List<NameValue> nvObjs = dataObj.getNameValues().stream().filter(dnv-> ! selectedCustomerKeys.contains(dnv.getKey())).collect(Collectors.toList());
			dataObj.getNameValues().removeAll( 
						nvObjs
					);
		}
		return "redirect:/folder/search";
    }
	
	
	@RequestMapping(value="/folder/search", params={"cloneQ"}, method=RequestMethod.POST)
    public String cloneQ(final ModelMap model) throws CustomException, ETSdkException {
		List<MyData> dataList = this.myDataCollectionService.findAll();
		
		List<APIObjectExtended> apiObjects = new ArrayList<APIObjectExtended>();
		
		for(MyData data: dataList) {
			for(NameValue nv : data.getNameValues()) {
				APIObjectExtended apiObj = new APIObjectExtended();
				apiObj.setName(nv.getName());
				apiObj.setCustomerKey(nv.getKey());
				apiObj.setObjectType(nv.getValue());
				apiObjects.add( apiObj );
			}
		}
		
		this.folderSearchService.uatToProd(apiObjects);
		
		model.clear();
        return "redirect:/folder/search";
    }
	
}
