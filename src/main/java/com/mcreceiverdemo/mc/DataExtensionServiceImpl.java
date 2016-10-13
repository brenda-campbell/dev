package com.mcreceiverdemo.mc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETDataExtensionColumn;
import com.exacttarget.fuelsdk.ETDataExtensionRow;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.et.ETDataExtensionObject;
import com.mcreceiverdemo.et.ETRetrieveDataExtensionObject;
import com.mcreceiverdemo.et.ETUpdateDataExtensionObject;
import com.mcreceiverdemo.exceptions.CustomException;

@Service
public class DataExtensionServiceImpl extends CommonMcServiceImpl implements DataExtensionService, CommonMcService {
	//http://salesforce-marketingcloud.github.io/FuelSDK-Java/	
	//http://column80.com/api.v2.php?a=salesforce&q=102553
	//http://seotoast.com/pagination-or-chunking-support-for-fuel-sdk/
	//https://developer.salesforce.com/docs/atlas.en-us.mc-sdks.meta/mc-sdks/data-extension-retrieve.htm
	//http://salesforce.stackexchange.com/questions/49208/inserting-a-row-in-dataextension-via-java-fuelsdk-not-working
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public DataExtensionServiceImpl(){
		super();
	}
	
	public ETResponse<?> upsert(String key, Map<String,String> recordsValues) throws ETSdkException{
		ETDataExtensionObject de = new ETDataExtensionObject();  //client.retrieveDataExtension(filter);
		de.setKey(key); //"8B1A73D6-8EFA-4A7D-AB43-88663EB9AD28"
		de.setClient(this.mcClient.getETClient());
		
		//add a row to the data extension "products"
		ETDataExtensionRow row = new ETDataExtensionRow();
		for (Map.Entry<String, String> entry : recordsValues.entrySet()) {
		    row.setColumn(entry.getKey(), entry.getValue());
		}
		ETResponse<?> response = de.insert(row);
		//ETResponse<?> response = de.update(row);
		response.getObject();
		logger.info(response.getResponseMessage());
		return response;
	}

	
	
	public void clone(ETRetrieveDataExtensionObject uatDE) throws Exception {
		String deName = uatDE.getName();
		if(! (deName.toLowerCase().startsWith("uat") || deName.toLowerCase().endsWith("uat")) ) {
			throw new CustomException(String.format("de `%s` is not a UAT de", deName));
		}
		ETUpdateDataExtensionObject newDE = new ETUpdateDataExtensionObject();
		newDE.cloneObject(uatDE);
		//newDE.setClient(this.mcClient.getEtClient());
		newDE.setName(uatDE.getName().replaceFirst("(?i)uat_", "").replaceFirst("(?i)_uat", ""));
		newDE.setKey(java.util.UUID.randomUUID().toString());
		String customerKey = uatDE.getKey();
		List<ETDataExtensionColumn> l = uatDE.retrieveColumns(this.mcClient.getETClient(), customerKey); //this.retrieveList(customerKey, ETDataExtensionColumn.class);
		for(ETDataExtensionColumn c : l) {
			newDE.addColumn(c.getName(), c.getType(), c.getLength(), c.getPrecision(), c.getScale(), c.getIsPrimaryKey(), c.getIsRequired(), c.getDefaultValue());
		}
		//newDE.create(this.mcClient.getEtClient(), arg1);
		ETResponse<ETDataExtensionObject> createdDE = this.mcClient.getETClient().create(new ArrayList<ETDataExtensionObject>() {{add(newDE);}});
	}


	@Override
	public void uatToProd(String key) throws Exception {
		ETRetrieveDataExtensionObject etDE = this.retrieve(key, ETRetrieveDataExtensionObject.class);
		if(etDE == null) {
			throw new CustomException(String.format("no DE cannot be found."));
		}
		if(etDE.getTemplate() != null && !etDE.getTemplate().getCustomerKey().isEmpty()) {
			// do retrieve template
			// set it in the etDE 
			// then go for cloning.
			// Otherwise it will always create standard DE.
		}
		this.clone(etDE);		
	}
	
	
}
