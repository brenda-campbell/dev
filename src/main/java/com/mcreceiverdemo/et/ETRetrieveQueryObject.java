package com.mcreceiverdemo.et;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETExpression;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;

@SoapObject(internalType = com.mcreceiverdemo.et.QueryDefinition.class, unretrievable = {
	    "ID"
	})
public class ETRetrieveQueryObject extends ETQueryObject {
	
	public ETResponse<ETRetrieveQueryObject> select(ETClient client, String key) throws ETSdkException {
    	ETFilter etFilter = new ETFilter();
		ETExpression etExpression = new ETExpression();
		etExpression.setProperty("CustomerKey");
		etExpression.setValue(key);
		etExpression.setOperator(com.exacttarget.fuelsdk.ETExpression.Operator.EQUALS);
		etFilter.setExpression(etExpression);
		ETResponse<ETRetrieveQueryObject> x = (ETResponse<ETRetrieveQueryObject>) this.retrieve(client, "QueryDefinition", etFilter, this.getClass());
		return x;    	
    }
}
