package com.mcreceiverdemo.mc;

import org.springframework.stereotype.Service;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;

@Service
public interface MCClientService {
	
	public void Init() throws ETSdkException;
	public ETClient getEtClient();
}
