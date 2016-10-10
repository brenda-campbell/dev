package com.mcreceiverdemo.mc;

import java.util.List;
import java.util.Map;

import com.exacttarget.fuelsdk.ETApiObject;
import com.exacttarget.fuelsdk.ETDataExtension;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;

public interface DataExtensionService {
	
	public ETResponse<?> upsert(String key, Map<String,String> recordsValues) throws ETSdkException;
	
	public void clone(ETDataExtension uatDE) throws Exception;
	
	public void uatToProd(String key) throws Exception;
}
