package com.platform.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 转换工具类
 * @author lin512100
 * @date 6/14/2021
 */
public class ConvertUtils {

    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");

    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        Matcher matcher = LINE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     * @param str 驼峰字段
     * @return 下划线字段
     */
    public static String humpToLine(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String convertString(Object str, String defaults) {
        return str == null ? defaults : String.valueOf(str);
    }

    public static int convertInt(Object str, int defaults) {
        if (str == null) {
            return defaults;
        } else {
            try {
                return Integer.parseInt(String.valueOf(str));
            } catch (Exception var3) {
                return defaults;
            }
        }
    }

    public static long convertLong(String str, long defaults) {
        if (str == null) {
            return defaults;
        } else {
            try {
                return Long.parseLong(str);
            } catch (Exception var4) {
                return defaults;
            }
        }
    }

    public static double convertDouble(String str, double defaults) {
        if (str == null) {
            return defaults;
        } else {
            try {
                return Double.parseDouble(str);
            } catch (Exception var4) {
                return defaults;
            }
        }
    }

    public static short convertShort(String str, short defaults) {
        if (str == null) {
            return defaults;
        } else {
            try {
                return Short.parseShort(str);
            } catch (Exception var3) {
                return defaults;
            }
        }
    }

    public static byte convertByte(String str, byte defaults) {
        if (str == null) {
            return defaults;
        } else {
            try {
                return Byte.parseByte(str);
            } catch (Exception var3) {
                return defaults;
            }
        }
    }

    public static float convertFloat(String str, float defaults) {
        if (str == null) {
            return defaults;
        } else {
            try {
                return Float.parseFloat(str);
            } catch (Exception var3) {
                return defaults;
            }
        }
    }

    public static boolean convertBoolean(String str, boolean defaults) {
        if (str == null) {
            return defaults;
        } else {
            try {
                return Boolean.parseBoolean(str);
            } catch (Exception var3) {
                return defaults;
            }
        }
    }

}
