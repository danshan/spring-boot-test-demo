package com.shanhh.demo.cache.key;

import com.google.common.base.Preconditions;

import lombok.Builder;

/**
 * @author dan
 * @since 2017-04-22 07:21
 */
@Builder
public class SessionKey extends CacheKey {

    private static final String VERSION = "1";
    private String sessionId;

    @Override
    public String buildKey() {
        return String.format("%s:%s:v:%s:sid:%s",
                DOMAIN, getClass().getCanonicalName(), VERSION,
                Preconditions.checkNotNull(sessionId)
        );
    }
}
