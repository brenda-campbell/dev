package com.mcreceiverdemo.et;

import java.util.Date;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETDataExtension;
import com.exacttarget.fuelsdk.ETExpression;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;

import com.exacttarget.fuelsdk.internal.QueryDefinition;
import com.exacttarget.fuelsdk.internal.DataExtension;
import com.exacttarget.fuelsdk.internal.InteractionBaseObject;

@SoapObject(internalType = QueryDefinitionExtended.class, unretrievable = {
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
    
    @InternalName("dataExtensionTarget.CustomerKey")
    //@ExternalName("dataExtensionTarget")
    //private InteractionBaseObject dataExtensionTarget = null;
    
    
    private String dataExtensionTargetCustomerKey = null;
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
    
    public String getDataExtensionTargetCustomerKey() {
    	return this.dataExtensionTargetCustomerKey;
    }
    
    public void setDataExtensionTargetCustomerKey(String dataExtensionTargetCustomerKey) {
    	this.dataExtensionTargetCustomerKey = dataExtensionTargetCustomerKey;
    }
    
    /*public InteractionBaseObject getDataExtensionTarget() {
    	return this.dataExtensionTarget;
    }
    
    public void setDataExtensionTarget(InteractionBaseObject dataExtensionTarget) {
    	this.dataExtensionTarget = dataExtensionTarget;
    }*/
    
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
}
