package com.ideax.fm360.dao;

import java.nio.charset.Charset;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import redis.clients.jedis.JedisPool;

/**
 * redis client
 * 
 * @author xuxin03
 * @since 2013年9月30日
 */
public class RedisAccess implements InitializingBean {

    private static Charset DC = Charset.forName("UTF-8");
    private String ip;
    private int port;
    private int minConn;
    private int maxConn;
    

    // simple connection pool
    private JedisPool jredis = null;

    public RedisAccess() {
    }

    public byte[] get(String key) {
        byte[] bkey = key.getBytes(DC);
        return jredis.getResource().get(bkey);
    }

    public void get(byte[] key) {
        jredis.getResource().get(key);
    }

    public boolean set(String key, byte[] el) {
        String status = jredis.getResource().set(key.getBytes(DC), el);
        return status.equals("OK");
    }

    public boolean setEx(String key, byte[] el, int expired) {
        String status = jredis.getResource().setex(key.getBytes(DC), expired, el);
        return status.equals("OK");
    }

    public boolean expire(String key, int seconds) {
        return jredis.getResource().expire(key, seconds) == 1L;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(ip, "jedis ip config is NULL");
        Assert.state(port > 0, "jedis port config is NULL");
        init();
    }

    private void init() {
        Config poolConfig = new Config();
        poolConfig.maxIdle = minConn;
        poolConfig.maxActive = maxConn;
        poolConfig.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_GROW;
        jredis = new JedisPool(poolConfig, ip, port);
    }

    public int getMaxConn() {
        return maxConn;
    }

    public void setMaxConn(int maxConn) {
        this.maxConn = maxConn;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMinConn() {
        return minConn;
    }

    public void setMinConn(int minConn) {
        this.minConn = minConn;
    }

}
