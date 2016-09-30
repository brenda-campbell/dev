package com.mcreceiverdemo.mvcmodels;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;

@Component
public class DeKey {
	
	private int idx = 0;
	
	//@Min(1)
	private List<String> keys = new ArrayList<String>();

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	} 
	
	
}
