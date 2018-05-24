package at.laola1.newsreader.feed;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader { // TODO PK more specific name
    private URL url; // TODO PK final

    // TODO PK format

    public Downloader(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public void getContent(FinishedDownloadCallback callback) throws IOException {
        new DownloadTask(callback).execute(this.url);
    }


    private static class DownloadTask extends AsyncTask<URL, Void, String> {

        private FinishedDownloadCallback callback; // TODO PK final

        public DownloadTask(FinishedDownloadCallback callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(URL... urls) {

            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) urls[0].openConnection();
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

        @Override
        protected void onPostExecute(String response) {
            callback.onDownloadFinish(response);
        } // TODO PK sort to bottom

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
}
