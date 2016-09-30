package com.mcreceiverdemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcreceiverdemo.mvcmodels.DeKey;
import com.mcreceiverdemo.repositories.DeKeyRepository;

@Service
public class DeKeyCollectionServiceImpl implements DeKeyCollectionService {

	@Autowired
    private DeKeyRepository keyRepository; 
	
	
	@Override
	public List<DeKey> findAll() {
		return this.keyRepository.findAll();
	}

	@Override
	public void add(DeKey key) {
		this.keyRepository.add(key);
	}

	@Override
	public void clear() {
		this.keyRepository.clear();
	}

}
