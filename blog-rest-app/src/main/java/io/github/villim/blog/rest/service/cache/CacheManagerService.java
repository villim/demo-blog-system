package io.github.villim.blog.rest.service.cache;

public interface CacheManagerService {


    void putIntoCache(String key, Object value);

    Object getFromCache(String key);

}
