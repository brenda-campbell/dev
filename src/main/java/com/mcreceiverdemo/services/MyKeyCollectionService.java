package com.mcreceiverdemo.services;

import java.util.List;

import com.mcreceiverdemo.mvcmodels.MyKey;

public interface MyKeyCollectionService {
	public List<MyKey> findAll();
	public void add(final MyKey key);
	public void clear();
}
