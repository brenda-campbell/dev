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
import com.mcreceiverdemo.exceptions.CustomException;

@Service
public class QueryActivityServiceImpl extends CommonMcServiceImpl implements QueryActivityService, CommonMcService {
	
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public QueryActivityServiceImpl(){
		super();
	}
	
	public void uatToProd(String key) throws Exception {
		ETQueryObject query = new ETQueryObject();
		ETQueryObject x = query.select(this.mcClient.getETClient(), key).getObject();
		/*if(etQA == null) {
			throw new CustomException(String.format("no Query Activiry found."));
		}*/	
		x.setQueryText("SELECT 'alitestseverything+3@gmail.com' as email, 'bla bla 3' as name");
		x.update(this.mcClient.getETClient());
		//ETSoapObject.update(this.mcClient.getETClient(), list);
	}
	
}
