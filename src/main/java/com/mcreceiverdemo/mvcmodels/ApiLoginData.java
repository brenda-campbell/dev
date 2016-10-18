package com.mcreceiverdemo.mvcmodels;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class ApiLoginData {
	
	@NotNull @NotEmpty(message = "{ApiLoginData.apiKey.required}")
	private String key;
	@NotNull @NotEmpty(message = "{ApiLoginData.apiSecret.required}")
	private String secret;
	
	private boolean apiLogin;
	
	private String soapEndpoint;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public boolean isApiLogin() {
		return apiLogin;
	}
	public void setApiLogin(boolean apiLogin) {
		this.apiLogin = apiLogin;
	}
	public String getSoapEndpoint() {
		return soapEndpoint;
	}
	public void setSoapEndpoint(String soapEndpoint) {
		this.soapEndpoint = soapEndpoint;
	}
	
}
