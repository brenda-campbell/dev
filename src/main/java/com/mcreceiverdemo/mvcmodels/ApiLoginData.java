package com.mcreceiverdemo.mvcmodels;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class ApiLoginData {
	
	@NotNull
	private String apiKey;
	@NotNull
	private String apiSecret;
	
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getApiSecret() {
		return apiSecret;
	}
	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}
}
