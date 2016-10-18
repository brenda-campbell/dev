package com.mcreceiverdemo.et;

import java.lang.reflect.Method;
import com.exacttarget.fuelsdk.ETApiObject;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;

/**
* <code>ETClient</code> is the central object in the Java
* client library.
*/

public class ETClientObject extends ETClient {
	
 
	 public ETClientObject() throws ETSdkException {
	     super();
	 }

	 public ETClientObject(String file) throws ETSdkException {
	     super(file);
	 }

	 public ETClientObject(ETConfiguration configuration) throws ETSdkException {
		 super(configuration);
	 }


	 public <T extends ETApiObject> ETResponse<T> retrieve(Class<T> type, ETFilter filter, Integer MID) throws ETSdkException {
	     //
	     // Find the retrieve method:
	     //

	     Method retrieve = null;
	     for (Class<T> t = type; t != null; t = (Class<T>) t.getSuperclass()) {
	         try {
	             retrieve = t.getDeclaredMethod("retrieve",
	                                            ETClientObject.class,  // client
	                                            String.class,	// soapObjectName
	                                            ETFilter.class,	// filter
	                                            String.class,	// continueRequest
	                                            Class.class,     // type
	                                            Integer.class    // MID
	                                            ); 
	             if (retrieve != null) {
	                 break;
	             }
	         } catch (Exception ex) {
	             // ignore
	         }
	     }

	     if (retrieve == null) {
	         throw new ETSdkException("could not find retrieve method for type " + type);
	     }

	     ETResponse<T> response = null;
	     try {
	         // first argument of null means method is static
	         response = (ETResponse<T>) retrieve.invoke(null,
	                                                    this,
	                                                    null,
	                                                    filter,
	                                                    null,
	                                                    type,
	                                                    MID);
	     } catch (Exception ex) {
	         throw new ETSdkException("error invoking retrieve method for type " + type, ex);
	     }

	     return response;
	 }

}
