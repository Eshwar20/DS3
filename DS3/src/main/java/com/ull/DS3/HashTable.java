package com.ull.DS3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Hashtable;
import java.util.Set;

@Configuration
public class HashTable {
    private Hashtable<Integer, String> hashtable;

    public HashTable() {
        this.hashtable = new Hashtable<>();
    }

    public void put(Integer id, String key) {
        this.hashtable.put(id, key);
    }

    public String get(int key) {
        return this.hashtable.get(key);
    }

    public Set<Integer> keySet() {
        return this.hashtable.keySet();
    }

}
