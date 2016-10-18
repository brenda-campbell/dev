package com.mcreceiverdemo.mc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETDataExtensionRow;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.et.ETDataExtensionColumnObject;
import com.mcreceiverdemo.et.ETDataExtensionObject;
import com.mcreceiverdemo.et.ETDataExtensionTemplateObject;
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
		de.setClient(this.mcClient.getETClientObject());
		
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

	
	
	public ETResponse<ETUpdateDataExtensionObject> clone(ETRetrieveDataExtensionObject uatDE) throws CustomException, ETSdkException {
		String deName = uatDE.getName();
		if(! (deName.toLowerCase().startsWith("uat") || deName.toLowerCase().endsWith("uat")) ) {
			throw new CustomException(String.format("de `%s` is not a UAT de", deName));
		}
		ETUpdateDataExtensionObject newDE = new ETUpdateDataExtensionObject();
		newDE.cloneObject(uatDE, this.mcClient.getMID());
		//newDE.setClient(this.mcClient.getEtClient());
		newDE.setName(uatDE.getName().replaceFirst("(?i)uat_", "").replaceFirst("(?i)_uat", ""));
		newDE.setKey(java.util.UUID.randomUUID().toString());
		if(newDE.getTemplate() == null || newDE.getTemplate().getObjectID().isEmpty()) {
			String customerKey = uatDE.getKey();
			List<ETDataExtensionColumnObject> l = uatDE.retrieveColumns(this.mcClient.getETClientObject(), customerKey, this.mcClient.getMID()); //this.retrieveList(customerKey, ETDataExtensionColumn.class);
			for(ETDataExtensionColumnObject c : l) {
				newDE.addColumn(c.getName(), c.getType(), c.getLength(), c.getPrecision(), c.getScale(), c.getIsPrimaryKey(), c.getIsRequired(), c.getDefaultValue());
			}
		}
		//newDE.create(this.mcClient.getEtClient(), arg1);
		ETResponse<ETUpdateDataExtensionObject> response = this.mcClient.getETClientObject().create(new ArrayList<ETUpdateDataExtensionObject>() {{add(newDE);}});
		if(response != null) {
			ETUpdateDataExtensionObject createdDE = response.getObject();		
			if(createdDE!=null && newDE.getTemplate() != null && !newDE.getTemplate().getObjectID().isEmpty()) {
				
				//ETUpdateDataExtensionObject createdDE = new ETUpdateDataExtensionObject();
				//createdDE.cloneObject(responseDE);
				
				String customerKey = createdDE.getKey();
				List<ETDataExtensionColumnObject> createdCols = createdDE.retrieveColumns(this.mcClient.getETClientObject(), customerKey, this.mcClient.getMID()); //this.retrieveList(customerKey, ETDataExtensionColumn.class);
				customerKey = uatDE.getKey();
				List<ETDataExtensionColumnObject> uatCols = uatDE.retrieveColumns(this.mcClient.getETClientObject(), customerKey, this.mcClient.getMID());
				for(ETDataExtensionColumnObject c : uatCols) {
					boolean isExist = false;
					for(ETDataExtensionColumnObject cc : createdCols) {
						if(cc.getName().equalsIgnoreCase(c.getName())) {
							isExist = true;
							break;
						}
					}
					if(isExist) {
						continue;
					}
					createdDE.addColumn(c.getName(), c.getType(), c.getLength(), c.getPrecision(), c.getScale(), c.getIsPrimaryKey(), c.getIsRequired(), c.getDefaultValue());
					
					/*ETDataExtensionColumn newCol = new ETDataExtensionColumn();
					newCol.setName(c.getName());
					newCol.setType(c.getType());
					newCol.setLength(c.getLength());
					newCol.setPrecision(c.getPrecision());
					newCol.setScale(c.getScale());
					newCol.setIsPrimaryKey(c.getIsPrimaryKey());
					newCol.setIsRequired(c.getIsRequired());
					newCol.setDefaultValue(c.getDefaultValue());
					
					newCol.setDataExtension(createdDE);
					*/
															
				}
				ETResponse<ETUpdateDataExtensionObject> resCol = this.mcClient.getETClientObject().update(new ArrayList<ETUpdateDataExtensionObject>() {{add(createdDE);}});
				return resCol;
			}
		}
		return response;
		
	}


	@Override
	public ETResponse<ETUpdateDataExtensionObject> uatToProd(String key) throws CustomException, ETSdkException {
		ETRetrieveDataExtensionObject etDE = this.retrieveByKey(key, ETRetrieveDataExtensionObject.class);
		if(etDE == null) {
			throw new CustomException(String.format("no DE cannot be found."));
		}
		if(etDE.getTemplate() != null && !etDE.getTemplate().getCustomerKey().isEmpty()) {
			ETDataExtensionTemplateObject etTemplate = this.retrieveByKey(etDE.getTemplate().getCustomerKey(), ETDataExtensionTemplateObject.class);
			etDE.getTemplate().setObjectID(etTemplate.getId());;
		}
		return this.clone(etDE);		
	}
	
	
}
