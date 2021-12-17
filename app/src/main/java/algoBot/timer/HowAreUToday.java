package algoBot.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HowAreUToday {
    public static LocalDate TODAY_DATE;
    public static LocalDateTime START_TIME;
    public static LocalDateTime PM1;
    public static LocalDateTime PM7;
    public static LocalDateTime PM11;
    public static LocalDateTime AM5;

    public List<LocalDateTime> getDailyTimeList() {
        return new ArrayList<>(Arrays.asList(PM1, PM7, PM11, AM5));
    }

    public static void setTodayDate() {
        Logger logger = LoggerFactory.getLogger(HowAreUToday.class);

        LocalTime nowTime = LocalTime.now();
        if (nowTime.isAfter(LocalTime.MIN) && nowTime.isBefore(LocalTime.of(5,59,59))) {
            //익일 0시-6시
            TODAY_DATE = LocalDate.now().minusDays(1L);
        } else {
            //당일 6시-24시
            TODAY_DATE = LocalDate.now();
        }

//test();

        logger.info("오늘 날짜는 {} 입니다", TODAY_DATE);
    }

//    private static void test() {
//        TODAY_DATE = LocalDate.now();
//    }

    public static void setDailyTime() {
        Logger logger = LoggerFactory.getLogger(HowAreUToday.class);
        setTodayDate();
        START_TIME = LocalDateTime.of(TODAY_DATE, LocalTime.of(6, 0));
        PM1 = LocalDateTime.of(TODAY_DATE, LocalTime.of(13, 0)); //당일 오후 1시
        PM7 = LocalDateTime.of(TODAY_DATE, LocalTime.of(19, 0)); //당일 오후 7시
        PM11 = LocalDateTime.of(TODAY_DATE, LocalTime.of(23, 0)); //당일 오후 11시
        AM5 = LocalDateTime.of(TODAY_DATE.plusDays(1L), LocalTime.of(5, 0)); //익일 오전 5시

        logger.info("오늘 시간대 세팅이 완료되었습니다.\n" +
                        "START_TIME : {}, " +
                        "PM1 : {}, PM7 : {}, AM1 : {}, AM6 : {}",
                START_TIME, PM1, PM7, PM11, AM5);
    }
}
