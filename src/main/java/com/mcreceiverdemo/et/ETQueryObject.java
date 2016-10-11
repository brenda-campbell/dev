package com.mcreceiverdemo.et;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETExpression;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETSoapConnection;
import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.InteractionBaseObject;
import com.exacttarget.fuelsdk.internal.Soap;
import com.exacttarget.fuelsdk.internal.UpdateOptions;
import com.exacttarget.fuelsdk.internal.UpdateRequest;
import com.exacttarget.fuelsdk.internal.UpdateResponse;

@SoapObject(internalType = QueryDefinition.class, unretrievable = {
	    "ID"
	})
public class ETQueryObject extends ETSoapObject  {

	@ExternalName("id")
    private String id = null;
    @ExternalName("key")
    @InternalName("customerKey")
    private String key = null;
    @ExternalName("name")
    private String name = null;
    @ExternalName("createdDate")
    private Date createdDate = null;
    @ExternalName("modifiedDate")
    private Date modifiedDate = null;
    @ExternalName("folderId")
    @InternalName("categoryID")
    private Integer folderId = null;
	
    @ExternalName("queryText")
    private String queryText = null;
    @ExternalName("targetType")
    private String targetType = null;
    
    @InternalName("dataExtensionTarget")
    @ExternalName("dataExtensionTarget.CustomerKey")
    private InteractionBaseObject dataExtensionTarget = null;
    //private String dataExtensionTargetCustomerKey = null;
    @ExternalName("targetUpdateType")
    private String targetUpdateType = null;
    
    
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }
    
    public String getQueryText() {
    	return this.queryText;
    }
    
    public void setQueryText(String queryText) {
    	this.queryText = queryText;
    }
    
    public String getTargetType() {
    	return this.targetType;
    }
    
    public void setTargetType(String targetType) {
    	this.targetType = targetType;
    }
    
    /*public String getDataExtensionTargetCustomerKey() {
    	return this.dataExtensionTargetCustomerKey;
    }
    
    public void setDataExtensionTargetCustomerKey(String dataExtensionTargetCustomerKey) {
    	this.dataExtensionTargetCustomerKey = dataExtensionTargetCustomerKey;
    }*/
    
    public InteractionBaseObject getDataExtensionTarget() {
    	return this.dataExtensionTarget;
    }
    
    public void setDataExtensionTarget(InteractionBaseObject dataExtensionTarget) {
    	this.dataExtensionTarget = dataExtensionTarget;
    }
    
    public String getTargetUpdateType() {
    	return this.targetUpdateType;
    }
    
    public void setTargetUpdateType(String targetUpdateType) {
    	this.targetUpdateType = targetUpdateType;
    }
    
    
    public ETResponse<ETQueryObject> select(ETClient client, String key) throws ETSdkException {
    	ETFilter etFilter = new ETFilter();
		ETExpression etExpression = new ETExpression();
		etExpression.setProperty("CustomerKey");
		etExpression.setValue(key);
		etExpression.setOperator(com.exacttarget.fuelsdk.ETExpression.Operator.EQUALS);
		etFilter.setExpression(etExpression);
		ETResponse<ETQueryObject> x = (ETResponse<ETQueryObject>) this.retrieve(client, "QueryDefinition", etFilter, this.getClass());
		return x;    	
    }
    
    public void update(ETClient client) throws ETSdkException {
    	List<QueryDefinition> l = new ArrayList<QueryDefinition>();
    	QueryDefinition qd = new QueryDefinition();
    	qd.setCustomerKey(this.getKey());
    	qd.setQueryText(this.getQueryText());
    	l.add(qd);
    	//this.update(client);
    	//ETSoapObject.update(client, l);
    	
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
         
         UpdateResponse updateResponse = soap.update(updateRequest);
    	
    	
    }
}
