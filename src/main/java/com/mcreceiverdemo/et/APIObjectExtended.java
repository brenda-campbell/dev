package com.mcreceiverdemo.et;

import javax.xml.bind.annotation.XmlElement;

import com.exacttarget.fuelsdk.internal.APIObject;

public class APIObjectExtended extends APIObject {
	@XmlElement(name = "Name")
    private String name;

	private String objectType;
	
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
	
	
}
