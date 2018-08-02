package com.caxs.cache;

import java.util.Date;
import java.util.Set;

/**
 * Created by kevin on 2016/8/29.
 */
public interface ICacheClient {
    public abstract boolean add(String paramString1, String paramString2, Object paramObject, int paramInt);

    public abstract boolean clear(String paramString);

    public abstract boolean put(String paramString1, String paramString2, Object paramObject);

    public abstract boolean put(String paramString1, String paramString2, Object paramObject, Date paramDate);

    public abstract boolean put(String paramString1, String paramString2, Object paramObject, int paramInt);

    public abstract Object get(String paramString1, String paramString2);

    public abstract boolean remove(String paramString1, String paramString2);

    public abstract int size(String paramString);

    public abstract Set<String> keySet(String paramString);

    public abstract boolean isExist(String paramString1, String paramString2);
}
