package com.mcreceiverdemo.mvcmodels;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class NameValue {
	
	@NotNull @NotEmpty(message = "{NameValue.name.required}")
	protected String name;
	@NotNull @NotEmpty(message = "{NameValue.value.required}")
	protected String value;
	
	protected String key;
	
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
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
    public String toString() {
        return "NameValue [name=" + this.name + ", value=" + this.value + ", key="+this.key+"]";
    }
}
