package com.mcreceiverdemo.mc;

import com.exacttarget.fuelsdk.ETSdkException;
import com.mcreceiverdemo.et.ETRetrieveQueryObject;
import com.mcreceiverdemo.exceptions.CustomException;

public interface QueryActivityService {
	public void uatToProd(String key) throws Exception;
	public ETRetrieveQueryObject retrieve(String customerKey) throws CustomException, ETSdkException;
	void uatToProd(ETRetrieveQueryObject etQuery) throws CustomException, ETSdkException;
	void uatToProd(ETRetrieveQueryObject etQuery, String targetDEObjectId, String targetDECustomerKey, String targetDEName) throws CustomException, ETSdkException;
	void uatToProd(String key, String targetDEObjectId, String targetDECustomerKey, String targetDEName)
			throws CustomException, ETSdkException;
}
