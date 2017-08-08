package com.ajayhao.simplelab.cl.impl;

import com.ajayhao.simplelab.base.cache.CacheManager;
import com.ajayhao.simplelab.cl.CommonParamCache;
import com.ajayhao.simplelab.facade.dto.CommonParamDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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

@Component("commonParamCache")
public class CommonParamCacheImpl implements CommonParamCache {

    Logger logger = LoggerFactory.getLogger(CommonParamCacheImpl.class);

    @Value("${cache.localFailOver}")
    private boolean localFailOver;

    @Autowired
    private CacheManager jedisManager;


    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private ConcurrentMap<String, Map<String,String>> localMap = new ConcurrentHashMap<>();

    @Override
    public boolean isGroupExist(String groupName) {
        boolean retVal = false;
        lock.readLock().lock();

        try{
            if(jedisManager.isUsable()){
                retVal = jedisManager.exists(groupName);
            }else if(localFailOver){
                retVal = isGroupExistLocal(groupName);
            }
        }catch(Exception e){
            logger.warn("访问远程缓存失败", e);
        }finally {
            lock.readLock().unlock();
        }

        return retVal;
    }

    private boolean isGroupExistLocal(String groupName) {
        Set<String> keySet = localMap.keySet();
        return !CollectionUtils.isEmpty(keySet) && keySet.contains(groupName);
    }

    @Override
    public long initData(String groupName, List<CommonParamDTO> paramList) {
        long result = 0;
        if(!CollectionUtils.isEmpty(paramList)) {
            Map<String,String> map = new HashMap<>();
            for(CommonParamDTO param : paramList){
                if(groupName.equals(param.getParamGroup())) {
                    map.put(param.getParamCode(), param.getParamValue());
                }
            }
            try {
                if(jedisManager.isUsable()) {
                    result = jedisManager.setMap(groupName, map);
                }else if(localFailOver){
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
                }
            }catch(Exception e){
                logger.warn("访问远程缓存失败", e);
            }
        }
        return result;
    }

    @Override
    public String getByNameKey(String groupName, String paramCode) {
        lock.readLock().lock();
        String retStr = null;
        Map<String, String> map = null;
        try {
            if(jedisManager.isUsable()) {
                map = jedisManager.getMap(groupName);
            }else if(localFailOver) {
                logger.info("===切换到本地缓存");
                map = localMap.get(groupName);
            }

            retStr = CollectionUtils.isEmpty(map) ? null : map.get(paramCode);
        }catch(Exception e){
            logger.warn("访问远程缓存失败", e);
        }finally {
            lock.readLock().unlock();
        }

        return retStr;
    }
}
