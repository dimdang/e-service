package org.code.jarvis.util;

/**
 * Created by KimChheng on 5/14/2017.
 */
public final class NumberUtil {

    public static int getInteger(Object object, Object defaultValue) {
        try {
            return Integer.valueOf(object.toString());
        } catch (Exception e) {
            return Integer.valueOf(defaultValue.toString());
        }
    }

    public static long getLong(Object object, Object defaultValue) {
        try {
            return Long.valueOf(object.toString());
        } catch (Exception e) {
            return Long.valueOf(defaultValue.toString());
        }
    }

    public static double getDouble(Object object, Object defaultValue) {
        try {
            return Double.valueOf(object.toString());
        } catch (Exception e) {
            return Double.valueOf(defaultValue.toString());
        }
    }

    public static float getFloat(Object object, Object defaultValue) {
        try {
            return Float.valueOf(object.toString());
        } catch (Exception e) {
            return Float.valueOf(defaultValue.toString());
        }
    }
}
