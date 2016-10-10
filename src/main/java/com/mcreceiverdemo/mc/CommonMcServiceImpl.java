package com.mcreceiverdemo.mc;

//https://github.com/MichaelAllenClark/exacttarget-soap-java


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETApiObject;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETExpression;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETSoapConnection;
import com.exacttarget.fuelsdk.ETSoapObject;

@Service
public abstract class CommonMcServiceImpl implements CommonMcService {
	
	@Autowired
	protected ClientService mcClient;
	
	@Override
	public <T extends ETApiObject> T retrieve(String key, Class<T> type) throws ETSdkException {
		ETFilter etFilter = new ETFilter();
		ETExpression etExpression = new ETExpression();
		etExpression.setProperty("CustomerKey");
		etExpression.setValue(key);
		etExpression.setOperator(com.exacttarget.fuelsdk.ETExpression.Operator.EQUALS);
		etFilter.setExpression(etExpression);
		ETResponse<T> etObjList = this.mcClient.getETClient().retrieve(type,etFilter);
		List<T> list = etObjList.getObjects();
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public <T extends ETApiObject> List<T> retrieveList(String key, Class<T> type) throws ETSdkException {
		ETFilter etFilter = new ETFilter();
		ETExpression etExpression = new ETExpression();
		etExpression.setProperty("CustomerKey");
		etExpression.setValue(key);
		etExpression.setOperator(com.exacttarget.fuelsdk.ETExpression.Operator.EQUALS);
		etFilter.setExpression(etExpression);
		ETResponse<T> etObjList = this.mcClient.getETClient().retrieve(type, etFilter);
		List<T> list = etObjList.getObjects();
		return list;
	}
	
	
	
	
	@Override
	public <T> T retrieveObject(String key, Class<T> type) throws ETSdkException {
		ETFilter etFilter = new ETFilter();
		ETExpression etExpression = new ETExpression();
		etExpression.setProperty("CustomerKey");
		etExpression.setValue(key);
		etExpression.setOperator(com.exacttarget.fuelsdk.ETExpression.Operator.EQUALS);
		etFilter.setExpression(etExpression);
		ETClient client = this.mcClient.getETClient();
		//ETSoapConnection sconn = new ETSoapConnection(client,"", client.getAccessToken());
		//ETResponse<ETSoapObject> x = ETApiObject.retrieve(client, ETSoapObject.class, etFilter);
		//T etObj = this.mcClient.getETClient().retrieveObject( type,etFilter);
		return null;
	}
}
