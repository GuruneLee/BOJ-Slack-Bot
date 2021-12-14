package algoBot.slack;

import algoBot.helper.httpHelper.HttpConnection;
import algoBot.helper.httpHelper.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

// TODO 토큰, 채널 ID 를 설정파일로 분리하기
public class SlackBot {
    final Logger logger = LoggerFactory.getLogger(SlackBot.class);
    private final HttpHelper httpHelper = new HttpHelper();

    // TODO 타임아웃 등 202가 아닌 응답 핸들링하기
    public void sendMessage(SlackMessage message) throws Exception {
        String chatPostMessageURL = "https://slack.com/api/chat.postMessage";
        HttpConnection connect = httpHelper.connect(chatPostMessageURL);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Authorization", "Bearer {봇 토큰}}");

        Map<String, String> params = new HashMap<>();
        params.put("channel", "{채널 ID}"); //bot-test
        params.put("text", message.getContent());

        connect.setMethod("POST");
        connect.setHeaders(headers);
        connect.setParameters(params);

        String responseBody = connect.getResponseBody();

        logger.info("메시지가 발송되었습니다. : {}", message.getContent());
        logger.info("ResponseBody : {}", responseBody);

    }
}
