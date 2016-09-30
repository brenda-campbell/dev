package com.mcreceiverdemo.mc;

import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;

@Service
public class ClientServiceImpl implements ClientService{
	
	private ETClient etClient;
	
	public ClientServiceImpl() throws ETSdkException {
		super();
		if(this.etClient == null) {
			etClient = new ETClient();
			etClient.autoHydrateObjects();
		}
	}
	
	public ETClient getEtClient(){
		return this.etClient;
	}
}
