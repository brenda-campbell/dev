package com.mcreceiverdemo.et;

import com.exacttarget.fuelsdk.annotations.RestObject;
import com.exacttarget.fuelsdk.annotations.SoapObject;

@RestObject(path = "/data/v1/customobjectdata/key/{key}/rowset",
			primaryKey = "key",
			collection = "items",
			totalCount = "count")
@SoapObject(internalType = com.mcreceiverdemo.et.DataExtension.class, unretrievable = {
		"Fields"
})
public class ETRetrieveDataExtensionObject extends ETDataExtensionObject {
	
}
