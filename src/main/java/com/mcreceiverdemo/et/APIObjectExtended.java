package com.mcreceiverdemo.et;

import javax.xml.bind.annotation.XmlElement;

import com.exacttarget.fuelsdk.internal.APIObject;

public class APIObjectExtended extends APIObject {
	@XmlElement(name = "Name")
    protected String name;

	protected String objectType;
	
	private String clonedObjectCustomerKey;
	
	private String clonedObjectObjectID;
	
	private String clonedObjectName;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getClonedObjectCustomerKey() {
		return clonedObjectCustomerKey;
	}

	public void setClonedObjectCustomerKey(String clonedObjectCustomerKey) {
		this.clonedObjectCustomerKey = clonedObjectCustomerKey;
	}

	public String getClonedObjectObjectID() {
		return clonedObjectObjectID;
	}

	public void setClonedObjectObjectID(String clonedObjectObjectID) {
		this.clonedObjectObjectID = clonedObjectObjectID;
	}

	public String getClonedObjectName() {
		return clonedObjectName;
	}

	public void setClonedObjectName(String clonedObjectName) {
		this.clonedObjectName = clonedObjectName;
	}
	
	
	
}
