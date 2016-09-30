package com.mcreceiverdemo.mc;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;

public class MCClientServiceImpl implements MCClientService{
	
	private ETClient etClient;
	
	@Override
	public void Init() throws ETSdkException {
		etClient = new ETClient();
		etClient.autoHydrateObjects();
		//String clientId = etClient.getClientId();
		//String accessToken = etClient.getAccessToken();
	}
	
	public ETClient getEtClient(){
		return this.etClient;
	}
}
