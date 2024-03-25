package org.example.redis;


import lombok.Getter;

import java.util.concurrent.TimeUnit;


@Getter
public enum RedisKey {
    COMMENT_HASH("CommentHash:");

    RedisKey(String prefix, TimeUnit unit,int expireTime){
        this.prefix = prefix;
        this.unit = unit;
        this.expireTime = expireTime;
    }

    RedisKey(String prefix) {
        this.prefix = prefix;
    }

    public String getRealKey(String key) {
        return this.prefix + key;
    }

    private String prefix;
    private TimeUnit unit;
    private int expireTime;

}
