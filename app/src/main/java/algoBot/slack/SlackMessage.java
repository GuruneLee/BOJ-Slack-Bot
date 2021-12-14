package algoBot.slack;

import algoBot.timer.HowAreUToday;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

public class SlackMessage {
//    final Logger logger = LoggerFactory.getLogger(SlackMessage.class);
    private String content;

    public SlackMessage() {
    }

    public SlackMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    //    ex) 1pm. 네! 뭐 점심먹고 얼마 안됐으니깐요! @Chlee4858 @Hyj879 님~
//    ex) 7pm. 식사는 맛있게 하셨을테고, 알고리즘 문제는 어떻게 되가나요 @Chlee4858 님???
//    ex) 1am. 잘시간이 다가와요!! 하하, 너 말고 @Chlee4858.
//    ex) 6am. @Chlee4858. 아웃.
//    public void writeContentWithNamesAndTime(List<String> names, LocalDateTime time) throws Exception {
//        String strNames = String.join(", ", names);
//        String tmp;
//
//        System.out.println("time = " + time);
//
//        if (time.isBefore(HowAreUToday.START_TIME)) {
//            logger.error("{}는 오늘 시간대가 아닙니다. 오늘은 {} 부터 입니다.", time, HowAreUToday.START_TIME);
//            throw new IllegalArgumentException("오늘 시간대가 아닙니다");
//        }
//
//        if (time.isBefore(HowAreUToday.PM1)) {
//            logger.info("오후 1시 에 보내는 메시지. {}", HowAreUToday.PM1);
//            tmp = "오후 1시에 보냅니다. 안푼사람: " + strNames;
//        } else if (time.isBefore(HowAreUToday.PM7)) {
//
//        } else if (time.isBefore(HowAreUToday.AM1)) {
//
//        } else if (time.isBefore(HowAreUToday.AM6)) {
//
//        }
//
//        if (time.isAfter(HowAreUToday.START_TIME) && time.isBefore(HowAreUToday.PM1)) {
//            tmp = "네! 뭐, 점심먹고 얼마 안됐으니깐요!! " + strNames + " 님!!!";
//        } else if (time.isAfter(HowAreUToday.START_TIME) && time.isBefore(HowAreUToday.PM7)) {
//            tmp = "식사는 맛있게 하셨을 테고, 알고리즘 문제는 어떻게 되가나요 " + strNames + " 님?";
//        } else if (time.isAfter(HowAreUToday.START_TIME) && time.isBefore(HowAreUToday.AM1)) {
//            tmp = "잘 시간이 다가와요~ " + strNames + " 님!!";
//        } else if (time.isAfter(HowAreUToday.START_TIME) && time.isBefore(HowAreUToday.AM6)) {
//            tmp = "6AM... " + strNames + " 아웃. ";
//        } else {
//            throw new Exception("마지막으로 푼 시간이 오늘 시간대가 아닙니다 : " + time);
//        }
//
////        if (time.isAfter(HowAreUToday.START_TIME) && time.isBefore(HowAreUToday.test1)) {
////            tmp = "test1" + strNames;
////        } else if (time.isAfter(HowAreUToday.START_TIME) && time.isBefore(HowAreUToday.test2)) {
////            tmp = "test2" + strNames;
////        } else {
////            throw new Exception("오늘 시간대가 아닙니다");
////        }
//        content = tmp;
//    }
}
