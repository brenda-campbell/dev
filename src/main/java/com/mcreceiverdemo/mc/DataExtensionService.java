package com.mcreceiverdemo.mc;

import java.util.Map;

import com.exacttarget.fuelsdk.ETDataExtension;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;

public interface DataExtensionService {
	
	public ETResponse<?> upsert(String key, Map<String,String> recordsValues) throws ETSdkException;
	
	public ETDataExtension retrieve(String key) throws ETSdkException;
	
	public void clone(ETDataExtension uatDE) throws ETSdkException;
	
	public void uatToProd(String key) throws ETSdkException;
}
