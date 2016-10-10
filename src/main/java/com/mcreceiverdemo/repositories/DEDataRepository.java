package com.mcreceiverdemo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcreceiverdemo.mvcmodels.MyData;

@Repository
public class DEDataRepository {
	
	private final List<MyData> deDataCollection = new ArrayList<MyData>();
    
    public DEDataRepository() {
        super();
    }
    
    public List<MyData> findAll() {
        return new ArrayList<MyData>(this.deDataCollection);
    }
    
    public void add(final MyData data) {
        this.deDataCollection.add(data);
    }
    
    public void clear() {
    	this.deDataCollection.clear();
    }
}
