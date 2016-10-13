package com.mcreceiverdemo.mc;

import java.util.Map;

import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.et.ETRetrieveDataExtensionObject;

public interface DataExtensionService {
	
	public ETResponse<?> upsert(String key, Map<String,String> recordsValues) throws ETSdkException;
	
	public void clone(ETRetrieveDataExtensionObject uatDE) throws Exception;
	
	public void uatToProd(String key) throws Exception;
}
