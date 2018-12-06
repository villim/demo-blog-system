package io.github.villim.blog.cache.utils;

import io.villim.blog.domain.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CacheKeyDecoder {

    private static final Logger LOG = LoggerFactory.getLogger(CacheKeyDecoder.class);
    private static final String DECODER_ERROR_MESSAGE = "Unable to decode the key from url";

    private CacheKeyDecoder() {
    }

    public static String getDecodedKey(@PathVariable String key) {
        String decodedKey = null;
        try {
            decodedKey = URLDecoder.decode(key, Constants.URL_ENCODING_CHARSET);
        } catch (UnsupportedEncodingException e) {
            LOG.error(DECODER_ERROR_MESSAGE, e);
            throw new RuntimeException("Key is invalid");
        }
        return decodedKey;
    }

}
