package com.mcreceiverdemo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcreceiverdemo.mvcmodels.MyData;

@Repository
public class MyDataRepository {
	
	private List<MyData> myDataCollection = new ArrayList<MyData>();
    
    public MyDataRepository() {
        super();
    }
    
    public List<MyData> findAll() {
        return new ArrayList<MyData>(this.myDataCollection);
    }
    
    public void add(final MyData data) {
        this.myDataCollection.add(data);
    }
    
    public void clear() {
    	this.myDataCollection.clear();
    	this.myDataCollection = new ArrayList<MyData>();
    }
}
