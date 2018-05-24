package at.laola1.newsreader.feed;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

public class DownloaderAsyncTask extends AsyncTask<URL, Void, String> {

    private FinishedDownloadCallback callback; // TODO PK final

    public DownloaderAsyncTask(FinishedDownloadCallback callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(URL... urls) {
        try {
            return new Downloader(urls[0]).getContent(); // TODO PK nonono
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String response) {
        callback.onDownloadFinish(response);
    } // TODO PK sort to bottom
}