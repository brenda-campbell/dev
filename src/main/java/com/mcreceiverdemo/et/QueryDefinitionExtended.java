package com.mcreceiverdemo.et;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.exacttarget.fuelsdk.internal.QueryDefinition;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryDefinition", propOrder = {
    "queryText",
    "targetType",
    "dataExtensionTarget.CustomerKey",
    "targetUpdateType",
    "fileSpec",
    "fileType",
    "status",
    "categoryID"
})
public class QueryDefinitionExtended extends QueryDefinition {
	@XmlElement(name = "DataExtensionTarget.CustomerKey")
    protected String dataExtensionTargetCustomerKey;
	
	
	
    public String getDataExtensionTargetCustomerKey() {
        return dataExtensionTargetCustomerKey;
    }

    public void setDataExtensionTargetCustomerKey(String value) {
        this.dataExtensionTargetCustomerKey = value;
    }
}
