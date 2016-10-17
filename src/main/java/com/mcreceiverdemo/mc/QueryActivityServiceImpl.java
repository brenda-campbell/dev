package com.mcreceiverdemo.mc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.internal.InteractionBaseObject;
import com.mcreceiverdemo.et.ETRetrieveQueryObject;
import com.mcreceiverdemo.et.ETUpdateQueryObject;
import com.mcreceiverdemo.exceptions.CustomException;

@Service
public class QueryActivityServiceImpl extends CommonMcServiceImpl implements QueryActivityService, CommonMcService {
	
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public QueryActivityServiceImpl(){
		super();
	}
	
	@Override
	public ETRetrieveQueryObject retrieve(String customerKey) throws CustomException, ETSdkException {
		ETRetrieveQueryObject x = new ETRetrieveQueryObject().select(this.mcClient.getETClientObject(), customerKey).getObject();
		return x;
	}
	
	@Override
	public void uatToProd(ETRetrieveQueryObject etQuery) throws CustomException, ETSdkException{
		ETUpdateQueryObject u = new ETUpdateQueryObject();
		u.cloneQueryObject(etQuery, this.mcClient.getMID());
		String queryText = etQuery.getQueryText();
		queryText = queryText.replace("(?i)uat_", "").replace("(?i)_uat", "");
		u.update(this.mcClient.getETClientObject());
	}
	
	@Override
	public void uatToProd(String key) throws Exception {
		
		ETRetrieveQueryObject x = this.retrieve(key);
		if(x == null) {
			throw new CustomException(String.format("no Query Activiry found."));
		}	
		this.uatToProd(x);
	}

	@Override
	public void uatToProd(String key, String targetDEObjectId, String targetDECustomerKey, String targetDEName) throws CustomException, ETSdkException {
		ETRetrieveQueryObject x = this.retrieve(key);
		if(x == null) {
			throw new CustomException(String.format("no Query Activiry found."));
		}	
		this.uatToProd(x, targetDEObjectId, targetDECustomerKey, targetDEName);
	}

	@Override
	public void uatToProd(ETRetrieveQueryObject etQuery, String targetDEObjectId, String targetDECustomerKey, String targetDEName) throws CustomException, ETSdkException {
		// TODO Auto-generated method stub
		ETUpdateQueryObject u = new ETUpdateQueryObject();
		u.cloneQueryObject(etQuery, this.mcClient.getMID());
		InteractionBaseObject targetDE = new InteractionBaseObject();
		targetDE.setObjectID(targetDEObjectId);
		targetDE.setCustomerKey(targetDECustomerKey);
		targetDE.setName(targetDEName);
		u.setDataExtensionTarget(targetDE);
		String queryText = etQuery.getQueryText();
		queryText = queryText.replaceAll("(?i)uat_", "");
		queryText = queryText.replaceAll("(?i)_uat", "");
		u.setQueryText(queryText);
		u.update(this.mcClient.getETClientObject());
	}
}
