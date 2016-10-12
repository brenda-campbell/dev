package com.mcreceiverdemo.mc;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETApiObject;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.ETSoapObject;
import com.mcreceiverdemo.et.ETQueryObject;
import com.mcreceiverdemo.et.ETRetrieveQueryObject;
import com.mcreceiverdemo.et.ETUpdateQueryObject;
import com.mcreceiverdemo.exceptions.CustomException;

@Service
public class QueryActivityServiceImpl extends CommonMcServiceImpl implements QueryActivityService, CommonMcService {
	
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public QueryActivityServiceImpl(){
		super();
	}
	
	public void uatToProd(String key) throws Exception {
		ETRetrieveQueryObject query = new ETRetrieveQueryObject ();
		ETRetrieveQueryObject x = query.select(this.mcClient.getETClient(), key).getObject();
		/*if(etQA == null) {
			throw new CustomException(String.format("no Query Activiry found."));
		}*/	
		ETUpdateQueryObject u = new ETUpdateQueryObject();
		u.cloneQueryObject(x);
		u.setQueryText("SELECT 'alitestseverything+3@gmail.com' as email, 'bla bla 3' as name");
		u.update(this.mcClient.getETClient());
		//ETSoapObject.update(this.mcClient.getETClient(), list);
	}
	
}
