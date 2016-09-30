package com.mcreceiverdemo.mc;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;

@Service
public interface DataExtensionService {
	
	public ETResponse<?> Upsert(String key, Map<String,String> recordsValues) throws ETSdkException;
	
}
