package com.mcreceiverdemo.mc;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.exceptions.CustomException;

public interface ClientService {
	public void logoutClient();
	public boolean isETClient();
	public ETClient getETClient() throws CustomException;
	public void initiate(String apiKey, String apiSecret) throws ETSdkException;
}
