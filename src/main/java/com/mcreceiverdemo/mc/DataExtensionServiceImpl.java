package com.mcreceiverdemo.mc;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETDataExtension;
import com.exacttarget.fuelsdk.ETDataExtensionRow;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;

@Service
public class DataExtensionServiceImpl implements DataExtensionService {
	//http://salesforce-marketingcloud.github.io/FuelSDK-Java/		
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ClientService mcClient;
	
	
	public DataExtensionServiceImpl(){
		super();
	}
	
	public ETResponse<?> Upsert(String key, Map<String,String> recordsValues) throws ETSdkException{
		ETDataExtension de = new ETDataExtension();  //client.retrieveDataExtension(filter);
		de.setKey(key); //"8B1A73D6-8EFA-4A7D-AB43-88663EB9AD28"
		de.setClient(this.mcClient.getEtClient());
		
		//add a row to the data extension "products"
		ETDataExtensionRow row = new ETDataExtensionRow();
		for (Map.Entry<String, String> entry : recordsValues.entrySet()) {
		    row.setColumn(entry.getKey(), entry.getValue());
		}
		ETResponse<?> response = de.insert(row);
		response.getObject();
		logger.info(response.getResponseMessage());
		return response;
	}
}
