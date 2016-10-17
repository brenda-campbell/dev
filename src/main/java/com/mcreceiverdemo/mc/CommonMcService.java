package com.mcreceiverdemo.mc;

import java.util.List;

import com.exacttarget.fuelsdk.ETApiObject;
import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.et.ETFolderObject;
import com.mcreceiverdemo.exceptions.CustomException;

public interface CommonMcService {
	
	<T extends ETApiObject> T retrieveByName(String key, Class<T> type) throws ETSdkException;
	<T extends ETApiObject> List<T> retrieveListByName(String key, Class<T> type) throws ETSdkException;
	<T extends ETApiObject> T retrieveByKey(String key, Class<T> type) throws ETSdkException;
	<T extends ETApiObject> List<T> retrieveListByFolder(String folderKey, Class<T> type) throws ETSdkException;
	<T> T retrieveObject(String key, Class<T> type) throws ETSdkException;
}
