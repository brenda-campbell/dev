package com.mcreceiverdemo.mc;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.exceptions.CustomException;

public interface ClientService {
	void logoutClient();
	boolean isETClient();
	ETClient getETClient() throws CustomException;
	String initiate(String apiKey, String apiSecret) throws ETSdkException;
	String login(String username, String password, String soapEndpoint) throws CustomException, ETSdkException;
}
