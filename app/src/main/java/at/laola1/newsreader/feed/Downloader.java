package at.laola1.newsreader.feed;

import android.os.AsyncTask;

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

    public void getContent(FinishedDownloadCallback callback) throws IOException {
        new DownloadTask(callback).execute(this.url);
    }


    private static class DownloadTask extends AsyncTask<URL, Void, String> {

        private FinishedDownloadCallback callback;

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
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            callback.onDownloadFinish(response);
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
}
