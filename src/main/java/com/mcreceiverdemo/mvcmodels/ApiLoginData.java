package com.mcreceiverdemo.mvcmodels;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class ApiLoginData {
	
	@NotNull @NotEmpty(message = "{ApiLoginData.apiKey.required}")
	private String apiKey;
	@NotNull @NotEmpty(message = "{ApiLoginData.apiSecret.required}")
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
