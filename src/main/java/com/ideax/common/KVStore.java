package com.ideax.common;

public interface KVStore {
    public <T> T get(Object key, Class<T> clz);

    public Object get(Object key);

    public <T> void put(Object key, T value);
    
    public void clear();
}
