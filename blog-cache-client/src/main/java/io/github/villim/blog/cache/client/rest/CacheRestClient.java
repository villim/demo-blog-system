package io.github.villim.blog.cache.client.rest;

import io.github.villim.blog.domain.Constants;
import io.github.villim.blog.domain.cache.CacheObject;
import io.github.villim.blog.domain.cache.CacheObjectFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class CacheRestClient {

    private static final Logger LOG = LoggerFactory.getLogger(CacheRestClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${blog.cache.rest.url}")
    private String restUrl;

    @Value("${blog.cache.rest.port}")
    private String restPort;

    //@Value("${blog.cache.rest.user}")
    //private String restUser;

    //@Value("${blog.cache.rest.password}")
    //private String restPassword;

    public Object fetch(String key) {
        String encodedKey = null;
        try {
            encodedKey = URLEncoder.encode(key, Constants.URL_ENCODING_CHARSET);
        } catch (UnsupportedEncodingException e) {
            LOG.error("Unable to encode the key", e);
            return null;
        }
        String fetchUrl = getBaseUrl() + "/v1/key/" + encodedKey;
        String json = this.restTemplate.getForObject(fetchUrl, String.class); // Without Basic Authentication

        // with basic authentication
        //HttpEntity<String> request = new HttpEntity<>(createBasicAuthenticationHeaders());
        //String json = this.restTemplate.exchange(fetchUrl, HttpMethod.GET, request, String.class).getBody();
        //if (StringUtils.isBlank(json)) {
        //    return null;
        //}

        return CacheObjectFactory.create(json).getObject();
    }

    public void cache(String key, Object object) {
        String encodedKey = null;
        try {
            encodedKey = URLEncoder.encode(key, Constants.URL_ENCODING_CHARSET);
        } catch (UnsupportedEncodingException e) {
            LOG.error("Unable to encode the key", e);
            return;
        }

        if (StringUtils.isBlank(encodedKey) || object == null) {
            throw new RuntimeException("key and Object can not be empty!");
        }
        String cacheUrl = getBaseUrl() + "/v1/key/" + encodedKey;
        restTemplate.postForObject(cacheUrl, new CacheObject(object.getClass().getName(), object), String.class);// No Authentication

        //With Authentication
        //CacheObject cacheObj = new CacheObject(object.getClass().getName(), object);
        //HttpEntity<?> request = new HttpEntity<Object>(new Gson().toJson(cacheObj), createBasicAuthenticationHeaders());
        //restTemplate.exchange(cacheUrl, HttpMethod.POST, request, String.class);
    }


    private String getBaseUrl() {
        return "http://" + restUrl + ":" + restPort + "/acs-cache-service";
    }

    //private HttpHeaders createBasicAuthenticationHeaders() {
    //    String plainCredential = restUser + ":" + restPassword;
    //    byte[] base64CredentialBytes = Base64.encodeBase64(plainCredential.getBytes());
    //    String base64Credential = new String(base64CredentialBytes);
    //    HttpHeaders headers = new HttpHeaders();
    //    headers.add("Authorization", "Basic " + base64Credential);
    //    return headers;
    //}

}
