package io.github.villim.blog.cache.client.service;

import io.github.villim.blog.cache.client.rest.CacheRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;

import java.net.ConnectException;

@Service
public class CacheClientServiceImpl implements CacheClientService {

    private static final Logger LOG = LoggerFactory.getLogger(CacheClientService.class);

    @Autowired
    private CacheRestClient cacheClient;

    private boolean cacheServiceAvailable = true;

    @Override
    public Object fetch(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }

        Object obj = null;
        try {
            obj = this.cacheClient.fetch(key);
            synchronized (this) {
                if (!cacheServiceAvailable) {
                    LOG.warn("Cache service is available now.");
                }
                cacheServiceAvailable = true;
            }
        } catch (ResourceAccessException e) {
            if (e.getRootCause() instanceof ConnectException) {
                synchronized (this) {
                    if (cacheServiceAvailable) {
                        // record the first one only
                        LOG.warn("Please check if cache service is up.");
                        LOG.error("encounter error when fetching Cache with key:" + key, e);
                    }
                    cacheServiceAvailable = false;
                }
            }
        } catch (Exception e) {
            LOG.trace("error fetching key " + key, e);
        }
        return obj;
    }

    @Override
    public void cache(String key, Object object) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(object)) {
            return;
        }
        try {
            this.cacheClient.cache(key, object);
        } catch (Exception e) {
            LOG.error("encounter error when caching object with key:" + key, e);
        }

    }

}
