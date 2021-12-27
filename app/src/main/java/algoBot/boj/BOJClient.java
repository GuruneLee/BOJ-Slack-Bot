package algoBot.boj;

import algoBot.helper.httpHelper.ParameterStringBuilder;
import algoBot.timer.HowAreUToday;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
            "hyj2508",
            "iamwho"
    };

    public BOJClient() {
    }

    public LocalDateTime crawlLatestSolveTimeByName(String name) throws IOException, URISyntaxException {
        String origin = "https://www.acmicpc.net/status";
        Map<String, String> params = new HashMap<>();
        params.put("problems_id", "");//모든문제
        params.put("user_id", name);
        params.put("language_id", "-1"); //모든언어
        params.put("result_id", "4"); //맞았습니다
        String url = origin + "?" + ParameterStringBuilder.getParamsString(params);

        Document doc = Jsoup.parse(new URI(url).toURL(), 5000);
        Elements elements = doc.select("table#status-table>tbody>tr>td>a.show-date");
        String time = elements.first().attr("title"); //yyyy-mm-dd hh:mm:ss

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(time, dateTimeFormatter);
    }

    /**
     * @return 지금 안 푼 사람 리턴
     * @throws IOException
     */
    public List<String> crawlBeingNotSolveMembersToday() throws IOException, URISyntaxException {
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
