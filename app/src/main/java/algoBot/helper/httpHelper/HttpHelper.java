package algoBot.helper.httpHelper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {
    public HttpConnection connect(String url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        return new HttpConnection(httpURLConnection);
    }
}
