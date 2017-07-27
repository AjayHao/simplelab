package com.ajayhao.simplelab.base.cache;

/**
 * Created by AjayHao on 2017/7/26.
 */
public interface CacheManager {


    long expire(String key, int seconds);

}
