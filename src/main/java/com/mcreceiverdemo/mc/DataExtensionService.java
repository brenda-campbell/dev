package com.mcreceiverdemo.mc;

import java.util.Map;

import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;

public interface DataExtensionService {
	
	public ETResponse<?> Upsert(String key, Map<String,String> recordsValues) throws ETSdkException;
	
}
