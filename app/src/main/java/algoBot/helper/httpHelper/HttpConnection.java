package algoBot.helper.httpHelper;

import algoBot.slack.SlackBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.Map;

// TODO timeout 설정하는 메서드 만들기
public class HttpConnection {
    final Logger logger = LoggerFactory.getLogger(SlackBot.class);

    private final HttpURLConnection httpURLConnection;

    public HttpConnection(HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }

    public void setMethod(String method) throws ProtocolException {
        httpURLConnection.setRequestMethod(method);
    }
    public void setHeaders(Map<String,String> headers){
        for (Map.Entry<String, String> kv : headers.entrySet()) {
            httpURLConnection.setRequestProperty(kv.getKey(), kv.getValue());
        }
    }
    public void setParameters(Map<String,String> params) throws IOException {
        httpURLConnection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(params));
        out.flush();
        out.close();
    }
    public void setTimeout(int conn, int read) {
        httpURLConnection.setConnectTimeout(conn);
        httpURLConnection.setReadTimeout(read);
    }
    public String getResponseBody() throws IOException, InterruptedException {
        while (true) {
            int status = httpURLConnection.getResponseCode();
            logger.info("http status : {}", status);
            if (200 <= status && status < 400) {
                break;
            } else {
                Thread.sleep(1000);
            }
        }


        BufferedReader in = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream())
        );
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        httpURLConnection.disconnect();

        return content.toString();
    }
}
