package at.laola1.newsreader.feed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {
    private URL url;

    public Downloader(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public String getContent() throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) this.url.openConnection();
        String response = readStream(urlConnection.getInputStream());
        urlConnection.disconnect();
        return response;
    }

    private String readStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }

}
