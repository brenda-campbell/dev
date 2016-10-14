package com.mcreceiverdemo.mc;

import java.util.List;

import com.exacttarget.fuelsdk.ETApiObject;
import com.exacttarget.fuelsdk.ETSdkException;

public interface CommonMcService {
	
	public <T extends ETApiObject> T retrieveByName(String key, Class<T> type) throws ETSdkException;
	public <T extends ETApiObject> List<T> retrieveListByName(String key, Class<T> type) throws ETSdkException;
	public <T extends ETApiObject> T retrieveByKey(String key, Class<T> type) throws ETSdkException;
	public <T extends ETApiObject> List<T> retrieveListByFolder(String folderKey, Class<T> type) throws ETSdkException;
	public <T> T retrieveObject(String key, Class<T> type) throws ETSdkException;
}
