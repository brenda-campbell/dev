package com.mcreceiverdemo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcreceiverdemo.mvcmodels.DeKey;


@Repository
public class DeKeyRepository {
private final List<DeKey> deKeyCollection = new ArrayList<DeKey>();
    
    public DeKeyRepository() {
        super();
    }
    
    public List<DeKey> findAll() {
        return new ArrayList<DeKey>(this.deKeyCollection);
    }
    
    public void add(final DeKey key) {
        this.deKeyCollection.add(key);
    }
    
    public void clear() {
    	this.deKeyCollection.clear();
    }
}
