package com.mcreceiverdemo.et;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

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
import com.exacttarget.fuelsdk.internal.ClientID;
import com.exacttarget.fuelsdk.internal.InteractionBaseObject;
import com.exacttarget.fuelsdk.internal.Soap;
import com.exacttarget.fuelsdk.internal.UpdateOptions;
import com.exacttarget.fuelsdk.internal.UpdateRequest;
import com.exacttarget.fuelsdk.internal.UpdateResponse;


public class ETQueryObject extends ETSoapObject  {

	@ExternalName("id")
    private String id = null;
	@ExternalName("objectID")
    protected String objectID;
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
    @ExternalName("fileSpec")
    private String fileSpec = null;
    @ExternalName("fileType")
    private String fileType = null;
    @ExternalName("status")
    private String status = null;
    
    @ExternalName("queryText")
    private String queryText = null;
    @ExternalName("targetType")
    private String targetType = null;
    
    @InternalName("dataExtensionTarget")
    @ExternalName("dataExtensionTarget")
    protected InteractionBaseObject dataExtensionTarget = null;

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

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String id) {
        this.objectID = id;
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
    
    
    public void setFileSpec(String fileSpec) {
    	this.fileSpec =fileSpec;
    }
    
    public String getFileSpec() {
    	return this.fileSpec;
    }
    
    public void setFileType(String fileType) {
    	this.fileType =fileType;
    }
    
    public String getFileType() {
    	return this.fileType;
    }
    
    public void setStatus(String status) {
    	this.status =status;
    }
    
    public String getStatus() {
    	return this.status;
    }
    
    public void applyDataExtensionTarget(InteractionBaseObject dataExtensionTarget) {
    	this.dataExtensionTarget = dataExtensionTarget;
    }
    
    /*
    public ETResponse<ETQueryObject> select(ETClient client, ETFilter etFilter, T ) throws ETSdkException {
    	ETFilter etFilter = new ETFilter();
		ETExpression etExpression = new ETExpression();
		etExpression.setProperty("CustomerKey");
		etExpression.setValue(key);
		etExpression.setOperator(com.exacttarget.fuelsdk.ETExpression.Operator.EQUALS);
		etFilter.setExpression(etExpression);
		ETResponse<ETQueryObject> x = (ETResponse<ETQueryObject>) super.retrieve(client, "QueryDefinition", etFilter, this.getClass());
		return x;    	
    }
    */
    
    /*public void update(ETClient client) throws ETSdkException {
    	List<com.exacttarget.fuelsdk.internal.QueryDefinition> l = new ArrayList<com.exacttarget.fuelsdk.internal.QueryDefinition>();
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
         
         UpdateResponse updateResponse = soap.update(updateRequest);
    	
    	
    }*/
}
