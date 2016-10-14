package com.mcreceiverdemo.mc;

import java.util.List;

import com.exacttarget.fuelsdk.ETFolder;
import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.et.APIObjectExtended;
import com.mcreceiverdemo.et.ETRetrieveDataExtensionObject;
import com.mcreceiverdemo.exceptions.CustomException;

public interface FolderSearchService {
	
	public ETFolder retrieveTargetFolder(String folderPath)throws ETSdkException, CustomException;

	List<ETRetrieveDataExtensionObject> retrieveDataExtensionsInFolder(ETFolder folder) throws CustomException, ETSdkException;

	List<APIObjectExtended> retrieveApiObjectsInFolder(String folderPath) throws CustomException, ETSdkException;
	
}
