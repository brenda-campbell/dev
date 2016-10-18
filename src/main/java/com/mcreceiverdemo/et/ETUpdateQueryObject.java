package com.mcreceiverdemo.et;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.ClientID;

@SoapObject(internalType = com.exacttarget.fuelsdk.internal.QueryDefinition.class, unretrievable = {
	    "ID"
	})
public class ETUpdateQueryObject extends ETQueryObject {

	public ClientID getClientID() {
		return clientID;
	}

	public void setClientID(ClientID clientID) {
		this.clientID = clientID;
	}

	@ExternalName("client")
	@InternalName("client")
	private ClientID clientID;
	
	
	public void cloneQueryObject(ETRetrieveQueryObject r, Integer mid){
		this.setKey(r.getKey());
		this.setName(r.getName());
		this.setQueryText(r.getQueryText());
		this.setTargetType(r.getTargetType());
		this.applyDataExtensionTarget(r.getDataExtensionTarget());
		this.setTargetUpdateType(r.getTargetUpdateType());
		this.setFolderId(r.getFolderId());
		this.setFileSpec(r.getFileSpec());
		this.setFileType(r.getFileType());
		this.setStatus(r.getStatus());
		this.setObjectID(r.getObjectID());
		this.setCreatedDate(r.getCreatedDate());
		this.setModifiedDate(new Date());
		
		if(mid != null && mid.intValue() > 0) {
			ClientID cid = new ClientID();
			cid.setClientID(mid);
			this.setClientID(cid);
		}
	}
	
	public void update(ETClient client) throws ETSdkException {
    	
    	List<ETUpdateQueryObject> ll = new ArrayList<ETUpdateQueryObject>();
    	ll.add(this);
    	//this.update(client);
    	super.update(client, ll);
    	
    	/*List<com.exacttarget.fuelsdk.internal.QueryDefinition> l = new ArrayList<com.exacttarget.fuelsdk.internal.QueryDefinition>();
    	com.exacttarget.fuelsdk.internal.QueryDefinition qd = new com.exacttarget.fuelsdk.internal.QueryDefinition();
    	qd.setCustomerKey(this.getKey());
    	
    	qd.setCategoryID(this.getFolderId());
    	qd.setQueryText(this.getQueryText());
    	qd.setDataExtensionTarget(this.getDataExtensionTarget());
    	qd.setTargetUpdateType(this.getTargetUpdateType());
    	qd.setName(this.getName());
    	qd.setCreatedDate(this.getCreatedDate());
    	qd.setModifiedDate(new Date());
    	qd.setFileSpec(this.getFileSpec());
    	qd.setFileType(this.getFileType());
    	qd.setStatus(this.getStatus());
    	//com.exacttarget.fuelsdk.internal.ClientID clientId = new com.exacttarget.fuelsdk.internal.ClientID();
    	//clientId.setCustomerKey(this.getKey());
    	//qd.setClient(clientId);;
    	l.add(qd);
    	//List<ETQueryObject> l = new ArrayList<ETQueryObject>();
    	//l.add(this);
    	//this.update(client);
    	//super.update(client, l);
    	
    	 ETSoapConnection connection = client.getSoapConnection();

         //
         // Automatically refresh the token if necessary:
         //

         client.refreshToken();

         //
         // Perform the SOAP update:
         //

         Soap soap = connection.getSoap();

         UpdateRequest updateRequest = new UpdateRequest();
         updateRequest.setOptions(new UpdateOptions());
         updateRequest.getObjects().add(qd);
         
         UpdateResponse updateResponse = soap.update(updateRequest);*/
    	
    	
    }
	
}
