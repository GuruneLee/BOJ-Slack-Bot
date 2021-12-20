package algoBot.timer;

import algoBot.boj.BOJClient;
import algoBot.slack.SlackBot;
import algoBot.slack.SlackMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {
    final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    private final BOJClient bojClient = new BOJClient();
    private final SlackBot slackBot = new SlackBot();
    private static final Timer mainTimer = new Timer();
    private static final Timer dailyTimer = new Timer();

    public void schedule() {
        long cycle = 24*60*60*1000L;
        HowAreUToday.setTodayDate();

        TimerTask theFirstTask = new TimerTask() {
            @Override
            public void run() {
                initDailySchedule();
            }
        };

        mainTimer.scheduleAtFixedRate(theFirstTask, toDate(LocalDateTime.of(HowAreUToday.TODAY_DATE, LocalTime.of(6,0))), cycle);
//        mainTimer.scheduleAtFixedRate(theFirstTask, toDate(LocalDateTime.of(HowAreUToday.TODAY_DATE, LocalTime.of(0,0))), cycle);
    }

    public void initDailySchedule() {
        // 하루 시간대 세팅하기
        HowAreUToday.setDailyTime();

        // 데일리 타이머에 오늘 할 일 세팅하기 - schedule(task, time) 메서드를 사용해서 한 번만 실행
//        // FIXME 이미 지나간 시간엔 스케쥴링 하지 않게 하기
//        dailyTimer.schedule(getTaskAt(HowAreUToday.PM1), toDate(HowAreUToday.PM1));
//        dailyTimer.schedule(getTaskAt(HowAreUToday.PM7), toDate(HowAreUToday.PM7));
//        dailyTimer.schedule(getTaskAt(HowAreUToday.PM11), toDate(HowAreUToday.PM11));
//        dailyTimer.schedule(getTaskAt(HowAreUToday.AM5), toDate(HowAreUToday.AM5));
        LocalDateTime cur = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        while (cur.isBefore(HowAreUToday.AM5.plusHours(1L))) {
            cur = cur.plusHours(1L);
            dailyTimer.schedule(getTaskAt(cur), toDate(cur));
            System.out.println("cur = " + cur.format(DateTimeFormatter.ofPattern("dd일,HH시mm분")));
        }
//test();

    }
//    private void test() {
//        // 1분마다 task 등록하기
//        LocalDateTime time = LocalDateTime.now().withSecond(0).plusMinutes(1);
//        while (time.isBefore(HowAreUToday.START_TIME.plusDays(1))) {
//            System.out.println("time = " + time);
//            LocalDateTime ldt = time.plusMinutes(1);
//            dailyTimer.schedule(getTaskAt(ldt), toDate(ldt));
//            time = ldt;
//        }
//    }

    private TimerTask getTaskAt(LocalDateTime todo) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    logger.info("{}에 할 일이 {}에 실행되었습니다.",
                            todo.format(DateTimeFormatter.ofPattern("MM월dd일 HH시mm분")),
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM월dd일 HH시mm분"))
                    );
                    List<String> beingNotSolveMembers = bojClient.crawlBeingNotSolveMembersToday();
                    SlackMessage message = new SlackMessage(
                                    todo.toLocalTime().format(DateTimeFormatter.ofPattern("HH시mm분"))
                                            +"까지 안 푼 사람"
                                            +String.join(", ", beingNotSolveMembers) );
                    slackBot.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }


    private Date toDate(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

//    private void crawlAndSend() {
//
//    }

}
