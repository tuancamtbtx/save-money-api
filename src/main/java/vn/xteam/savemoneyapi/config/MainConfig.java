package vn.xteam.savemoneyapi.config;

import vn.xteam.savemoneyapi.common.utils.XEnv;

public class MainConfig {
    public final static String MYSQL_URI = XEnv.getEnvironmentEntry("MYSQL_URL");
    public final static String MYSQL_USERNAME = XEnv.getEnvironmentEntry("MYSQL_USERNAME");
    public final static String MYSQL_PASSWORD = XEnv.getEnvironmentEntry("MYSQL_PASSWORD");
}
