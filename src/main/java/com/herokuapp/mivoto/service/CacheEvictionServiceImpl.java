package com.herokuapp.mivoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CacheEvictionServiceImpl implements CacheEvictionService {
    @Autowired
    private CacheManager cacheManager;

    private Set<String> cachedKeys = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void addKeyToSet(String key) {
        this.cachedKeys.add(key);
    }

    private List<String> findKeyByPartialKey(String partialKey) {
        return cachedKeys.stream()
                .filter(k -> k.contains(partialKey))
                .collect(Collectors.toList());
    }

    @Override
    public void evict(String partialKey) {
        List<String> keys = findKeyByPartialKey(partialKey);
        keys.forEach(k -> cacheManager.getCache("restaurants_with_menu").evict(k));
        cachedKeys.remove(keys);
    }

    @Override
    public void evictAllRestaurantsAndMenu() {
        clearKeys();
        cacheManager.getCache("restaurants").clear();
        cacheManager.getCache("restaurants_with_menu").clear();
    }

    @Override
    public void clearKeys() {
        cachedKeys.clear();
    }
}