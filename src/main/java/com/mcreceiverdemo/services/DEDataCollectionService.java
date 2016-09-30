package com.mcreceiverdemo.services;

import java.util.List;

import com.mcreceiverdemo.mvcmodels.DeData;

public interface DEDataCollectionService {
	public List<DeData> findAll();
	public void add(final DeData nameValue);
	public void clear();
}
