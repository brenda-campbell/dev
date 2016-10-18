package com.mcreceiverdemo.et;

import java.util.Date;

import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;

@SoapObject(internalType = com.exacttarget.fuelsdk.internal.AccountUser.class, unretrievable = {
	    
	})
public class ETAccountUserObject extends ETSoapObject  {
	@ExternalName("id")
    private String id = null;
	//@ExternalName("objectID")
    //protected String objectID;
    @ExternalName("key")
    @InternalName("customerKey")
    private String key = null;    
    @ExternalName("createdDate")
    private Date createdDate = null;
    @ExternalName("modifiedDate")
    private Date modifiedDate = null;
    
    @ExternalName("accountUserID")
    protected Integer accountUserID = null;
    @ExternalName("name")
    private String name = null;
    @ExternalName("email")
    protected String email = null;
    @ExternalName("userID")
    protected String userID = null;
    //@ExternalName("businessUnit")
    //protected Integer businessUnit;
    @ExternalName("defaultBusinessUnit")
    protected Integer defaultBusinessUnit;
    
    @Override
    public String getId() {
		return id;
	}
    @Override
	public void setId(String id) {
		this.id = id;
	}
	/*public String getObjectID() {
		return objectID;
	}
	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}*/
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
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
	public Integer getAccountUserID() {
		return accountUserID;
	}
	public void setAccountUserID(Integer accountUserID) {
		this.accountUserID = accountUserID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/*public Integer getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(Integer businessUnit) {
		this.businessUnit = businessUnit;
	}*/
	public Integer getDefaultBusinessUnit() {
		return defaultBusinessUnit;
	}
	public void setDefaultBusinessUnit(Integer defaultBusinessUnit) {
		this.defaultBusinessUnit = defaultBusinessUnit;
	}
	
    
}
