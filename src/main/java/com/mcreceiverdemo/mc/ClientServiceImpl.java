package com.mcreceiverdemo.mc;

import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.exceptions.CustomException;

@Service
public class ClientServiceImpl implements ClientService{
	
	private ETClient etClient;
	
	/*public ClientServiceImpl() throws CustomException {
		super();
		if(this.etClient == null) {
			//etClient = new ETClient();
			//etClient.autoHydrateObjects();
			throw new CustomException("etClient is not initated and is null");
		}
	}*/
	
	
	public void initiate(String apiKey, String apiSecret) throws ETSdkException {
		ETConfiguration config = new ETConfiguration();
		config.set("clientId", apiKey);
		config.set("clientSecret", apiSecret);
		
		etClient = new ETClient(config);
		etClient.autoHydrateObjects();
	}
	
	public boolean isETClient() {
		if(this.etClient == null) {
			return false;
		}
		return true;
	}
	
	public void logoutClient() {
		this.etClient = null;
	}
	
	public ETClient getETClient() throws CustomException {
		if(this.etClient == null) {
			throw new CustomException("etClient is not initated and is null.");
		}
		return this.etClient;
	}
}
