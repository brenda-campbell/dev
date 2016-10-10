package com.mcreceiverdemo.services;

import java.util.List;

import com.mcreceiverdemo.mvcmodels.MyData;

public interface MyDataCollectionService {
	public List<MyData> findAll();
	public void add(final MyData nameValue);
	public void clear();
}
