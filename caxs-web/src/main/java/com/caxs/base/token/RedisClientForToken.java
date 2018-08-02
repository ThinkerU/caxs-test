package com.caxs.base.token;

/**
 * Created by kevin on 2016/9/7.
 */


import com.caxs.cache.ICacheClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;


@SuppressWarnings("hiding")
public class RedisClientForToken<String, Object> {
    private static Logger logger = LoggerFactory.getLogger("RedisClientForToken.class");

    private ICacheClient cacheClient;
    private java.lang.String group;

    public RedisClientForToken(java.lang.String group, ICacheClient cacheClient){
        this.group = group;
        this.cacheClient = cacheClient;
    }

    public void setCacheClient(ICacheClient cacheClient) {
        this.cacheClient = cacheClient;
    }

    public void setGroup(java.lang.String group) {
        this.group = group;
    }

    @SuppressWarnings("unchecked")

    public Object get(String key)  {
        return (Object) cacheClient.get(group, key.toString());
    }


    public Object put(String key, Object value)  {
        if(cacheClient.put(group, key.toString(), value)) {
            return value;
        }else {
            return null;
        }
    }

    /**
     *
     * @param key
     * @param value
     * @param ttl <>有效时间 单位为S</>
     * @return
     */
    public Object put(String key, Object value, Integer ttl)  {
        if(cacheClient.put(group, key.toString(), value, ttl)) {
            return value;
        }else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")

    public Object remove(String key)  {
        if(cacheClient.remove(group, key.toString())) {
            return (Object) key;
        }else {
            return null;
        }
    }


    public void clear() {
        cacheClient.clear(group);
    }


    public int size() {
        return cacheClient.size(group);
    }


    public Set<String> keys() {
        try {
            //TODO: unsupported
            return Collections.emptySet();
        } catch (Exception e) {
            logger.error("get values error"+e.toString());
            return null;
        }
    }


    public Collection<Object> values() {
        try {
            //TODO: unsupported
            return Collections.emptyList();

        } catch (Exception e){
            logger.error("get values error"+e.toString());
            return null;
        }
    }
}
