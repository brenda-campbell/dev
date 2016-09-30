package com.mcreceiverdemo.mvcmodels;

import org.springframework.stereotype.Component;

@Component
public class NameValue {
	private String name;
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	@Override
    public String toString() {
        return "NameValue [name=" + this.name + ", value=" + this.value + "]";
    }
}
