package io.github.villim.blog.cache.controller;

import io.github.villim.blog.cache.service.CacheManagerService;
import io.github.villim.blog.cache.utils.CacheKeyDecoder;
import io.github.villim.blog.domain.Version;
import io.github.villim.blog.domain.cache.CacheObject;
import io.github.villim.blog.domain.cache.CacheObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
public class CacheRestController {

    private static final Logger LOG = LoggerFactory.getLogger(CacheRestController.class);


    @Value("${sparkpost.authKey}")
    private String authKey;

    @Value("${sparkpost.baseUrl}")
    private String baseUrl;
    @Autowired
    private Environment env;

    @Autowired
    private CacheManagerService cacheManagerService;

    @RequestMapping(method = RequestMethod.GET, value = "/version", produces = "application/json")
    public Version getVersion() {
        LOG.info("invoke CacheController.version()");
        String projectVersion = env.getProperty("project.version", "");
        String gitVersion = env.getProperty("git.version", "");
        return new Version(projectVersion, gitVersion);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/v1/key/{key}", produces = "application/json")
    public CacheObject getByKey(@PathVariable String key) {
        LOG.debug("get cacheObject with key [{}]", key);

        String decodedKey = CacheKeyDecoder.getDecodedKey(key);

        Object value;
        try {
            value = cacheManagerService.getFromCache(decodedKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return value == null ? null : new CacheObject(value.getClass().getName(), value);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/v1/key/{key}", consumes = "application/json", produces = "application/json")
    public void cacheWithKey(@PathVariable String key, @RequestBody String json) {
        LOG.debug("Cache Object with key [{}] and content[{}]", key, json);
        String decodedKey = CacheKeyDecoder.getDecodedKey(key);

        CacheObject currentObject = this.getByKey(decodedKey);
        if (currentObject != null) {
            throw new RuntimeException("Key [" + decodedKey + "] arealdy exsited, can not cache!");
        }

        this.cacheManagerService.putIntoCache(decodedKey, CacheObjectFactory.create(json).getObject());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/v1/key/{key}", consumes = "application/json", produces = "application/json")
    public void updateWithKey(@PathVariable String key, @RequestBody String json) {
        LOG.debug("Update Cached Object with key [{}] and content[{}]", key, json);
        this.cacheManagerService.putIntoCache(CacheKeyDecoder.getDecodedKey(key), CacheObjectFactory.create(json).getObject());
    }


}
