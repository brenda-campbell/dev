package com.mcreceiverdemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcreceiverdemo.mvcmodels.DeData;
import com.mcreceiverdemo.repositories.DEDataRepository;

@Service
public class DEDataCollectionServiceImpl implements DEDataCollectionService {
	
	@Autowired
    private DEDataRepository nameValueRepository; 
	
	@Override
	public List<DeData> findAll() {
		return this.nameValueRepository.findAll();
	}

	@Override
	public void add(DeData nameValue) {
		this.nameValueRepository.add(nameValue);
	}
	
	@Override
	public void clear() {
		this.nameValueRepository.clear();
	}

}
