package com.mcreceiverdemo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcreceiverdemo.mvcmodels.MyKey;


@Repository
public class DeKeyRepository {
private final List<MyKey> deKeyCollection = new ArrayList<MyKey>();
    
    public DeKeyRepository() {
        super();
    }
    
    public List<MyKey> findAll() {
        return new ArrayList<MyKey>(this.deKeyCollection);
    }
    
    public void add(final MyKey key) {
        this.deKeyCollection.add(key);
    }
    
    public void clear() {
    	this.deKeyCollection.clear();
    }
}
