package com.caxs.base.token;
/**
 * Created by kevin on 2016/9/7.
 */

import com.caxs.cache.ICacheClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.DestroyFailedException;


/**
 * CacheManager for shiro
 * 缓存管理器
 *
 * @author zhengxiang
 */

public class RedisManagerForToken {
    private static Logger logger = LoggerFactory
                .getLogger("RedisManagerForToken.class");
    @SuppressWarnings("rawtypes")
    private RedisClientForToken redisClientForToken;
    private String group;
    private ICacheClient cacheClient;

    public RedisManagerForToken() {

    }

    public void setCacheClient(ICacheClient cacheClient) {
        this.cacheClient = cacheClient;
    }


    public void destroy() throws DestroyFailedException {
        redisClientForToken = null;
        group = null;
        cacheClient= null;
    }

    @SuppressWarnings("rawtypes")
    public void setRedisClientForToken(RedisClientForToken redisClientForToken) {
        this.redisClientForToken = redisClientForToken;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @SuppressWarnings("unchecked")

    public  RedisClientForToken<String, Object>  getCache()  {
        if(redisClientForToken == null) {
            redisClientForToken = new RedisClientForToken<String, Object>(group, cacheClient);
        }
        return this.redisClientForToken;
    }

}
