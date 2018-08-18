package com.demo.redis.core;


import com.demo.redis.config.RedisConfig;

/**
 * Redis 操作类接口定义
 *
 * @param <T> the type parameter
 * @author tuzhengsong
 */
public interface RedisClient<T> {

    /**
     * Sets redis config.
     *
     * @param var1 the var 1
     * @return redis config
     */
    RedisClient<?> setRedisConfig(RedisConfig var1);

    /**
     * Gets jedis.
     *
     * @return the jedis
     */
    T getJedis();

    /**
     * Sets .
     *
     * @param var1 the var 1
     * @param var2 the var 2
     * @return the
     */
    boolean setnx(String var1, String var2);

    /**
     * Set boolean.
     *
     * @param var1 the var 1
     * @param var2 the var 2
     * @param var3 the var 3
     * @return the boolean
     */
    boolean set(String var1, String var2);

    /**
     * Get string.
     *
     * @param var1 the var 1
     * @return the string
     */
    String get(String var1);



    /**
     * Sets .
     *
     * @param var1 the var 1
     * @param var2 the var 2
     * @param var3 the var 3
     * @return the
     */
    long setnx(String var1, String var2, int var3);

    /**
     * Sadd long.
     *
     * @param var1 the var 1
     * @param var2 the var 2
     * @return the long
     */
    Long sadd(String var1, String... var2);

    /**
     * Has key boolean.
     *
     * @param var1 the var 1
     * @return the boolean
     */
    Boolean hasKey(String var1);

    /**
     * Sets .
     *
     * @param var1 the var 1
     * @param var2 the var 2
     * @param var3 the var 3
     * @return the
     */
    Boolean setex(String var1, int var2, String var3);

    /**
     * Pexpire at boolean.
     *
     * @param var1 the var 1
     * @param var2 the var 2
     * @return the boolean
     */
    Boolean pexpireAt(String var1, long var2);

    /**
     * Ttl long.
     *
     * @param var1 the var 1
     * @return the long
     */
    Long ttl(String var1);

    /**
     * Sismember boolean.
     *
     * @param var1 the var 1
     * @param var2 the var 2
     * @return the boolean
     */
    Boolean sismember(String var1, String var2);

    /**
     * 根据key删除
     * @param var1 key
     * @return the long
     */
    Long del(String var1);
}