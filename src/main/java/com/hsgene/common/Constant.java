package com.hsgene.common;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hjc on 2016/10/21.
 */
public class Constant {

    protected static Logger logger = LoggerFactory.getLogger(Constant.class);

    public static final String ACCESS_KEY = getValue("access-key");
    public static final String SECRET_KEY = getValue("secret-key");
    public static final String BUCKET_NAME = getValue("bucket_name");

    //  推荐两种读取properties文件方法：java.lang.ClassLoader的getSystemResourceAsStream和Spring的ResourceLoader resourceLoader = new DefaultResourceLoader()

    private static Properties properties = new Properties();

    /**
     * 将Properties配置文件读取到Properties对象中
     */
    static {
        InputStream is = null;
        try {
            is = ClassLoader.getSystemResourceAsStream("app.properties");
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("配置加载失败！");
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}
