package com.mcreceiverdemo.et;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.exacttarget.fuelsdk.ETDataExtensionColumn;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.ClientID;

@RestObject(path = "/data/v1/customobjectdata/key/{key}/rowset",
		primaryKey = "key",
		collection = "items",
		totalCount = "count")
@SoapObject(internalType = com.exacttarget.fuelsdk.internal.DataExtension.class, unretrievable = {
		"Fields"
})
public class ETUpdateDataExtensionObject extends ETDataExtensionObject {
	
	@ExternalName("client")
	@InternalName("client")
	private ClientID clientID;
	
	
	public void cloneObject(ETRetrieveDataExtensionObject r, Integer mid){
		this.setKey(r.getKey());
		this.setName(r.getName());
		this.setDescription(r.getDescription());
		this.setTemplate(r.getTemplate());
		this.setIsSendable(r.getIsSendable());
		this.setIsTestable(r.getIsTestable());
		this.setFolderId(r.getFolderId());
		this.setCreatedDate(r.getCreatedDate());
		this.setModifiedDate(new Date());
		
		if(mid != null && mid.intValue() > 0) {
			ClientID cid = new ClientID();
			cid.setClientID(mid);
			this.setClientID(cid);
		}
		
		List<ETDataExtensionColumnObject> clone = new ArrayList<ETDataExtensionColumnObject>(r.getColumns().size());
	    for (ETDataExtensionColumnObject item : r.getColumns()) {
	    	clone.add(item);
	    }
	    
	}


	public ClientID getClientID() {
		return clientID;
	}


	public void setClientID(ClientID clientID) {
		this.clientID = clientID;
	}
}
