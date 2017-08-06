package com.ajayhao.simplelab.cl.impl;

import com.ajayhao.simplelab.base.cache.impl.JedisManager;
import com.ajayhao.simplelab.cl.CommonParamCache;
import dto.CommonParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AjayHao on 2017/8/7.
 */

@Resource(name = "commonParamCache")
public class CommonParamCacheImpl implements CommonParamCache{

    @Autowired
    private JedisManager jedisManager;


    @Override
    public boolean isGroupExist(String groupName) {
        return jedisManager.exists(groupName);
    }

    @Override
    public long initData(String groupName, List<CommonParamDTO> paramList) {
        if(!CollectionUtils.isEmpty(paramList)) {
            Map<String,String> map = new HashMap<>();
            for(CommonParamDTO param : paramList){
                if(groupName.equals(param.getParamGroup())) {
                    map.put(param.getParamName(), param.getParamValue());
                }
            }
            return jedisManager.setMap(groupName, map);
        }else{
            return 0;
        }
    }

    @Override
    public String getByNameKey(String groupName, String paramName) {
        return (String)jedisManager.getMap(groupName).get(paramName);
    }
}
