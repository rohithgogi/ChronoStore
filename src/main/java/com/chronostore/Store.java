package com.chronostore;

import java.util.HashMap;

public class Store {
    private final HashMap<String, String> map=new HashMap<>();

    public void set(String key,String value){
        map.put(key,value);
    }

    public String get(String key){
        return map.getOrDefault(key,null);
    }

    public boolean delete(String key){
        return map.remove(key)!=null;
    }
}
