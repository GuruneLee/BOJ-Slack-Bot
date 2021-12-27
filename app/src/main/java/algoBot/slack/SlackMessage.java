package algoBot.slack;

import algoBot.timer.HowAreUToday;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SlackMessage {
//    final Logger logger = LoggerFactory.getLogger(SlackMessage.class);
    private String content;

    public SlackMessage() {
    }

    public SlackMessage(String content) {
        this.content = content;
    }

    public void setContentByDailyTime(LocalDateTime time, List<String> beingNotSolvedMember) {
        String content = time.toLocalTime().format(DateTimeFormatter.ofPattern("HH시. "));
        String members = String.join(", ", beingNotSolvedMember);
        if (time.isEqual(HowAreUToday.PM1)) {
            content += "뭐, 네... 점심먹고 얼마 안됐으니깐요 " + members;
        }
        if (time.isEqual(HowAreUToday.PM7)) {
            content += "식사는 맛있게 하셨을테고, 알고리즘 문제는 어떻게 되가나요 " + members;
        }
        if (time.isEqual(HowAreUToday.PM11)) {
            content += "잘시간이 다가와요!! " + members + " 니들 말고";
        }
        if (time.isEqual(HowAreUToday.AM5)) {
            content += members + ". 아웃.";
        }

        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
