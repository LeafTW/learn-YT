package day23ts;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

public class timets {
    @Test
    public void test1() {
        Date date = new Date();
        System.out.println(date);
        System.out.println(System.currentTimeMillis());
        System.out.println(date.getTime());
        System.out.println();
        Date date1 = new Date(date.getTime());
        System.out.println(date1.getTime());
        System.out.println(date1.toString());
    }

    @Test
    public void test2() {
        Date date = new Date(); // 产生一个Date实例
        // 产生一个formater格式化的实例
        SimpleDateFormat formater = new SimpleDateFormat();
        System.out.println(formater.format(date));// 打印输出默认的格式
        SimpleDateFormat formater2 = new SimpleDateFormat("yyyy年MM月dd日 EEE HH:mm:ss");
        System.out.println(formater2.format(date));
        try {
            // 实例化一个指定的格式对象
            Date date2 = formater2.parse("2008年08月08日 星期一 08:08:08");
            // 将指定的日期解析后格式化按指定的格式输出
            System.out.println(date2.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3() {
        Calendar calendar = Calendar.getInstance();
        // 从一个 Calendar 对象中获取 Date 对象
        Date date = calendar.getTime();
        // 使用给定的 Date 设置此 Calendar 的时间
//        date = new Date(234234235235L);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 8);
        System.out.println("当前时间日设置为8后,时间是:" + calendar.getTime());
        calendar.add(Calendar.HOUR, 2);
        System.out.println("当前时间加2小时后,时间是:" + calendar.getTime());
        calendar.add(Calendar.MONTH, -2);
        System.out.println("当前日期减2个月后,时间是:" + calendar.getTime());
    }

    @Test
    public void test4() {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);

        LocalDateTime t1 = LocalDateTime.of(2022, 8, 8, 23, 52, 00);
        System.out.println(t1);

        System.out.println(t1.getMonth());
        System.out.println(t1.getMonthValue());

        LocalDateTime time1 = t1.withDayOfMonth(22);
        System.out.println(t1);
        System.out.println(time1);




    }

    @Test
    public void test5() {
        Instant now = Instant.now();
        System.out.println(now);

        now.atZone(ZoneOffset.ofHours(8));

        //自1970年1月1日0时0分0秒(UTC)开始的秒数
        long epochMilli = now.toEpochMilli();
        System.out.println(epochMilli);

        Instant instant = Instant.ofEpochMilli(epochMilli);
        System.out.println(instant);

    }

    @Test
    public void test6() {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        String format = timeFormatter.format(LocalDateTime.now());
        System.out.println(format);

        TemporalAccessor parse = timeFormatter.parse("2022-09-07T00:27:57.035086");
        System.out.println(parse);

//        FormatStyLe. LONG Formatstyle.MEDIUM / Formatstyle.SHORT:适用于LocalDateTime
        DateTimeFormatter ofLocalizedDate = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        String format1 = ofLocalizedDate.format(LocalDateTime.now());
        System.out.println(format1);

//        重点:方式三自定义的格式。如:ofpattern("MM-yyyy-dd hh:mm:ss")
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("MM-yyyy-dd hh:mm:ss");
        String format2 = ofPattern.format(LocalDateTime.now());
        System.out.println(format2);

        TemporalAccessor parse1 = ofPattern.parse("09-2022-07 12:50:26");//一定要合ofPattern格式內容一樣
        System.out.println(parse1);

    }

}
