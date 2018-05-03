package at.laola1.newsreader;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import at.laola1.newsreader.feed.Downloader;

import static org.junit.Assert.assertEquals;

public class DownloaderUnitTest {
    private HttpServer server;

    @Test
    public void shouldDownloadEmptyFile() throws IOException {
        startServer();
        try {
            Downloader downloader = new Downloader("http://127.0.0.1:8801/test");
            String content = downloader.getContent();
            assertEquals(0, content.length());
        } finally {
            stopServer();
        }
    }

    private void stopServer() {
        server.stop(0);
    }


    public void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8801), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}