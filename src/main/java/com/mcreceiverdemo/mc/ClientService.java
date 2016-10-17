package com.mcreceiverdemo.mc;

import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.et.ETClientObject;
import com.mcreceiverdemo.exceptions.CustomException;

public interface ClientService {
	void logoutClient();
	boolean isETClientObject();
	ETClientObject getETClientObject() throws CustomException;
	String initiate(String apiKey, String apiSecret) throws ETSdkException;
	String login(String username, String password, String soapEndpoint) throws CustomException, ETSdkException;
	void setMID(Integer mid);
	Integer getMID();
}
