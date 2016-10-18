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
import com.mcreceiverdemo.et.ETClientObject;
import com.mcreceiverdemo.exceptions.CustomException;

@Service
public class ClientServiceImpl implements ClientService{
	
	private ETClientObject etClientObject;
	
	private Integer mid;
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
		
		etClientObject = new ETClientObject(config);
		etClientObject.requestToken();
		String clientID = etClientObject.getClientId();
		
		return etClientObject.getAccessToken();
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
		etClientObject = new ETClientObject(config);
		
		ETFilter etFilter = new ETFilter();
		ETExpression etExpression = new ETExpression();
		etExpression.setProperty("UserID");
		etExpression.setValue(username);
		etExpression.setOperator(com.exacttarget.fuelsdk.ETExpression.Operator.EQUALS);
		etFilter.setExpression(etExpression);
		
		ETResponse<ETAccountUserObject> response = this.etClientObject.retrieve(ETAccountUserObject.class, etFilter);
		if(response.getStatus() == ETResult.Status.OK ) {
			ETAccountUserObject accountUser = response.getObject();
			return accountUser.getUserID();
		}
		
		return null;
	}
	
	@Override
	public boolean isETClientObject() {
		if(this.etClientObject == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public void logoutClient() {
		this.etClientObject = null;
	}
	
	@Override
	public ETClientObject getETClientObject() throws CustomException {
		if(this.etClientObject == null) {
			throw new CustomException("etClient is not initated and is null.");
		}
		return this.etClientObject;
	}
	
	@Override
	public void setMID(Integer mid) {
		this.mid = mid;
	}
	@Override
	public Integer getMID() {
		return this.mid;
	}
}
