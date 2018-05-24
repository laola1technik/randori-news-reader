package at.laola1.newsreader.feed;

// TODO PK maybe static inner class of HttpTextDownloader
public interface FinishedDownloadCallback {
    void onDownloadFinish(String response);
}
