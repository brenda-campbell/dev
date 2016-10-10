package com.mcreceiverdemo.mc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETApiObject;
import com.exacttarget.fuelsdk.ETObject;
import com.mcreceiverdemo.exceptions.CustomException;

@Service
public class QueryActivityServiceImpl extends CommonMcServiceImpl implements QueryActivityService, CommonMcService {
	
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public QueryActivityServiceImpl(){
		super();
	}
	
	public void uatToProd(String key) throws Exception {
		ETObject etQA = this.retrieveObject(key, ETObject.class);
		if(etQA == null) {
			throw new CustomException(String.format("no Query Activiry found."));
		}		
	}
	
}
