package vn.xteam.savemoneyapi.common.utils;

import java.util.concurrent.ThreadLocalRandom;

public class XString {
    public static String getCodeSaving(Long customerId, int type) {
        String typeName = "";
        if (type == 1) {
            typeName = "KO_KH";
        } else if (type == 2) {
            typeName = "3_TH";
        } else if (type == 3) {
            typeName = "6_TH";
        }
        int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
        return String.format("%s_%s_%s", customerId, typeName,randomNum);
    }
    public static void main(String[] args){
    }
}
