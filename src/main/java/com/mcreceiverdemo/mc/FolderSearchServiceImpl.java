package com.mcreceiverdemo.mc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETApiObject;
import com.exacttarget.fuelsdk.ETFolder;
import com.exacttarget.fuelsdk.ETSdkException;
import com.google.common.collect.Lists;
import com.mcreceiverdemo.et.APIObjectExtended;
import com.mcreceiverdemo.et.ETRetrieveDataExtensionObject;
import com.mcreceiverdemo.exceptions.CustomException;

@Service
public class FolderSearchServiceImpl extends CommonMcServiceImpl implements FolderSearchService {

	@Override
	public ETFolder retrieveTargetFolder(String folderPath) throws ETSdkException, CustomException {
		List<String> folders = Lists.reverse(Arrays.asList(folderPath.split(">")));
		String folder = folders.get(0);
		List<ETFolder> matchedFolders = super.retrieveListByName(folder.trim(), ETFolder.class);
		boolean isCorrectFolder = false;
		for(ETFolder matchedFolder : matchedFolders) {
			String parentFolderKey = matchedFolder.getParentFolderKey();
			int idx = 1;
			while(parentFolderKey != null && idx < folders.size()) {
				ETFolder parentFolder = super.retrieveByKey(parentFolderKey, ETFolder.class);
				if(parentFolder.getName().equalsIgnoreCase(folders.get(idx++))) {
					isCorrectFolder = true;
					parentFolderKey = parentFolder.getParentFolderKey();
				}
				else {
					isCorrectFolder = false;
				}
			}
			if(isCorrectFolder) {
				return matchedFolder;
			}
		}
		return null;
	}
	
	@Override
	public List<APIObjectExtended> retrieveApiObjectsInFolder(String folderPath) throws CustomException, ETSdkException {
		List<APIObjectExtended> apiObjectList = new ArrayList<APIObjectExtended>(); 
				
		ETFolder folder = this.retrieveTargetFolder(folderPath);
		if(folder.getContentType().equalsIgnoreCase("dataextension")) {
			List<ETRetrieveDataExtensionObject> l = this.retrieveDataExtensionsInFolder(folder);
			for(ETRetrieveDataExtensionObject de: l) {
				APIObjectExtended apiObj = new APIObjectExtended();
				apiObj.setName(de.getName());
				apiObj.setCustomerKey(de.getKey());
				apiObj.setObjectType("dataextension");
				apiObjectList.add(apiObj );
			}
		}
		else {
			throw new CustomException("The target folder is not a DataExtension Or QueryActivity container.");
		}
		
		return apiObjectList;
	}
	
	@Override
	public List<ETRetrieveDataExtensionObject> retrieveDataExtensionsInFolder(ETFolder folder) throws CustomException, ETSdkException {	
		return super.retrieveListByFolder(folder.getId(), ETRetrieveDataExtensionObject.class);
	}
}
