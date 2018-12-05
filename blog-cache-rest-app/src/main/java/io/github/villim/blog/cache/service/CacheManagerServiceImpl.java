package io.github.villim.blog.cache.service;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CacheManagerServiceImpl implements CacheManagerService {


    private static final String CACHE_NAME = "ACS_CACHE";

    @Autowired
    private HazelcastInstance cacheHazelcastInstance;

    public void putIntoCache(String key, Object value) {
        Map<String, Object> hzMap = initHazelcastMap();
        hzMap.put(key.toLowerCase(), value);
    }


    @Override
    public Object getFromCache(String key) {
        Map<String, Object> hzMap = initHazelcastMap();
        return hzMap.get(key);
    }

    private Map<String, Object> initHazelcastMap() {
        Map<String, Object> acsMap = cacheHazelcastInstance.getMap(CACHE_NAME);
        return acsMap;
    }

}
