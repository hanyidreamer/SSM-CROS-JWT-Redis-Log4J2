package com.demo.handler;

import com.demo.redis.core.JedisPoolFactory;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;


/**
 * @author tuzhengsong
 */
public class MyContextListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger("MyContextListener");

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        //注销
        JedisPoolFactory.destroy();

        LogManager.shutdown();
        // 解决Tomcat mysql 驱动内存泄漏，手动注销JDBC
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d;
        while (drivers.hasMoreElements()) {
            try {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
            } catch (SQLException ignored) {
            }
        }
        try {
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}