package com.mcreceiverdemo.mc;

import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETExpression;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETResult;
import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.et.ETAccountUserObject;
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
	
	@Override
	public String initiate(String apiKey, String apiSecret) throws ETSdkException {
		ETConfiguration config = new ETConfiguration();
		config.set("clientId", apiKey);
		config.set("clientSecret", apiSecret);
		
		etClient = new ETClient(config);
		etClient.requestToken();
		String clientID = etClient.getClientId();
		
		return etClient.getAccessToken();
		//etClient.autoHydrateObjects();
	}
	
	@Override
	public String login(String username, String password, String soapEndpoint) throws CustomException, ETSdkException {
		if(soapEndpoint.isEmpty()) {
			throw new CustomException("Provide a Soap Endpoint, you must.");
		}
		ETConfiguration config = new ETConfiguration();
		config.set("username", username);
		config.set("password", password);
		config.set("soapEndpoint", soapEndpoint);
		etClient = new ETClient(config);
		
		ETFilter etFilter = new ETFilter();
		ETExpression etExpression = new ETExpression();
		etExpression.setProperty("UserID");
		etExpression.setValue(username);
		etExpression.setOperator(com.exacttarget.fuelsdk.ETExpression.Operator.EQUALS);
		etFilter.setExpression(etExpression);
		
		ETResponse<ETAccountUserObject> response = this.etClient.retrieve(ETAccountUserObject.class, etFilter);
		if(response.getStatus() == ETResult.Status.OK ) {
			ETAccountUserObject accountUser = response.getObject();
			return accountUser.getUserID();
		}
		
		return null;
	}
	
	@Override
	public boolean isETClient() {
		if(this.etClient == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public void logoutClient() {
		this.etClient = null;
	}
	
	@Override
	public ETClient getETClient() throws CustomException {
		if(this.etClient == null) {
			throw new CustomException("etClient is not initated and is null.");
		}
		return this.etClient;
	}
}
