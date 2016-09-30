package com.mcreceiverdemo.services;

import java.util.List;

import com.mcreceiverdemo.mvcmodels.DeKey;

public interface DeKeyCollectionService {
	public List<DeKey> findAll();
	public void add(final DeKey key);
	public void clear();
}
