package com.mcreceiverdemo.et;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.exacttarget.fuelsdk.ETDataExtensionColumn;
import com.exacttarget.fuelsdk.annotations.RestObject;
import com.exacttarget.fuelsdk.annotations.SoapObject;

@RestObject(path = "/data/v1/customobjectdata/key/{key}/rowset",
		primaryKey = "key",
		collection = "items",
		totalCount = "count")
@SoapObject(internalType = com.exacttarget.fuelsdk.internal.DataExtension.class, unretrievable = {
		"ID", "Fields"
})
public class ETUpdateDataExtensionObject extends ETDataExtensionObject {
	
	
	public void cloneObject(ETRetrieveDataExtensionObject r){
		this.setKey(r.getKey());
		this.setName(r.getName());
		this.setDescription(r.getDescription());
		this.setTemplate(r.getTemplate());
		this.setIsSendable(r.getIsSendable());
		this.setIsTestable(r.getIsTestable());
		this.setFolderId(r.getFolderId());
		this.setCreatedDate(r.getCreatedDate());
		this.setModifiedDate(new Date());
		
		List<ETDataExtensionColumn> clone = new ArrayList<ETDataExtensionColumn>(r.getColumns().size());
	    for (ETDataExtensionColumn item : r.getColumns()) {
	    	clone.add(item);
	    }
	    
	}
}
