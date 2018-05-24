package at.laola1.newsreader.feed;

// TODO PK maybe static inner class of Downloader
public interface FinishedDownloadCallback {
    void onDownloadFinish(String response);
}
