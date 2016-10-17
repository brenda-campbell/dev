package com.mcreceiverdemo.et;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETExpression;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETResult;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETSoapConnection;
import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.ClientID;
import com.exacttarget.fuelsdk.internal.RetrieveRequest;
import com.exacttarget.fuelsdk.internal.RetrieveRequestMsg;
import com.exacttarget.fuelsdk.internal.RetrieveResponseMsg;
import com.exacttarget.fuelsdk.internal.Soap;

public abstract class ETSoapObjectExtended extends ETSoapObject {
	
	private static Logger logger = Logger.getLogger(ETSoapObjectExtended.class);
	
	public ETSoapObjectExtended() {
        super();
    }
	
	protected static <T extends ETSoapObjectExtended> ETResponse<T> retrieve(ETClientObject client, String soapObjectName, ETFilter filter, String continueRequest, Class<T> type, Integer MID) throws ETSdkException {
		ETResponse<T> response = new ETResponse<T>();

		//
		// Get handle to the SOAP connection:
		//

		ETSoapConnection connection = client.getSoapConnection();

		//
		// Automatically refresh the token if necessary:
		//

		client.refreshToken();

		//
		// Read internal type from the SoapObject annotation:
		//

		Class<T> externalType = type; // for code readability

		SoapObject internalTypeAnnotation = externalType.getAnnotation(SoapObject.class);
		assert internalTypeAnnotation != null;
		Class<? extends APIObject> internalType = internalTypeAnnotation.internalType();
		assert internalType != null;
	
		ETExpression expression = filter.getExpression();
	
		//
		// Determine properties to retrieve:
		//
	
		List<String> externalProperties = filter.getProperties();
		List<String> internalProperties = null;
	
		if (externalProperties.size() > 0) {
			//
			// Only request those properties specified:
			//
			
			internalProperties = new ArrayList<String>();
	
			for (String externalProperty : externalProperties) {
				String internalProperty = getInternalProperty(externalType, externalProperty);
				assert internalProperty != null;
				internalProperties.add(internalProperty);
			}
		} else {
			//
			// No properties were explicitly requested:
			//
	
			internalProperties = getInternalProperties(externalType);
	
			//
			// Remove properties that are unretrievable:
			//
	
			for (String property : internalTypeAnnotation.unretrievable()) {
				internalProperties.remove(property);
			}
		}
	
		//
		// Perform the SOAP retrieve:
		//
	
		Soap soap = connection.getSoap();
	
		RetrieveRequest retrieveRequest = new RetrieveRequest();
		
		// catering for Business Unit
		if(MID != null && MID.intValue() > 0) {
			ClientID cid = new ClientID();
			cid.setClientID(MID);
			//retrieveRequest.setQueryAllAccounts(true);
			retrieveRequest.getClientIDs().add(cid);
		}
		
		if (continueRequest == null) {
			// if soapObjectType is specified, use it; otherwise, default
			// to the name of the internal class representing the object:
			if (soapObjectName != null) {
				retrieveRequest.setObjectType(soapObjectName);
			} else {
				retrieveRequest.setObjectType(internalType.getSimpleName());
			}
			retrieveRequest.getProperties().addAll(internalProperties);
	
			if (expression.getOperator() != null) {
				//
				// Convert the property names to their internal counterparts:
				//
	
				String property = expression.getProperty();
				if (property != null) {
					expression.setProperty(getInternalProperty(type, property));
				}
				for (ETExpression subexpression : expression.getSubexpressions()) {
					String p = subexpression.getProperty();
					if (p != null) {
						subexpression.setProperty(getInternalProperty(type, p));
					}
				}
	
				retrieveRequest.setFilter(toFilterPart(expression));
			}
		} else {
			if (continueRequest != null) {
				retrieveRequest.setContinueRequest(continueRequest);
			}
		}
	
		if (logger.isTraceEnabled()) {
			logger.trace("RetrieveRequest:");
			logger.trace("  objectType = " + retrieveRequest.getObjectType());
			StringBuilder line = new StringBuilder("  properties = { ");
			//String line = null;
			for (String property : retrieveRequest.getProperties()) {
	
				line.append(property).append(", ");
			}
			if (retrieveRequest.getProperties().size() > 0) {
				line.setLength(line.length() - 2);	 
				/*if (line == null) {
				line = "  properties = { " + property;
				} else {
				line += ", " + property;
				}*/
			}
			line.append(" }");
			logger.trace(line.toString());	            
			//logger.trace(line + " }");
			if (filter != null) {
				logger.trace("  filter = " + toFilterPart(expression));
			}
		}
		
		logger.trace("calling soap.retrieve...");
	
		RetrieveRequestMsg retrieveRequestMsg = new RetrieveRequestMsg();
		retrieveRequestMsg.setRetrieveRequest(retrieveRequest);
	
		RetrieveResponseMsg retrieveResponseMsg = soap.retrieve(retrieveRequestMsg);
	
		if (logger.isTraceEnabled()) {
			logger.trace("RetrieveResponseMsg:");
			logger.trace("  requestId = " + retrieveResponseMsg.getRequestID());
			logger.trace("  overallStatus = " + retrieveResponseMsg.getOverallStatus());
			logger.trace("  results = {");
			for (APIObject result : retrieveResponseMsg.getResults()) {
				logger.trace("    " + result);
			}
			logger.trace("  }");
		}
	
		response.setRequestId(retrieveResponseMsg.getRequestID());
		if (retrieveResponseMsg.getOverallStatus().equals("OK")) {
			response.setStatus(ETResult.Status.OK);
		} else if (retrieveResponseMsg.getOverallStatus().equals("Error")) {
			response.setStatus(ETResult.Status.ERROR);
		}
		response.setResponseCode(retrieveResponseMsg.getOverallStatus());
		response.setResponseMessage(retrieveResponseMsg.getOverallStatus());
		for (APIObject internalObject : retrieveResponseMsg.getResults()) {
			//
			// Allocate a new (external) object:
			//
	
			T externalObject = null;
			try {
				externalObject = externalType.newInstance();
			} catch (Exception ex) {
				throw new ETSdkException("could not instantiate "+ externalType.getName(), ex);
			}
			
			externalObject.setClient(client);
			
			//
			// Convert from internal representation:
			//
			
			externalObject.fromInternal(internalObject);
			
			//
			// Add result to the list of results:
			//
	
			ETResult<T> result = new ETResult<T>();
			result.setObject(externalObject);
			response.addResult(result);
		}
	
		if (retrieveResponseMsg.getOverallStatus().equals("MoreDataAvailable")) {
			response.setMoreResults(true);
		}
	
		return response;
	}
	
	
	
	
	public ETSoapObjectExtended fromInternal(APIObject internalObject)
	        throws ETSdkException
	    {
	        ETSoapObjectExtended externalObject = this; // for code readability

	        Class<? extends ETSoapObjectExtended> externalType = externalObject.getClass();
	        String externalTypeName = externalType.getSimpleName();
	        Class<? extends APIObject> internalType = internalObject.getClass();
	        String internalTypeName = internalType.getSimpleName();

	        logger.trace("converting object from internal type "
	                + internalTypeName);
	        logger.trace("                    to external type "
	                + externalTypeName);

	        for (Field externalField : getAllFields(externalType)) {
	            //
	            // Skip this field if it doesn't have the @ExternalName
	            // annotation (it's an internal field):
	            //

	            ExternalName externalName =
	                    externalField.getAnnotation(ExternalName.class);
	            if (externalName == null) {
	                continue;
	            }

	            String externalFieldName = externalField.getName();
	            String internalFieldName = null;

	            InternalName internalName =
	                    externalField.getAnnotation(InternalName.class);

	            if (internalName != null) {
	                internalFieldName = internalName.value();
	            } else {
	                // internal name is the same as external name
	                internalFieldName = externalFieldName;
	            }

	            Object internalFieldValue = null;
	            try {
	                internalFieldValue =
	                        PropertyUtils.getProperty(internalObject,
	                                                  internalFieldName);
	            } catch (Exception ex) {
	                throw new ETSdkException("could not get property \""
	                        + internalFieldName
	                        + "\" of object "
	                        + internalObject,
	                        ex);
	            }

	            if (internalFieldValue == null) {
	                continue;
	            }

	            if (internalFieldValue instanceof List) {
	                externalField.setAccessible(true);

	                List<ETSoapObjectExtended> externalList = new ArrayList<ETSoapObjectExtended>();
	                @SuppressWarnings("unchecked")
	                List<APIObject> internalList
	                    = (List<APIObject>) internalFieldValue;

	                Type fieldType = externalField.getGenericType();
	                assert fieldType instanceof ParameterizedType;
	                ParameterizedType parameterizedType
	                    = (ParameterizedType) fieldType;
	                assert parameterizedType.getActualTypeArguments().length == 1;
	                Class<?> externalItemType
	                    = (Class<?>) parameterizedType.getActualTypeArguments()[0];

	                for (APIObject internalItem : internalList) {
	                    ETSoapObjectExtended externalItem = null;
	                    try {
	                        externalItem = (ETSoapObjectExtended) externalItemType.newInstance();
	                    } catch (Exception ex) {
	                        throw new ETSdkException("could not instantiate "
	                                + externalItemType.getName(), ex);
	                    }
	                    externalList.add(externalItem.fromInternal(internalItem));
	                }

	                try {
	                    externalField.set(externalObject, externalList);
	                } catch (Exception ex) {
	                    throw new ETSdkException("could not set field \""
	                            + externalFieldName
	                            + "\" of object "
	                            + externalObject,
	                            ex);
	                }

	                continue;
	            }

	            try {
	                BeanUtils.setProperty(externalObject,
	                                      externalFieldName,
	                                      internalFieldValue);
	            } catch (Exception ex) {
	            	
	            	try {
						externalObject.getClass().getMethod("set"+(externalFieldName.substring(0, 1).toUpperCase() + externalFieldName.substring(1)), externalField.getType()).invoke(externalObject, internalFieldValue);
					} catch (Exception exx) {

		                throw new ETSdkException("could not set property \""
		                        + externalFieldName
		                        + "\" of object "
		                        + externalObject,
		                        exx);
					}
	            	
	            }

	            if (logger.isTraceEnabled()) {
	                Field internalField = getField(internalType,
	                                               internalFieldName);

	                Object externalFieldValue = null;
	                try {
	                    externalFieldValue =
	                            PropertyUtils.getProperty(externalObject,
	                                                      externalFieldName);
	                } catch (Exception ex) {
	                    throw new ETSdkException("could not get property \""
	                            + externalFieldName
	                            + "\" of object "
	                            + externalObject,
	                            ex);
	                }

	                logger.trace("  converted field "
	                        + internalTypeName + "." + internalFieldName
	                        + " (type="
	                        + internalField.getType().getSimpleName()
	                        + ", value="
	                        + internalFieldValue
	                        + ")");
	                logger.trace("         to field "
	                        + externalTypeName + "." + externalFieldName
	                        + " (type="
	                        + externalField.getType().getSimpleName()
	                        + ", value="
	                        + externalFieldValue
	                        + ")");
	            }
	        }

	        return externalObject;
	    }
	
	

}
