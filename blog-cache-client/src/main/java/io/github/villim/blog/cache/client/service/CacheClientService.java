package io.github.villim.blog.cache.client.service;

public interface CacheClientService {

    Object fetch(String key);

    void cache(String key, Object object);
}
