package at.laola1.newsreader.feed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader { // TODO PK more specific name
    private URL url; // TODO PK final

    // TODO PK format

    public Downloader(URL url) {
        this.url = url;
    }

    public String getContent() throws IOException {

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) this.url.openConnection();
            return readStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace(); // TODO PK nono
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null; // TODO PK nonono
    }

    private String readStream(InputStream in) throws IOException { // TODO PK rename in
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close(); // TODO PK try with resource or finally
        return response.toString();
    }
}
