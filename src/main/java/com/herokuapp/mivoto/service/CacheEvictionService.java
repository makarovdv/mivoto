package com.herokuapp.mivoto.service;

import java.util.List;

public interface CacheEvictionService {
    // Adds the provided key to a set of cached keys that we'll need later for eviction
    void addKeyToSet(String key);

    // Find keys that contain the partial key
    List<String> findKeyByPartialKey(String partialKey);

    // Evicts the cache "restaurants_with_menu" and key for an entry matching the provided key
    void evict(String key);

    // Evicts all entries in cache("restaurants", "restaurants_with_menu")
    // and clear set cached of keys for eviction
    void evictAllRestaurantsAndMenu();

    // Clear set of cached keys for eviction
    void clearKeys();
}

