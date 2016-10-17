package com.mcreceiverdemo.et;

import java.util.Date;

import com.exacttarget.fuelsdk.ETFolder;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.InternalProperty;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.DataFolder;

/**
* An <code>ETFolder</code> object represents a folder
* in the Salesforce Marketing Cloud.
*/

@SoapObject(internalType = DataFolder.class)
public class ETFolderObject extends ETSoapObjectExtended {
	 @ExternalName("id")
	 private String id = null;
	 @ExternalName("key")
	 @InternalName("customerKey")
	 private String key = null;
	 @ExternalName("name")
	 private String name = null;
	 @ExternalName("description")
	 private String description = null;
	 @ExternalName("createdDate")
	 private Date createdDate = null;
	 @ExternalName("modifiedDate")
	 private Date modifiedDate = null;
	 @ExternalName("contentType")
	 private String contentType = null;
	 @ExternalName("parentFolder")
	 @InternalProperty("ParentFolder.CustomerKey")
	 @InternalName("parentFolder")
	 private DataFolder parentFolder = null;
	 @ExternalName("isActive")
	 private Boolean isActive = null;
	 @ExternalName("isEditable")
	 private Boolean isEditable = null;
	 @ExternalName("allowChildren")
	 private Boolean allowChildren = null;

	 public ETFolderObject() {}

	 public String getId() {
	     return id;
	 }
	
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
	
	 public String getDescription() {
	     return description;
	 }
	
	 public void setDescription(String description) {
	     this.description = description;
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
	
	 public String getContentType() {
	     return contentType;
	 }
	
	 public void setContentType(String contentType) {
	     this.contentType = contentType;
	 }
	
	 public String getParentFolderKey() {
	     if (parentFolder == null) {
	         return null;
	     }
	     return parentFolder.getCustomerKey();
	 }
	
	 public void setParentFolderKey(String parentFolderKey) {
	     if (parentFolder == null) {
	         parentFolder = new DataFolder();
	     }
	     parentFolder.setCustomerKey(parentFolderKey);
	 }
	
	 public Boolean getIsActive() {
	     return isActive;
	 }
	
	 public void setIsActive(Boolean isActive) {
	     this.isActive = isActive;
	 }
	
	 public Boolean getIsEditable() {
	     return isEditable;
	 }
	
	 public void setIsEditable(Boolean isEditable) {
	     this.isEditable = isEditable;
	 }
	
	 public Boolean getAllowChildren() {
	     return allowChildren;
	 }
	
	 public void setAllowChildren(Boolean allowChildren) {
	     this.allowChildren = allowChildren;
	 }
	
	 /**
	  * @deprecated
	  * Use <code>getKey()</code>.
	  */
	 @Deprecated
	 public String getCustomerKey() {
	     return getKey();
	 }
	
	 /**
	  * @deprecated
	  * Use <code>setKey()</code>.
	  */
	 @Deprecated
	 public void setCustomerKey(String customerKey) {
	     setKey(customerKey);
	 }
	
	 /**
	  * @deprecated
	  * Use <code>getParentFolderKey()</code>.
	  */
	 @Deprecated
	 public DataFolder getParentFolder() {
	     return parentFolder;
	 }
	
	 /**
	  * @deprecated
	  * Use <code>setParentFolderKey()</code>.
	  */
	 //@Deprecated
	 public void setParentFolder(DataFolder parentFolder) {
		 this.parentFolder = new DataFolder();
	     this.parentFolder.setAllowChildren(parentFolder.getAllowChildren()); 
	     this.parentFolder.setContentType(parentFolder.getContentType());
	     this.parentFolder.setCreatedDate(parentFolder.getCreatedDate());
	     this.parentFolder.setCustomerKey(parentFolder.getCustomerKey());
	     this.parentFolder.setDescription(parentFolder.getDescription());
	     this.parentFolder.setObjectID(parentFolder.getObjectID());
	     this.parentFolder.setIsActive(parentFolder.getIsActive());
	     this.parentFolder.setIsEditable(parentFolder.getIsEditable());
	     this.parentFolder.setName(parentFolder.getName());
	     //this.parentFolder.setParentFolderKey(parentFolder.getParentFolder() == null ? null : parentFolder.getParentFolder().getCustomerKey());
	 }
}

