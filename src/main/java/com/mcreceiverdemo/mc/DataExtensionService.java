package com.mcreceiverdemo.mc;

import java.util.List;
import java.util.Map;

import com.exacttarget.fuelsdk.ETApiObject;
import com.exacttarget.fuelsdk.ETDataExtension;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;

public interface DataExtensionService {
	
	public ETResponse<?> upsert(String key, Map<String,String> recordsValues) throws ETSdkException;
	
	public void clone(ETDataExtension uatDE) throws ETSdkException;
	
	public void uatToProd(String key) throws ETSdkException;

	public <T extends ETApiObject> T retrieve(String key, Class<T> type) throws ETSdkException;
	public <T extends ETApiObject> List<T> retrieveList(String key, Class<T> type) throws ETSdkException;
}
