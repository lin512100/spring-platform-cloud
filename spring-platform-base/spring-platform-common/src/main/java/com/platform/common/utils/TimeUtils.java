package com.platform.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Strings;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间工具类
 * @author lin512100
 * @date 2021/6/16
 */
public class TimeUtils {

    public final static String YYYY_MM_DD = "yyyy-MM-dd";

    public final static  String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:SS";

    public final static String YEAR_TO_DATE = "yyyy年M月d日";

    public TimeUtils() {
    }

    /**
     * 时间比较
     * @param time1 时间一
     * @param time2 时间二
     * @return long
     */
    public static long compare(LocalDateTime time1, LocalDateTime time2) {
        Long milliSecond1 = dateTime2Timestamp(time1, null);
        Long milliSecond2 = dateTime2Timestamp(time2, null);
        return (milliSecond1 - milliSecond2) / (1000 * 3600 * 24);
    }

    /**
     * 时间取整
     * @param time 时间
     * @return yyyy-MM-dd
     */
    public static LocalDateTime truncTime(LocalDateTime time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
        String localTime = df.format(time);
        return LocalDateTime.parse(localTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String convertTimeToString(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getCurrentTime() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return df.format(LocalDateTime.now());
    }

    /**
     * 判断日期格式:yyyy-mm-dd
     * @param sDate 日期
     * @return true if pass
     */
    public static boolean isValidDate(String sDate) {
        String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
        String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
            + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
            + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
            + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
            + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
            + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        if ((sDate != null)) {
            Pattern pattern = Pattern.compile(datePattern1);
            Matcher match = pattern.matcher(sDate);
            if (match.matches()) {
                pattern = Pattern.compile(datePattern2);
                match = pattern.matcher(sDate);
                return match.matches();
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 日期字符串转时间戳
     * @param dtf     转换器
     * @param dateStr 日期
     * @param pattern 格式 yyyy-MM-dd yyyy/MM/dd yyyyMMdd等
     * @return 时间戳
     */
    public static Long dateStrToTimestamp(DateTimeFormatter dtf, String dateStr, String pattern) {
        if (Strings.isNullOrEmpty(dateStr)) {
            return null;
        }
        if (dtf == null) {
            dtf = DateTimeFormatter.ofPattern(pattern);
        }
        Long timestamp;
        try {
            timestamp = LocalDate.parse(dateStr, dtf).atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
        } catch (Exception e) {
            timestamp = null;
        }
        return timestamp;
    }

    /**
     * 日期时间字符串转时间戳
     * @param dtf         转换器
     * @param dateTimeStr 日期时间
     * @param pattern     格式 yyyy-MM-dd HH:mm:ss等
     * @return 时间戳
     */
    public static Long dateTimeStrToTimestamp(DateTimeFormatter dtf, String dateTimeStr, String pattern) {
        if (Strings.isNullOrEmpty(dateTimeStr)) {
            return null;
        }
        if (dtf == null) {
            dtf = DateTimeFormatter.ofPattern(pattern);
        }
        Long timestamp;
        try {
            timestamp = LocalDateTime.parse(dateTimeStr, dtf).atZone(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
        } catch (Exception e) {
            timestamp = null;
        }
        return timestamp;
    }

    /**
     * 时间戳转字符串
     * @param dtf       转换器
     * @param timestamp 时间戳
     * @param pattern   格式
     * @return 时间字符串
     */
    public static String timestampToStr(DateTimeFormatter dtf, Long timestamp, String pattern) {
        if (timestamp == null) {
            return "";
        }
        if (dtf == null) {
            dtf = DateTimeFormatter.ofPattern(pattern);
        }
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return dtf.format(localDateTime);
    }

    /**
     * localDateTime 转 时间戳
     * @param localDateTime 时间
     * @param zoneOffset 时区
     * @return 时间戳
     */
    public static Long dateTime2Timestamp(LocalDateTime localDateTime, String zoneOffset) {
        zoneOffset = StringUtils.isBlank(zoneOffset) ? "+8" : zoneOffset;
        return localDateTime.toInstant(ZoneOffset.of(zoneOffset)).toEpochMilli();
    }

    /**
     * 时间戳转LocalDateTime
     * @param timestamp 时间戳
     * @return LocalDateTime 时间
     */
    public static LocalDateTime timestamp2LocalDateTime(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 获取本周起点
     * @return 本周起点
     */
    public static LocalDateTime getThisWeekOrigin() {
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return today.with(DayOfWeek.MONDAY);
    }

    /**
     * 获取当前时间和目标时间之间的间隔天数
     * @param targetTime 目标时间
     * @return 目标时间在当前时间之后返回负数，目标时间在当前时间之前则返回正数，同一天则返回0
     */
    public static long getBetweenDays(LocalDateTime targetTime) {
        LocalDate now = LocalDate.now();
        if (targetTime == null) {
            return now.toEpochDay();
        }
        return now.toEpochDay() - targetTime.toLocalDate().toEpochDay();
    }


    /**
     * LocalDateTime转Date
     * @param localDateTime localDateTime
     * @return date 时间
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        return Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
    }

}
