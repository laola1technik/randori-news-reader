package at.laola1.newsreader.feed;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

public class DownloaderAsyncTask extends AsyncTask<URL, Void, DownloaderAsyncTask.Result> {

    private final FinishedDownloadCallback callback;

    public DownloaderAsyncTask(FinishedDownloadCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Result doInBackground(URL... urls) {
        try {
            String content = new HttpTextDownloader(urls[0]).getContent();
            return new Result(content);
        } catch (IOException error) {
            return new Result(error);
        }
    }

    @Override
    protected void onPostExecute(Result result) {
        callback.onDownloadFinish(result.response);
        //TODO BG Handle Error
    }

    public static class Result {
        private final String response;
        private final IOException downloadFailed;

        private Result(String response) {
            this.response = response;
            this.downloadFailed = null;
        }

        private Result(IOException downloadFailed) {
            this.response = null;
            this.downloadFailed = downloadFailed;
        }

        public boolean success() {
            return this.downloadFailed == null;
        }
    }
}