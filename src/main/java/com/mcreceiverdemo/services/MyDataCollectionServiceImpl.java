package com.mcreceiverdemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcreceiverdemo.mvcmodels.MyData;
import com.mcreceiverdemo.repositories.MyDataRepository;

@Service
public class MyDataCollectionServiceImpl implements MyDataCollectionService {
	
	@Autowired
    private MyDataRepository nameValueRepository; 
	
	@Override
	public List<MyData> findAll() {
		return this.nameValueRepository.findAll();
	}

	@Override
	public void add(MyData nameValue) {
		this.nameValueRepository.add(nameValue);
	}
	
	@Override
	public void clear() {
		this.nameValueRepository.clear();
	}

}
