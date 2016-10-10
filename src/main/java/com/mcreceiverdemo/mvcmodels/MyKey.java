package com.mcreceiverdemo.mvcmodels;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class MyKey {
	
	@NotNull
	private String bunchName;
	
	public String getBunchName() {
		return bunchName;
	}

	public void setBunchName(String bunchName) {
		this.bunchName = bunchName;
	}

	@Size(min=1)
	private List<String> keys = new ArrayList<String>();

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}
	
	
}
