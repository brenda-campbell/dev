package com.mcreceiverdemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcreceiverdemo.mvcmodels.MyKey;
import com.mcreceiverdemo.repositories.DeKeyRepository;

@Service
public class MyKeyCollectionServiceImpl implements MyKeyCollectionService {

	@Autowired
    private DeKeyRepository keyRepository; 
	
	
	@Override
	public List<MyKey> findAll() {
		return this.keyRepository.findAll();
	}

	@Override
	public void add(MyKey key) {
		this.keyRepository.add(key);
	}

	@Override
	public void clear() {
		this.keyRepository.clear();
	}

}
