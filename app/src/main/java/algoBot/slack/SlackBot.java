package algoBot.slack;

import algoBot.helper.httpHelper.HttpConnection;
import algoBot.helper.httpHelper.HttpHelper;
import algoBot.helper.resourceReader.YamlReader;
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
        BotSecretDto botSecretDto = new YamlReader().readToMap("secret.yml");
        String token = botSecretDto.token;
        String channelId = botSecretDto.channelId;

        String chatPostMessageURL = "https://slack.com/api/chat.postMessage";
        HttpConnection connect = httpHelper.connect(chatPostMessageURL);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Authorization", "Bearer "+ token);

        Map<String, String> params = new HashMap<>();
        params.put("channel", channelId); //bot-test
        params.put("text", message.getContent());

        connect.setMethod("POST");
        connect.setHeaders(headers);
        connect.setParameters(params);
        connect.setTimeout(5000, 5000);


        //TODO response 에 ok:false 일 때 핸들링 하기
        String responseBody = connect.getResponseBody();

        logger.info("메시지가 발송되었습니다. : {}", message.getContent());
        logger.info("ResponseBody : {}", responseBody);

    }
}
