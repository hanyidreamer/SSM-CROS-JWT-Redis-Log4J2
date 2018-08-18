package com.demo.redis.core;




import com.demo.redis.config.RedisConfig;
import com.demo.redis.exception.RedisClientException;
import com.demo.redis.util.AssertUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;


/**
 * 基于 jedis 第三方依赖库实现 RedisClient 接口
 *
 * @author tuzhengsong
 */
public class JedisRedisClient implements RedisClient<Jedis> {
    private static final String OK = "OK";

    private static Logger logger = LogManager.getLogger("JedisRedisClient");
    private RedisConfig redisConfig = null;
//    private final Map<String, RedisMsgPubSubListener> redisMsgPubSubListenerMap = new ConcurrentHashMap<>();

    @Override
    public RedisClient<Jedis> setRedisConfig(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
        return this;
    }

    @Override
    public Jedis getJedis() {
        if (null == redisConfig) {
            return JedisPoolFactory.getClient();
        }
        return JedisPoolFactory.getClient(redisConfig);
    }


    @Override
    public boolean setnx(String key, String value) {
        Jedis jedis = getJedis();
        AssertUtil.notNull(jedis, "The Jedis Object is Not allow null .");
        boolean flag = false;
        try {
            long status = jedis.setnx(key, value);
            if (status == 1) {
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            logger.error("Write String Value To Redis Error:" + e);
            throw new RedisClientException(e);
        } finally {
            release(jedis);
        }
    }

    @Override
    public boolean set(String key, String value) {
//        AssertUtil.isTrue(expiredSeconds > 0, "The expiredSeconds is not less then 0 .");
        Jedis jedis = getJedis();
        AssertUtil.notNull(jedis, "The Jedis Object is Not Null .");
        try {
            Transaction ts = jedis.multi();
            ts.set(key, value);
//            ts.expire(key, expiredSeconds);
            ts.exec();
            return true;
        } catch (Exception e) {
            logger.error("Write String Value To Redis Error:" + e);
            throw new RedisClientException(e);
        } finally {
            release(jedis);
        }
    }


    @Override
    public String get(String key) {
        Jedis jedis = getJedis();
        AssertUtil.notNull(jedis, "The Jedis Object is Not Null .");
        String value;
        try {
            value = jedis.get(key);

        } catch (Exception e) {
            logger.warn("Read redis in error:" + e);
            throw new RedisClientException(e);
        } finally {
            release(jedis);
        }
        return value;
    }

    @Override
    public long setnx(String key, String value, int expiredSeconds) {
        AssertUtil.isTrue(expiredSeconds > 0, "The expiredSeconds is not less then 0.");
        Jedis jedis = getJedis();
        AssertUtil.notNull(jedis, "The Jedis Object is Not allow null.");
        try {
            int oldExpireSeconds = Integer.parseInt(jedis.ttl(key).toString());
            Transaction ts = jedis.multi();
            ts.setnx(key, value);
            ts.expire(key, oldExpireSeconds > 0 ? oldExpireSeconds : expiredSeconds);
            // 提交事务并返回 "ts.setnx(key,value)" 的执行结果
            return Long.parseLong(String.valueOf(ts.execGetResponse().get(0).get()));
        } catch (Exception e) {
            logger.error("Execute setnx in Redis Error:", e);
            throw new RedisClientException(e);
        } finally {
            release(jedis);
        }
    }

    @Override
    public Long sadd(String key, String... members) {
        Jedis jedis = getJedis();
        AssertUtil.notNull(jedis, "The Jedis Object is Not Null.");
        long result;
        try {
            // 添加元素，返回添加的元素个数
            result = jedis.sadd(key, members);
        } catch (Exception e) {
            logger.error("The Redis decr {} Error:", key, e);
            throw new RedisClientException(e);
        } finally {
            release(jedis);
        }
        return result;
    }

    @Override
    public Boolean hasKey(String key) {
        Jedis jedis = getJedis();
        AssertUtil.notNull(jedis, "The Jedis Object is Not Null.");
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            logger.error("The Redis decr {} Error:", key, e);
            throw new RedisClientException(e);
        } finally {
            release(jedis);
        }

    }

    @Override
    public Boolean setex(String key, int seconds, String value) {
        Jedis jedis = getJedis();
        AssertUtil.notNull(jedis, "The Jedis Object is Not Null.");
        boolean flag = false;
        try {
            String status = jedis.setex(key, seconds, value);
            if (OK.equals(status)) {
                flag = true;
            }
        } catch (Exception e) {
            logger.error("The Redis decr {} Error:", key, e);
            throw new RedisClientException(e);
        } finally {
            release(jedis);
        }
        return flag;
    }

    @Override
    public Boolean pexpireAt(String key, long unixTime) {
        Jedis jedis = getJedis();
        AssertUtil.notNull(jedis, "The Jedis Object is Not Null.");
        boolean flag = false;
        try {
            long status = jedis.pexpireAt(key, unixTime);

            if (status == 1) {
                flag = true;
            }
        } catch (Exception e) {
            logger.error("The Redis decr {} Error:", key, e);
            throw new RedisClientException(e);
        } finally {
            release(jedis);
        }
        return flag;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = getJedis();
        AssertUtil.notNull(jedis, "The Jedis Object is Not Null.");
        try {
            return jedis.ttl(key);

        } catch (Exception e) {
            logger.error("The Redis decr {} Error:", key, e);
            throw new RedisClientException(e);
        } finally {
            release(jedis);
        }
    }

    @Override
    public Boolean sismember(String key, String member) {
        Jedis jedis = getJedis();
        AssertUtil.notNull(jedis, "The Jedis Object is Not Null.");
        try {
            return jedis.sismember(key, member);

        } catch (Exception e) {
            logger.error("The Redis decr {} Error:", key, e);
            throw new RedisClientException(e);
        } finally {
            release(jedis);
        }
    }

    @Override
    public Long del(String key) {
        Jedis jedis = getJedis();
        AssertUtil.notNull(jedis, "The Jedis Object is Not Null.");
        try {
            return jedis.del(key);
        } catch (Exception e) {
            logger.error("The Redis decr {} Error:", key, e);
            throw new RedisClientException(e);
        } finally {
            release(jedis);
        }
    }

    /**
     * 释放 Jedis 资源
     */
    private void release(Jedis jedis) {
        if (jedis == null) {
            return;
        }
        try {
            jedis.close();
        } catch (Exception e) {
            logger.error("Release jedis Error: ", e);
            throw new RedisClientException(e);
        }
    }
}
