package com.mcreceiverdemo.et;

import com.exacttarget.fuelsdk.ETDataExtension;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.DataExtension;
import com.exacttarget.fuelsdk.internal.DataExtensionTemplate;

public class ETDataExtensionObject extends ETDataExtension {
	@ExternalName("template")
	@InternalName("template")
	private DataExtensionTemplate template;

	public DataExtensionTemplate getTemplate() {
		return template;
	}

	public void setTemplate(DataExtensionTemplate template) {
		this.template = template;
	}
	
	
}
