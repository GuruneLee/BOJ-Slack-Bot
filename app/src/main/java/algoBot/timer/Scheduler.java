package algoBot.timer;

import algoBot.boj.BOJClient;
import algoBot.slack.SlackBot;
import algoBot.slack.SlackMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
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
    }

    public void initDailySchedule() {
        HowAreUToday.setDailyTime();

        for (LocalDateTime time : HowAreUToday.getDailyTimeList()) {
            if (time.isBefore(LocalDateTime.now()))
                dailyTimer.schedule(getTaskAt(time), toDate(time));
        }
    }

    private TimerTask getTaskAt(LocalDateTime todo) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    logger.info("{}에 할 일이 {}에 실행되었습니다.",
                            todo.format(DateTimeFormatter.ofPattern("MM월dd일 HH시mm분")),
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM월dd일 HH시mm분"))
                    );
                    List<String> beingNotSolveMembersToday = bojClient.crawlBeingNotSolveMembersToday();
                    SlackMessage message = new SlackMessage();
                    message.setContentByDailyTime(todo, beingNotSolveMembersToday);
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

}
