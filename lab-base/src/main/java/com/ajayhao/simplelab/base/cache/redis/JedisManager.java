package com.ajayhao.simplelab.base.cache.redis;

import com.ajayhao.simplelab.base.cache.CacheManager;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存管理， Jedis实现
 * <p>
 * <p>
 * Created by AjayHao on 2017/8/6.
 */
public class JedisManager implements CacheManager {

    private JedisPoolConfig jedisPoolConfig;
    private List<JedisShardInfo> jedisServerList;
    private ShardedJedisPool jedisPool;
    private final ReadWriteLock wrLock = new ReentrantReadWriteLock();
    private boolean usable = false;

    public JedisManager() {}

    private void initialize() {
        notEmpty();
        if (this.jedisPool == null) {
            wrLock.writeLock().lock();
            try {
                if (this.jedisPool == null) {
                    jedisPool = new ShardedJedisPool(jedisPoolConfig, jedisServerList);
                    checkUsableStatus();
                }
            } finally {
                wrLock.writeLock().unlock();
            }
        }
    }

    public void shutdown() {
        if (this.jedisPool != null && !this.jedisPool.isClosed()) {
            wrLock.writeLock().lock();

            try {
                if (this.jedisPool != null && !this.jedisPool.isClosed()) {
                    this.jedisPool.close();
                }
            } finally {
                wrLock.writeLock().unlock();
            }
        }
    }

    /**
     * 从jedis连接池中获取jedis对象
     *
     * @return
     */
    private ShardedJedis getJedis() {
        if (jedisPool != null && jedisPool.isClosed()) {
            return null;
        }

        return jedisPool.getResource();
    }

    /**
     * 尝试从jedis连接池中获取jedis对象
     *
     * @return
     */
    private void checkUsableStatus() {
        if (jedisPool != null && jedisPool.isClosed()) {
            this.usable = false;
        }
        try {
            jedisPool.getResource();
            this.usable = true;
        }catch(Exception e){
            this.usable = false;
        }
    }

    /**
     * 回收jedis
     *
     * @param jedis
     */
    private void returnJedis(ShardedJedis jedis) {
        /*if (jedisPool != null && !jedisPool.isClosed()) {
            jedisPool.returnResource(jedis);
        }*/
        jedis.close();
    }

    public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        this.jedisPoolConfig = jedisPoolConfig;
    }

    public void setJedisServerList(List<JedisShardInfo> jedisServerList) {
        this.jedisServerList = jedisServerList;
    }

    @Override
    public long expire(String key, int seconds) {
        if (seconds <= 0) {
            return 0;
        }

        ShardedJedis jedis = getJedis();
        if (jedis != null) {
            try {
                long nums = jedis.expire(key, seconds);
                return nums;
            } finally {
                returnJedis(jedis);
            }
        }

        return 0;
    }

    @Override
    public long expire(String key) {
        return expire(key, 0);
    }

    @Override
    public long expireAt(String key, long timestamp) {
        ShardedJedis jedis = getJedis();
        if (jedis != null) {
            try {
                long nums = jedis.expireAt(key, timestamp);
                return nums;
            } finally {
                returnJedis(jedis);
            }
        } else {
            return 0;
        }
    }

    @Override
    public long persist(String key) {
        ShardedJedis jedis = getJedis();
        if (jedis != null) {
            try {
                long nums = jedis.persist(key);
                return nums;
            } finally {
                returnJedis(jedis);
            }
        } else {
            return 0;
        }
    }

    @Override
    public long delete(String key) {
        ShardedJedis jedis = getJedis();
        if (jedis != null) {
            try {
                long count = jedis.del(key);
                return count;
            } finally {
                returnJedis(jedis);
            }
        } else {
            return 0;
        }
    }

    @Override
    public boolean exists(String key) {
        ShardedJedis jedis = getJedis();
        if (jedis != null) {
            try {
                boolean isExist = jedis.exists(key);
                return isExist;
            } finally {
                returnJedis(jedis);
            }
        } else {
            return false;
        }
    }

    @Override
    public String getString(String key) {
        ShardedJedis jedis = getJedis();
        if (jedis != null) {
            wrLock.readLock().lock();
            try {
                String str = jedis.get(key);
                return str;
            } finally {
                wrLock.readLock().unlock();
                returnJedis(jedis);
            }
        } else {
            return null;
        }
    }

    @Override
    public long setString(String key, String value) {
        ShardedJedis jedis = getJedis();
        if (jedis != null) {
            wrLock.writeLock().lock();
            try {
                String status = jedis.set(key, value);
                return "OK".equals(status) ? 1 : 0;
            } finally {
                wrLock.writeLock().unlock();
                returnJedis(jedis);
            }
        } else {
            return 0;
        }
    }

    @Override
    public long setString(String key, int seconds, String value) {
        ShardedJedis jedis = getJedis();
        if (jedis != null) {
            wrLock.writeLock().lock();
            try {
                String status = jedis.setex(key, seconds, value);
                return "OK".equals(status) ? 1 : 0;
            } finally {
                wrLock.writeLock().unlock();
                returnJedis(jedis);
            }
        } else {
            return 0;
        }
    }

    @Override
    public Object getObject(String key) {
        return null;
    }

    @Override
    public long setObject(String key, Object object) {
        return 0;
    }

    @Override
    public long setObject(String key, int seconds, Object object) {
        return 0;
    }

    @Override
    public List getList(String key) {
        return null;
    }

    @Override
    public long setList(String key, List list) {
        return 0;
    }

    @Override
    public long setList(String key, int seconds, List list) {
        return 0;
    }

    @Override
    public Set getSet(String key) {
        return null;
    }

    @Override
    public long setSet(String key, Set set) {
        return 0;
    }

    @Override
    public long setSet(String key, int seconds, Set set) {
        return 0;
    }

    @Override
    public Map getMap(String key) {
        ShardedJedis jedis = getJedis();
        if (jedis != null) {
            wrLock.readLock().lock();
            try {
                Map map = jedis.hgetAll(key);
                return map;
            } finally {
                wrLock.readLock().unlock();
                returnJedis(jedis);
            }
        } else {
            return null;
        }
    }

    @Override
    public List getMapValList(String key) {
        ShardedJedis jedis = getJedis();
        if (jedis != null) {
            wrLock.readLock().lock();
            try {
                List list = jedis.hvals(key);
                return list;
            } finally {
                wrLock.readLock().unlock();
                returnJedis(jedis);
            }
        } else {
            return null;
        }
    }

    @Override
    public long setMap(String key, Map map) {
        ShardedJedis jedis = getJedis();
        if (jedis != null) {
            wrLock.writeLock().lock();
            try {
                String status = jedis.hmset(key, map);
                return "OK".equals(status) ? 1 : 0;
            } finally {
                wrLock.writeLock().unlock();
                returnJedis(jedis);
            }
        } else {
            return 0;
        }
    }

    @Override
    public long setMap(String key, int seconds, Map map) {
        ShardedJedis jedis = getJedis();
        if (jedis != null) {
            wrLock.writeLock().lock();
            try {
                jedis.hmset(key, map);
                return jedis.expire(key, seconds);
            } finally {
                wrLock.writeLock().unlock();
                returnJedis(jedis);
            }
        } else {
            return 0;
        }
    }

    private void notEmpty() {
        if (this.jedisPoolConfig == null) {
            throw new IllegalArgumentException("jedis配合信息不能为空");
        }

        if (this.jedisServerList == null || this.jedisServerList.isEmpty()) {
            throw new IllegalArgumentException("jedis服务器列表信息不能为空");
        }
    }

    @Override
    public boolean isUsable() {
        return usable;
    }
}
