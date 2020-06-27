package hx.base.core.manage.tools;

import org.apache.commons.compress.utils.Lists;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @ClassName MyTimeTools
 * @Description 时间相关工具
 * @Author HuoJiaJin
 * @Date 2020/6/27 5:44
 * @Version 1.0
 **/
public class MyTimeTools {

    public static String timeToStr(LocalDateTime time){
        return timeToStr(time, "yyyy-MM-dd HH:mm:ss");
    }

    public static String timeToStr(LocalDateTime time, String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(time);
    }

    public static LocalDateTime strToTime(String timeStr){
        return strToTime(timeStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static LocalDateTime strToTime(String timeStr, String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(timeStr, df);
    }

    public static List<Integer> getQuarter(int month){
        List<Integer> quarter = Lists.newArrayList();
        if (month >= 1 && month <= 3){
            quarter.add(1);
            quarter.add(2);
            quarter.add(3);
        } else if (month >= 4 && month <= 6){
            quarter.add(4);
            quarter.add(5);
            quarter.add(6);
        } else if (month >= 7 && month <= 9){
            quarter.add(7);
            quarter.add(8);
            quarter.add(9);
        } else if (month >= 10 && month <= 12){
            quarter.add(10);
            quarter.add(11);
            quarter.add(12);
        }
        return quarter;
    }
}
