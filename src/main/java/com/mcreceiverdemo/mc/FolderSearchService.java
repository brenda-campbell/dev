package com.mcreceiverdemo.mc;

import java.util.List;

import com.exacttarget.fuelsdk.ETFolder;
import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.et.APIObjectExtended;
import com.mcreceiverdemo.et.ETFolderObject;
import com.mcreceiverdemo.et.ETRetrieveDataExtensionObject;
import com.mcreceiverdemo.et.ETRetrieveQueryObject;
import com.mcreceiverdemo.exceptions.CustomException;

public interface FolderSearchService {
	
	List<ETRetrieveDataExtensionObject> retrieveDataExtensionsInFolder(ETFolderObject folder) throws CustomException, ETSdkException;

	List<APIObjectExtended> retrieveApiObjectsInFolder(String folderPath) throws CustomException, ETSdkException;

	List<ETRetrieveQueryObject> retrieveQueryActivitiesInFolder(ETFolderObject folder) throws CustomException, ETSdkException;

	void uatToProd(List<APIObjectExtended> apiObjects) throws CustomException, ETSdkException;

	ETFolderObject retrieveTargETFolderObject(String folderPath) throws ETSdkException, CustomException;
	
}
