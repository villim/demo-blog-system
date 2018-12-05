package io.github.villim.blog.cache.service;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;

import java.util.Collection;

public interface CacheManagerService {


    public void putIntoCache(String key, Object value);

    public Object getFromCache(String key);

}
