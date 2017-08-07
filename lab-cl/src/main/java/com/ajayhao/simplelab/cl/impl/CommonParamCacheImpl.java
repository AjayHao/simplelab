package com.ajayhao.simplelab.cl.impl;

import com.ajayhao.simplelab.base.cache.redis.JedisManager;
import com.ajayhao.simplelab.cl.CommonParamCache;
import com.ajayhao.simplelab.facade.dto.CommonParamDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by AjayHao on 2017/8/7.
 */

@Resource(name = "commonParamCache")
public class CommonParamCacheImpl implements CommonParamCache{

    Logger logger = LoggerFactory.getLogger(CommonParamCacheImpl.class);

    @Value("${cache.localFailOver}")
    private boolean localFailOver;

    @Autowired
    private JedisManager jedisManager;


    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private ConcurrentMap<String, Map<String,String>> localMap = new ConcurrentHashMap<>();

    @Override
    public boolean isGroupExist(String groupName) {
        boolean retVal;
        lock.readLock().lock();
        try{
            retVal = jedisManager.exists(groupName);
        }catch(Exception e){
            logger.warn("系统异常", e);
            if(localFailOver){
                logger.info("===切换到本地缓存");
                Set<String> keySet = localMap.keySet();
                retVal = !CollectionUtils.isEmpty(keySet) && keySet.contains(groupName);
            }else{
                throw e;
            }
        }finally {
            lock.readLock().unlock();
        }

        return retVal;
    }

    @Override
    public long initData(String groupName, List<CommonParamDTO> paramList) {
        long result = 0;
        if(!CollectionUtils.isEmpty(paramList)) {
            Map<String,String> map = new HashMap<>();
            for(CommonParamDTO param : paramList){
                if(groupName.equals(param.getParamGroup())) {
                    map.put(param.getParamName(), param.getParamValue());
                }
            }
            try {
                result = jedisManager.setMap(groupName, map);
            }catch(Exception e){
                logger.warn("系统异常", e);
                if(localFailOver){
                    logger.info("===切换到本地缓存");
                    lock.writeLock().lock();
                    try{
                        Map<String, String> innerMap = localMap.get(groupName);
                        if(CollectionUtils.isEmpty(innerMap)){
                            innerMap = new ConcurrentHashMap<>();
                        }
                        innerMap.putAll(map);
                        localMap.put(groupName, innerMap);
                    }finally{
                        lock.writeLock().unlock();
                    }
                }else{
                    throw e;
                }
            }
        }
        return result;
    }

    @Override
    public String getByNameKey(String groupName, String paramName) {
        lock.readLock().lock();
        String retStr = null;
        Map<String, String> map = null;
        try {
            map = jedisManager.getMap(groupName);
            retStr = CollectionUtils.isEmpty(map) ? null : map.get(paramName);
        }catch(Exception e){
            logger.warn("系统异常", e);
            if(localFailOver) {
                logger.info("===切换到本地缓存");
                map = localMap.get(groupName);
                retStr = CollectionUtils.isEmpty(map) ? null : map.get(paramName);
            }
        }finally {
            lock.readLock().unlock();
        }

        return retStr;
    }
}
