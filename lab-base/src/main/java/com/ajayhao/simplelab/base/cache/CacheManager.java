package com.ajayhao.simplelab.base.cache;

import java.util.List;
import java.util.Set;

/**
 * 缓存管理器
 *
 * Created by AjayHao on 2017/7/26.
 */
public interface CacheManager {

    /**
     * 设置过期时间，seconds秒后超时
     *
     * @param key     the key
     * @param seconds 超时秒数
     * @return 影响条数
     */
    long expire(String key, int seconds);

    /**
     * 设置为过期
     *
     * @param key  键
     * @return 影响条数
     */
    long expire(String key);

    /**
     * 取字符串
     *
     * @param key 键
     * @return value
     */
    String getString(String key);

    /**
     * 设置字符串
     *
     * @param key 键
     * @param value 字符串值
     * @return 影响条数
     */
    long setString(String key, String value);


    /**
     * 设置字符串
     *
     * @param key 键
     * @param value 字符串值
     * @param seconds 秒数
     * @return 影响条数
     */
    long setString(String key, String value, long seconds);

    /**
     * 取对象
     *
     * @param key 键
     * @return 对象
     */
    Object getObject(String key);

    /**
     * 设置对象
     *
     * @param key 键
     * @param object 对象
     * @return 影响条数
     */
    long setObject(String key, Object object);

    /**
     * 设置对象
     *
     * @param key 键
     * @param object 对象
     * @param seconds 秒数
     * @return 影响条数
     */
    long setObject(String key, Object object, long seconds);

    /**
     * 取列表
     *
     * @param key 键
     * @return 列表
     */
    List getList(String key);

    /**
     * 设置列表
     *
     * @param key 键
     * @param list 列表
     * @return 影响条数
     */
    long setList(String key, List list);

    /**
     * 设置列表
     *
     * @param key 键
     * @param list 列表
     * @param seconds 秒数
     * @return 影响条数
     */
    long setList(String key, List list, long seconds);

    /**
     * 取集合
     *
     * @param key 键
     * @return 集合
     */
    Set getSet(String key);


    /**
     * 设置列表
     *
     * @param key 键
     * @param set 集合
     * @return 影响条数
     */
    long setSet(String key, Set set);

    /**
     * 设置列表
     *
     * @param key 键
     * @param set 集合
     * @param seconds 秒数
     * @return 影响条数
     */
    long setSet(String key, Set set, long seconds);

}
