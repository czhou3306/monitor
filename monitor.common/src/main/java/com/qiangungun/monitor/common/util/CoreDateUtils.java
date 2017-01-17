package com.qiangungun.monitor.common.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by chengkaijie on 2017/1/14.
 */
public abstract class CoreDateUtils {

    /**
     * 将日期格式化为：年4位、月2位（01 ~ 12）、日2位（01 ~ 31）<br/>
     * <p/>
     * <pre>
     *     2015-1-22 15:47:20 结果为： 20150122
     * </pre>
     */
    public static final String yyyymmdd = "yyyyMMdd";

    /**
     * 将日期格式化为：年4位、月2位（01 ~ 12）、日2位（01 ~ 31）<br/>
     * <p/>
     * <pre>
     *     2015-1-22 15:47:20 结果为： 2015年01月22日
     * </pre>
     */
    public static final String yyyymmdd_chinese = "yyyy年MM月dd日";

    /**
     * 将日期格式化为：小时2位（00 ~ 23）、分2位（00 ~ 59）、秒2位（00 ~ 59）<br/>
     * <p/>
     * <pre>
     *     2015-1-22 15:47:20 结果为： 154720
     * </pre>
     */
    public static final String hh24miss = "HHmmss";

    /**
     * 将日期格式化为：小时2位（00 ~ 23）、分2位（00 ~ 59）、秒2位（00 ~ 59）<br/>
     * <p/>
     * <pre>
     *     2015-1-22 15:47:20 结果为： 15时47分20秒
     * </pre>
     */
    public static final String hh24miss_chinese = "HH时mm分ss秒";

    /**
     * 将日期格式化为：小时2位（00 ~ 11）、分2位（00 ~ 59）、秒2位（00 ~ 59）<br/>
     * <p/>
     * <pre>
     *     2015-1-22 15:47:20 结果为： 034720
     * </pre>
     */
    public static final String hh12miss = "hhmmss";

    /**
     * 将日期格式化为：小时2位（00 ~ 11）、分2位（00 ~ 59）、秒2位（00 ~ 59）<br/>
     * <p/>
     * <pre>
     *     2015-1-22 15:47:20 结果为： 03时47分20秒
     * </pre>
     */
    public static final String hh12miss_chinese = "hh时mm分ss秒";

    /**
     * 将日期格式化为：年4位、月2位（01 ~ 12）、日2位（01 ~ 31）、小时2位（00 ~ 23）、分2位（00 ~ 59）、秒2位（00 ~ 59）<br/>
     * <p/>
     * <pre>
     *     2015-1-22 15:47:20 结果为： 20150122154720
     * </pre>
     */
    public static final String yyyymmddhh24miss = "yyyyMMddHHmmss";

    /**
     * 将日期格式化为：年4位、月2位（01 ~ 12）、日2位（01 ~ 31）、小时2位（00 ~ 23）、分2位（00 ~ 59）、秒2位（00 ~ 59）<br/>
     * <p/>
     * <pre>
     *     2015-1-22 15:47:20 结果为： 2015年01月22日 15时47分20秒
     * </pre>
     */
    public static final String yyyymmddhh24miss_chinese = "yyyy年MM月dd日HH时mm分ss秒";

    /**
     * 将日期格式化为：年4位、月2位（01 ~ 12）、日2位（01 ~ 31）、小时2位（00 ~ 11）、分2位（00 ~ 59）、秒2位（00 ~ 59）<br/>
     * <p/>
     * <pre>
     *     2015-1-22 15:47:20 结果为： 034720
     * </pre>
     */
    public static final String yyyymmddhh12miss = "yyyyMMddhhmmss";

    /**
     * 将日期格式化为：年4位、月2位（01 ~ 12）、日2位（01 ~ 31）、小时2位（00 ~ 11）、分2位（00 ~ 59）、秒2位（00 ~ 59）<br/>
     * <p/>
     * <pre>
     *     2015-1-22 15:47:20 结果为： 2015年01月22日 03时47分20秒
     * </pre>
     */
    public static final String yyyymmddhh12miss_chinese = "yyyy年MM月dd日 hh时mm分ss秒";

    /**
     * 将日期格式化为：年4位、月2位（01 ~ 12）、日2位（01 ~ 31）、小时2位（00 ~ 23）、分2位（00 ~ 59）、秒2位（00 ~ 59）<br/>
     * <p/>
     * <pre>
     *     2015-1-22 15:47:20 结果为： 034720
     * </pre>
     */
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将日期格式化为：年-月-日<br/>
     * <p/>
     * <pre>
     *     2015-1-22 结果为： 2015-1-22
     * </pre>
     */
    public static final String STANDARD_YYYYMMDD_FORMAT = "yyyy-MM-dd";


    private CoreDateUtils() {
        ; // nothing.
    }

    /**
     * 将日期格式化为：年4位、月2位（01 ~ 12）、日2位（01 ~ 31）<br/>
     *
     * @param date
     * @return
     */
    public static String yyyymmdd(Date date) {
        return format(date, yyyymmdd);
    }

    /**
     * 将日期格式化为：年4位、月2位（01 ~ 12）、日2位（01 ~ 31）<br/>
     *
     * @param date
     * @return
     */
    public static String yyyymmdd4chinese(Date date) {
        return format(date, yyyymmdd_chinese);
    }

    public static Date yyyymmdd(String strDate) {
        return parseToDate(strDate, yyyymmdd);
    }

    public static Date yyyymmdd4chinese(String strDate) {
        return parseToDate(strDate, yyyymmdd_chinese);
    }

    public static String hh24miss(Date date) {
        return format(date, hh24miss);
    }

    public static String hh24miss4chinese(Date date) {
        return format(date, hh24miss_chinese);
    }

    public static String hh12miss(Date date) {
        return format(date, hh12miss);
    }

    public static String hh12miss4chinese(Date date) {
        return format(date, hh12miss_chinese);
    }

    public static String yyyymmddhh24miss(Date date) {
        return format(date, yyyymmddhh24miss);
    }

    public static String yyyymmddhh24miss4chinese(Date date) {
        return format(date, yyyymmddhh24miss_chinese);
    }

    public static Date yyyymmddhh24miss(String strDate) {
        return parseToDate(strDate, yyyymmddhh24miss);
    }

    public static Date yyyymmddhh24miss4chinese(String strDate) {
        return parseToDate(strDate, yyyymmddhh24miss_chinese);
    }

    public static String yyyymmddhh12miss(Date date) {
        return format(date, yyyymmddhh12miss);
    }

    public static String yyyymmddhh12miss4chinese(Date date) {
        return format(date, yyyymmddhh12miss_chinese);
    }

    public static Date yyyymmddhh12miss(String strDate) {
        return parseToDate(strDate, yyyymmddhh12miss);
    }

    public static Date yyyymmddhh12miss4chinese(String strDate) {
        return parseToDate(strDate, yyyymmddhh12miss_chinese);
    }

    /**
     * 指定格式的日期转换
     *
     * @param strDate
     * @param format
     * @return
     */
    public static Date parseToDate(String strDate, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (Exception e) {
            //转换失败就返回NULL
        }
        return date;
    }

    /**
     * 自定义日期格式化<br/>
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            String result = dateFormat.format(date);

            return result;
        } catch (Exception e) {
            // ignore
        }

        return null;
    }

    /**
     * 是否是星期一<br/>
     *
     * @param date
     * @return
     */
    public static boolean isMonday(Date date) {
        return dayIn(date, new int[]{Calendar.MONDAY});
    }

    /**
     * 是否是星期六<br/>
     *
     * @param date
     * @return
     */
    public static boolean isSaturday(Date date) {
        return dayIn(date, new int[]{Calendar.SATURDAY});
    }

    /**
     * 是否是星期天<br/>
     *
     * @param date
     * @return
     */
    public static boolean isSunday(Date date) {
        return dayIn(date, new int[]{Calendar.SUNDAY});
    }

    /**
     * 是否是周末<br/>
     *
     * @param date
     * @return
     */
    public static boolean isWeekend(Date date) {
        return dayIn(date, new int[]{Calendar.SUNDAY, Calendar.SATURDAY});
    }

    private static boolean dayIn(Date date, int[] expects) {
        Calendar c = getCalendar(date);

        // 获取一周数据；
        final int curr = c.get(Calendar.DAY_OF_WEEK);

        return ArrayUtils.contains(expects, curr);
    }

    /**
     * 一年的总天数<br/>
     *
     * @param date
     * @return
     */
    public static int daysOfYear(Date date) {
        Calendar curr = getCalendar(date);

        return curr.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    /**
     * 计算日期在当年的第N天，从1开始<br/>
     *
     * @param date
     * @return
     */
    public static int daysInYear(Date date) {
        Calendar curr = getCalendar(date);

        return curr.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 计算日期在一周中的第几天，星期一为第一天，周六为第六天，周末为第七天<br/>
     *
     * @param date
     * @return
     */
    public static int daysInWeek(Date date) {
        Calendar c = getCalendar(date);

        // 获取一周数据；
        final int curr = c.get(Calendar.DAY_OF_WEEK) - 1;
        return Math.max(curr % 7, (curr + 7) % 8);
    }

    /**
     * 从指定的时间，增加N个月，然后停留到当月的M天<br/>
     * NOTE：如果停留的当月没有M天，则停留在当月的月末<br/>
     *
     * @param date
     * @param months
     * @param index  当月的天数，从1开始
     * @return
     */
    public static Date shiftMonth(Date date, int months, int index) {
        final Calendar c = getCalendar(date);
        final int m = c.get(Calendar.MONTH);
        final int y = c.get(Calendar.YEAR);
        c.clear();
        c.set(y, m, 1); // 当前月的第一天；
        c.add(Calendar.MONTH, months); // 增加N个月；

        // 移动到当月正确的天数；
        final int days = c.getActualMaximum(Calendar.DATE);
        if (index >= days) {
            c.add(Calendar.DAY_OF_MONTH, days - 1);
        } else {
            c.add(Calendar.DAY_OF_MONTH, index - 1);
        }

        return c.getTime();
    }

    /**
     * 从指定的时间，增加N个星期，然后停留到当周的M天（星期一为1，星期六为6）<br/>
     *
     * @param date
     * @param weeks
     * @param index 当周的天数，从1开始
     * @return
     */
    public static Date shiftWeek(Date date, int weeks, int index) {
        if (index > 7) {
            throw new IllegalArgumentException("星期天的索引号必须满足： [1, 7]");
        }

        final Calendar c = getCalendar(date);
        final int m = c.get(Calendar.MONTH);
        final int y = c.get(Calendar.YEAR);
        final int dow = c.get(Calendar.DAY_OF_WEEK) - 1;
        // 先调整到星期的同一天；
        int index0 = Math.max(dow % 7, (dow + 7) % 8);
        if (index0 != index) {
            c.add(Calendar.DAY_OF_YEAR, (index - index0));
        }

        // 然后再增加N个星期；
        c.add(Calendar.DAY_OF_YEAR, weeks * 7);

        return c.getTime();

    }

    /**
     * 获取当前时间第一天
     *
     * @param date
     * @return
     */
    public static Date getMonthStart(Date date) {
        Calendar c = getCalendar(date);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return c.getTime();
    }

    /**
     * 获取当前时间最后一天
     *
     * @param date
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar c = getCalendar(date);
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 0);//设置为1号,当前日期既为本月第一天
        return c.getTime();
    }

    /**
     * 一个月的天数, 从1开始<br/>
     *
     * @param date
     * @return
     */
    public static int daysOfMonth(Date date) {
        Calendar curr = getCalendar(date);

        return curr.getActualMaximum(Calendar.DATE);
    }

    /**
     * 计算日期在当月的第N天<br/>
     *
     * @param date
     * @return
     */
    public static int daysInMonth(Date date) {
        Calendar curr = getCalendar(date);

        return curr.get(Calendar.DAY_OF_MONTH);
    }

    private static Calendar getCalendar(Date date) {
        Calendar curr = Calendar.getInstance();
        curr.clear();
        curr.setTime(date);

        return curr;
    }

    private static final NavigableSet<Integer> quarters
            = new ConcurrentSkipListSet<>(Arrays.asList(1, 4, 7, 10));

    /**
     * 获取当前季度<br/>
     *
     * @param date
     * @return
     */
    public static int getCurrQuarter(Date date) {
        final int month = getCalendar(date).get(Calendar.MONTH) + 1;
        return quarters.floor(month) / 3 + 1;
    }
//============================4.时间加减=====================================


    /**
     * 为一个日期加上指定年数
     *
     * @param aDate
     * @param amount 年数
     * @return
     */
    public static final Date addYears(Date aDate, int amount) {
        return addTime(aDate, Calendar.YEAR, amount);
    }

    /**
     * 为一个日期加上指定天数
     *
     * @param aDate
     * @param amount 天数
     * @return
     */
    public static final Date addDays(Date aDate, int amount) {
        return addTime(aDate, Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * 为一个日期加上指定天数
     *
     * @param aDate  yyyyMMdd格式字串
     * @param amount 天数
     * @return
     */
    public static final String addDays(String aDate, int amount) {
        try {
            return yyyymmdd(addTime(yyyymmdd(aDate), Calendar.DAY_OF_YEAR, amount));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 为一个日期加上指定小时数
     *
     * @param aDate
     * @param amount 小时数
     * @return
     */
    public static final Date addHours(Date aDate, int amount) {
        return addTime(aDate, Calendar.HOUR, amount);

    }

    /**
     * 为一个日期加上指定分钟数
     *
     * @param aDate
     * @param amount 分钟数
     * @return
     */
    public static final Date addMinutes(Date aDate, int amount) {
        return addTime(aDate, Calendar.MINUTE, amount);
    }

    /**
     * 为一个日期加上指定秒数
     *
     * @param aDate
     * @param amount 秒数
     * @return
     */
    public static final Date addSeconds(Date aDate, int amount) {
        return addTime(aDate, Calendar.SECOND, amount);

    }

    private static final Date addTime(Date aDate, int timeType, int amount) {
        if (aDate == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.add(timeType, amount);
        return cal.getTime();
    }

    /**
     * hhmiss时间格式的美化版本<br/>
     *
     * @param time
     * @param separator
     * @return
     */
    public static String hhmissPretty(String time, String separator) {
        if (StringUtils.isEmpty(separator)) {
            return time;
        }

        if (time.length() != 6) {
            throw new IllegalArgumentException("Unsupported time length");
        }

        String hh = time.substring(0, 2);
        String mi = time.substring(2, 4);
        String ss = time.substring(4);
        return hh + separator + mi + separator + ss;
    }


    /**
     * yyyymmdd时间格式的美化版本<br/>
     *
     * @param date
     * @param separator
     * @return
     */
    public static String yyyymmddPretty(String date, String separator) {
        if (StringUtils.isEmpty(separator)) {
            return date;
        }

        if (StringUtils.isBlank(date) || date.length() != 8) {
            throw new IllegalArgumentException("data length not 8");
        }

        String yyyy = date.substring(0, 4);
        String mm = date.substring(4, 6);
        String dd = date.substring(6);
        return yyyy + separator + mm + separator + dd;
    }

    /**
     * 获取[start, end]的所有日期<br/>
     *
     * @param startDate yyyymmdd
     * @param endDate   yyyymmdd
     * @return
     */
    public static List<String> toDateList(String startDate, String endDate) {
        if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
            return Collections.emptyList();
        }

        if (startDate.compareTo(endDate) > 0) {
            final List<String> result = toDateList(endDate, startDate);
            Collections.reverse(result);
            return result;
        }
        final Date start = yyyymmdd(startDate);
        final Date end = yyyymmdd(endDate);

        final Calendar startC = getCalendar(start);
        final Calendar endC = getCalendar(end);
        int startY = startC.get(Calendar.YEAR);
        int endY = endC.get(Calendar.YEAR);
        int startM = startC.get(Calendar.MONTH);
        int endM = endC.get(Calendar.MONTH);
        int startD = startC.get(Calendar.DAY_OF_MONTH);
        int endD = endC.get(Calendar.DAY_OF_MONTH);

        List<String> result = Collections.EMPTY_LIST;
        // 两个时间在同一年的同一个月;
        if (startY == endY && startM == endM) {
            result = toList(startDate, startD, endD);
        } else {
            int lastDay = daysOfMonth(start); // 开始时间的最后一天；
            // 当前月的数据；
            result = toList(startDate, startD, lastDay);

            // 之后的数据；
            for (int j = startY; j <= endY; ++j) {
                // NOTE：month start from 0， end with 11！！
                for (int i = (j == startY ? startM + 1 : 0); i <= (j == endY ? endM : 11); ++i) {
                    String curDateOfyyyymm = StringUtils.leftPad(j + "", 4, "0") // yyyy
                            + StringUtils.leftPad((i + 1) + "", 2, "0") /*MM*/;
                    String curDate = curDateOfyyyymm + "01";
                    int currLastDay = (endDate.indexOf(curDateOfyyyymm) == 0
                            ? endD : daysOfMonth(yyyymmdd(curDate)));
                    result.addAll(toList(curDate, 1, currLastDay));
                }
            }
        }

        return result;
    }

    private static List<String> toList(String startDate, int startD, int endD) {
        List<String> result = new ArrayList<String>(endD - startD + 1);
        String prefix = startDate.substring(0, 6); // yyyymm
        for (int i = startD; i <= endD; ++i) {
            final String day = Integer.valueOf(i).toString();
            result.add(prefix + StringUtils.leftPad(day, 2, "0"));
        }

        return result;
    }

    /**
     * 两个日期之间的天数，例如：20150901 --> 20150902 返回1<br/>
     *
     * @param endDate
     * @param startDate
     * @return
     */
    public static int days(Date endDate, Date startDate) {
        if (endDate == null || startDate == null) {
            throw new IllegalArgumentException("Parameter is null");
        }

        // 如果相等，则直接返回；
        if (endDate.equals(startDate)) {
            return 0;
        }

        // 排序；
        boolean exchanged = endDate.compareTo(startDate) > 0 ? false : true;
        if (exchanged) {
            Date tmp = endDate;
            endDate = startDate;
            startDate = tmp;
        }

        // 开始日期的年份；
        Calendar endC = getCalendar(endDate);
        int endYear = endC.get(Calendar.YEAR);
        // 结束日期的年份；
        Calendar startC = getCalendar(startDate);
        int startYear = startC.get(Calendar.YEAR);

        // 开始年份的数据
        int days = endYear != startYear ? CoreDateUtils.daysOfYear(startDate)
                - CoreDateUtils.daysInYear(startDate) : 0;
        // 中间年份的数据
        for (int i = 1; i < endYear - startYear; ++i) {
            days += CoreDateUtils.daysOfYear(CoreDateUtils.addYears(startDate, i));
        }
        // 结束年份的数据；
        days += endYear != startYear ? CoreDateUtils.daysInYear(endDate) :
                CoreDateUtils.daysInYear(endDate) - CoreDateUtils.daysInYear(startDate);

        return (exchanged ? -days : days);
    }
}
