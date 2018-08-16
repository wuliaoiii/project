package com.yangy.generator.config;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author yangy
 * @email java_yangy@126.com
 * @create 2018/6/13
 * @since 1.0.0
 */
public class GenerateConfig {

    private static String AUTHOR;
    private static String EMAIL;

    private static String DATE;
    private static String DISKPATH;
    private static String RESOURCEPATH;
    private static String PACKAGENAME;
    private static String BASEPACKAGENAME;

    private static String DRIVER;
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    private static final ResourceBundle RESOURCE = ResourceBundle.getBundle("generateModel", Locale.getDefault());

    public static String getProperty(String key) {
        try {
            return new String(RESOURCE.getString(key).getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    public static String getAUTHOR() {
        if (StringUtils.isNotBlank(AUTHOR)) {
            return AUTHOR;
        }
        AUTHOR = getProperty("AUTHOR");
        return AUTHOR;
    }

    public static String getEMAIL() {
        if (StringUtils.isNotBlank(EMAIL)) {
            return EMAIL;
        }
        EMAIL = getProperty("EMAIL");
        return EMAIL;
    }

    public static String getDATE() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd");
        String format = simpleDateFormat.format(new Date());
        return format;
    }

    public static String getDRIVER() {
        if (StringUtils.isNotBlank(DRIVER)) {
            return DRIVER;
        }
        DRIVER = getProperty("DRIVER");
        return DRIVER;
    }

    public static String getDISKPATH() {
        if (StringUtils.isNotBlank(DISKPATH)) {
            return DISKPATH;
        }
        DISKPATH = getProperty("DISKPATH");
        return DISKPATH;
    }

    public static String getRESOURCEPATH() {
        if (StringUtils.isNotBlank(RESOURCEPATH)) {
            return RESOURCEPATH;
        }
        RESOURCEPATH = getProperty("RESOURCEPATH");
        return RESOURCEPATH;
    }

    public static String getPACKAGENAME() {
        if (StringUtils.isNotBlank(PACKAGENAME)) {
            return PACKAGENAME;
        }
        PACKAGENAME = getProperty("PACKAGENAME");
        return PACKAGENAME;
    }

    public static String getBASEPACKAGENAME() {
        if (StringUtils.isNotBlank(BASEPACKAGENAME)) {
            return BASEPACKAGENAME;
        }
        BASEPACKAGENAME = getProperty("BASEPACKAGENAME");
        return BASEPACKAGENAME;
    }

    public static String getURL() {
        if (StringUtils.isNotBlank(URL)) {
            return URL;
        }
        URL = getProperty("URL");
        return URL;
    }

    public static String getUSER() {
        if (StringUtils.isNotBlank(USER)) {
            return USER;
        }
        USER = getProperty("USER");
        return USER;
    }

    public static String getPASSWORD() {
        if (StringUtils.isNotBlank(PASSWORD)) {
            return PASSWORD;
        }
        PASSWORD = getProperty("PASSWORD");
        return PASSWORD;
    }
}