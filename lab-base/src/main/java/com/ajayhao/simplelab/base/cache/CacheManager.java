package com.ajayhao.simplelab.base.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 缓存管理器
 *
 * Created by AjayHao on 2017/7/26.
 */
public interface CacheManager {
    /**
     * 判断是否可用状态
     *
     * @return 是否可用
     */
    boolean isUsable();

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
     * 设置key的过期时间,它是距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00，格里高利历）的偏移量。
     *
     * @param key 键
     * @param timestamp 时间单位：秒
     * @return 影响的记录数
     */
    long expireAt(String key, long timestamp);

    /**
     * 取消对key过期时间的设置
     *
     * @param key
     * @return 影响的记录数
     * */
    long persist(String key);

    /**
     * 删除key对应的记录
     *
     * @param key
     * @return 删除的记录数
     * */
    long delete(String key);

    /**
     * 判断key是否存在
     * @param  key
     * @return boolean
     * */
    boolean exists(String key);

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
     * @param seconds 秒数
     * @param value 字符串值
     * @return 影响条数
     */
    long setString(String key, int seconds, String value);

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
     * @param seconds 秒数
     * @param object 对象
     * @return 影响条数
     */
    long setObject(String key, int seconds, Object object);

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
     * @param seconds 秒数
     * @param list 列表
     * @return 影响条数
     */
    long setList(String key, int seconds, List list);

    /**
     * 取集合
     *
     * @param key 键
     * @return 集合
     */
    Set getSet(String key);


    /**
     * 设置集合
     *
     * @param key 键
     * @param set 集合
     * @return 影响条数
     */
    long setSet(String key, Set set);

    /**
     * 设置集合
     *
     * @param key 键
     * @param seconds 秒数
     * @param set 集合
     * @return 影响条数
     */
    long setSet(String key, int seconds, Set set);

    /**
     * 取散列
     *
     * @param key 键
     * @return 散列
     */
    Map getMap(String key);


    List getMapValList(String key);

    /**
     * 设置散列
     *
     * @param key 键
     * @param map 散列
     * @return 影响条数
     */
    long setMap(String key, Map map);

    /**
     * 设置散列
     *
     * @param key 键
     * @param seconds 秒数
     * @param map 散列
     * @return 影响条数
     */
    long setMap(String key,  int seconds, Map map);

}
