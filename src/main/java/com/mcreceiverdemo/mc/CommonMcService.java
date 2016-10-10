package com.mcreceiverdemo.mc;

import java.util.List;

import com.exacttarget.fuelsdk.ETApiObject;
import com.exacttarget.fuelsdk.ETSdkException;

public interface CommonMcService {
	public <T extends ETApiObject> T retrieve(String key, Class<T> type) throws ETSdkException;
	public <T extends ETApiObject> List<T> retrieveList(String key, Class<T> type) throws ETSdkException;
	public <T> T retrieveObject(String key, Class<T> type) throws ETSdkException;
}
