package vn.xteam.savemoneyapi.common.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class XEnv {
    public static Dotenv dotenv = Dotenv.configure().load();
    public static String getEnvironmentEntry(String key) {
        return dotenv.get(key);
    }
}
