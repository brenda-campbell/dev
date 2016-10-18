package com.mcreceiverdemo.mc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETApiObject;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETResult;
import com.exacttarget.fuelsdk.ETSdkException;
import com.google.common.collect.Lists;
import com.mcreceiverdemo.et.APIObjectExtended;
import com.mcreceiverdemo.et.ETFolderObject;
import com.mcreceiverdemo.et.ETRetrieveDataExtensionObject;
import com.mcreceiverdemo.et.ETRetrieveQueryObject;
import com.mcreceiverdemo.et.ETUpdateDataExtensionObject;
import com.mcreceiverdemo.et.ETUpdateQueryObject;
import com.mcreceiverdemo.exceptions.CustomException;

@Service
public class FolderSearchServiceImpl extends CommonMcServiceImpl implements FolderSearchService {

	
	@Autowired
    private DataExtensionService deService;
	
	@Autowired
    private QueryActivityService qaService;
	
	
	@Override
	public ETFolderObject retrieveTargETFolderObject(String folderPath) throws ETSdkException, CustomException {
		List<String> folders = Lists.reverse(Arrays.asList(folderPath.split(">")));
		String folder = folders.get(0);
		List<ETFolderObject> matchedFolders = super.retrieveListByName(folder.trim(), ETFolderObject.class);
		boolean isCorrectFolder = false;
		if(1 == folders.size() && matchedFolders.size() > 0) {
			isCorrectFolder = true;
			return matchedFolders.get(0);
		}
		else {
			for(ETFolderObject matchedFolder : matchedFolders) {
				String parentFolderKey = matchedFolder.getParentFolderKey();
				int idx = 1;
				while(parentFolderKey != null && idx < folders.size()) {
					ETFolderObject parentFolder = super.retrieveByKey(parentFolderKey, ETFolderObject.class);
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
		}
		
		return null;
	}
	
	@Override
	public List<APIObjectExtended> retrieveApiObjectsInFolder(String folderPath) throws CustomException, ETSdkException {
		List<APIObjectExtended> apiObjectList = new ArrayList<APIObjectExtended>(); 
				
		ETFolderObject folder = this.retrieveTargETFolderObject(folderPath);
		if(folder == null) {
			throw new CustomException("Folder path "+folderPath+" does not exist.");
		}
		if(folder.getContentType().equalsIgnoreCase(com.mcreceiverdemo.utils.ETAPIObjectType.DATA_EXTENSION)) {
			List<ETRetrieveDataExtensionObject> l = this.retrieveDataExtensionsInFolder(folder);
			for(ETRetrieveDataExtensionObject de: l) {
				APIObjectExtended apiObj = new APIObjectExtended();
				apiObj.setName(de.getName());
				apiObj.setCustomerKey(de.getKey());
				apiObj.setObjectType(com.mcreceiverdemo.utils.ETAPIObjectType.DATA_EXTENSION);
				apiObjectList.add(apiObj );
			}
		}
		else if(folder.getContentType().equalsIgnoreCase(com.mcreceiverdemo.utils.ETAPIObjectType.QUERY_ACTIVITY)) {
			List<ETRetrieveQueryObject> l = this.retrieveQueryActivitiesInFolder(folder);
			for(ETRetrieveQueryObject q: l) {
				APIObjectExtended apiObj = new APIObjectExtended();
				apiObj.setName(q.getName());
				apiObj.setCustomerKey(q.getKey());
				apiObj.setObjectType(com.mcreceiverdemo.utils.ETAPIObjectType.QUERY_ACTIVITY);
				apiObjectList.add(apiObj );
			}
		}
		else {
			throw new CustomException("The target folder is not a DataExtension Or QueryActivity container.");
		}
		
		return apiObjectList;
	}
	
	@Override
	public List<ETRetrieveDataExtensionObject> retrieveDataExtensionsInFolder(ETFolderObject folder) throws CustomException, ETSdkException {	
		return super.retrieveListByFolder(folder.getId(), ETRetrieveDataExtensionObject.class);
	}
	
	@Override
	public List<ETRetrieveQueryObject> retrieveQueryActivitiesInFolder(ETFolderObject folder) throws CustomException, ETSdkException {	
		return super.retrieveListByFolder(folder.getId(), ETRetrieveQueryObject.class);
	}
	
	@Override
	public void uatToProd(List<APIObjectExtended> apiObjects) throws CustomException, ETSdkException {
			
		List<APIObjectExtended> deList = apiObjects.stream()
					.filter(t->t.getObjectType().equalsIgnoreCase(com.mcreceiverdemo.utils.ETAPIObjectType.DATA_EXTENSION))
					.collect(Collectors.toList());
		
		
		for(APIObjectExtended apiObject:deList) {
			ETResponse<ETUpdateDataExtensionObject> response = deService.uatToProd(apiObject.getCustomerKey());
			if(response.getStatus() == ETResult.Status.OK ) {
				ETUpdateDataExtensionObject etDE = response.getObject();
				apiObject.setClonedObjectCustomerKey(etDE.getKey());
				apiObject.setClonedObjectObjectID(etDE.getId());
				apiObject.setClonedObjectName(etDE.getName());
			}
		}
		
		List<APIObjectExtended> qaList = apiObjects.stream()
				.filter(t->t.getObjectType().equalsIgnoreCase(com.mcreceiverdemo.utils.ETAPIObjectType.QUERY_ACTIVITY))
				.collect(Collectors.toList());
		
		for(APIObjectExtended apiObject:qaList) {
			ETRetrieveQueryObject qa = this.qaService.retrieve(apiObject.getCustomerKey());
			Optional<APIObjectExtended> newObject = deList.stream().filter(t->t.getCustomerKey().equals(qa.getDataExtensionTarget().getCustomerKey()))
											.findFirst();
			
			if(newObject.isPresent() && newObject.get() != null) {
				qaService.uatToProd(qa, newObject.get().getClonedObjectObjectID(), newObject.get().getClonedObjectCustomerKey(), newObject.get().getClonedObjectName());
			}
			else {
				qaService.uatToProd(qa);
			}
			
		}
	}
}
