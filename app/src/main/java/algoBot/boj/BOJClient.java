package algoBot.boj;

import algoBot.helper.httpHelper.ParameterStringBuilder;
import algoBot.timer.HowAreUToday;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BOJClient {

    private static final String[] members = {
            "sanbada79",
            "dlckdgk4858",
            "hy01n",
            "rjsgh7943",
            "koderjoon",
            "koseyeon",
            "hyj2508"
    };

    public BOJClient() {
//        this.members = new ArrayList<>();
    }
//    public BOJClient(List<String> members) {
//        this.members = new ArrayList<>(members);
//    }

    public LocalDateTime crawlLatestSolveTimeByName(String name) throws IOException {
        String origin = "https://www.acmicpc.net/status";
        Map<String, String> params = new HashMap<>();
        params.put("problems_id", "");//모든문제
        params.put("user_id", name);
        params.put("language_id", "-1"); //모든언어
        params.put("result_id", "4"); //맞았습니다
        String url = origin + "?" + ParameterStringBuilder.getParamsString(params);

        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("table#status-table>tbody>tr>td>a.show-date");
        String time = elements.first().attr("title"); //yyyy-mm-dd hh:mm:ss

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(time, dateTimeFormatter);
    }

    /**
     * @param time 기준 시간 (START_TIME ~ time)
     * @return 기준시간 까지 풀지 않은 사람
     * @throws IOException
     */
    public List<String> crawlBeingNotSolveMembersUntilTime(LocalDateTime time) throws IOException {
        List<String> beingNotSolveMembers = new ArrayList<>();
        for (String member : members) {
            LocalDateTime lastSolve = crawlLatestSolveTimeByName(member);
            if (!(lastSolve.isAfter(HowAreUToday.START_TIME) && lastSolve.isBefore(time))) {
                beingNotSolveMembers.add(member);
            }
        }
        return beingNotSolveMembers;
    }

    /**
     * @return 오늘 안 푼 사람 리턴
     * @throws IOException
     */
    public List<String> crawlBeingNotSolveMembersToday() throws IOException {
        List<String> beingNotSolveMembers = new ArrayList<>();
        for (String member : members) {
            LocalDateTime lastSolve = crawlLatestSolveTimeByName(member);
            if (lastSolve.isBefore(HowAreUToday.START_TIME)) {
                beingNotSolveMembers.add(member);
            }
        }
        return beingNotSolveMembers;
    }


}
