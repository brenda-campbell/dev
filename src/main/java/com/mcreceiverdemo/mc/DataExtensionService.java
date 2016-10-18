package com.mcreceiverdemo.mc;

import java.util.Map;

import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.et.ETRetrieveDataExtensionObject;
import com.mcreceiverdemo.et.ETUpdateDataExtensionObject;
import com.mcreceiverdemo.exceptions.CustomException;

public interface DataExtensionService {
	
	public ETResponse<?> upsert(String key, Map<String,String> recordsValues) throws ETSdkException;
	
	public ETResponse<ETUpdateDataExtensionObject> clone(ETRetrieveDataExtensionObject uatDE) throws CustomException, ETSdkException;
	
	public ETResponse<ETUpdateDataExtensionObject> uatToProd(String key) throws CustomException, ETSdkException;
}
