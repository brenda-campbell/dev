package com.mcreceiverdemo.mvcmodels;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class MyData {
	
	@NotNull @NotEmpty(message = "{MyData.key.required}")
	private String key;
	
	@Size(min=1, max=400)
	private List<NameValue> nameValues = new ArrayList<NameValue>();
	
	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	
	public List<NameValue> getNameValues() {
        return this.nameValues;
    }


    @Override
    public String toString() {
        return "DEData [key=" + this.key + ", rows=" + this.nameValues + "]";
    }
	
	
}
