package com.servlet3.sample.common.abs;

import org.bson.BasicBSONObject;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class AbstractBaseEntity extends BasicBSONObject implements DBObject{

    protected boolean _isPartialObject;
    
    public boolean isPartialObject(){
        return _isPartialObject;
    }

    public void markAsPartialObject(){
        _isPartialObject = true;
    }
	
    /**
     * Returns a JSON serialization of this object
     * @return JSON serialization
     */    
    @Override
    public String toString(){
        return JSON.serialize( this );
    }

}
