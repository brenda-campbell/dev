package com.mcreceiverdemo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcreceiverdemo.mvcmodels.DeData;

@Repository
public class DEDataRepository {
	
	private final List<DeData> deDataCollection = new ArrayList<DeData>();
    
    public DEDataRepository() {
        super();
    }
    
    public List<DeData> findAll() {
        return new ArrayList<DeData>(this.deDataCollection);
    }
    
    public void add(final DeData data) {
        this.deDataCollection.add(data);
    }
    
    public void clear() {
    	this.deDataCollection.clear();
    }
}
