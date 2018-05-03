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
            Downloader downloader = new Downloader("http://127.0.0.1:8801/emptyFile");
            String content = downloader.getContent();
            assertEquals(0, content.length());
        } finally {
            stopServer();
        }
    }

    @Test
    public void shouldDownloadFileWithText() throws IOException {
        startServer();
        try {
            Downloader downloader = new Downloader("http://127.0.0.1:8801/fileWithText");
            String content = downloader.getContent();
            assertEquals("text", content);
        } finally {
            stopServer();
        }
    }

    private void stopServer() {
        server.stop(0);
    }

    private void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8801), 0);
        server.createContext("/emptyFile", new FileHandler(""));
        server.createContext("/fileWithText", new FileHandler("text"));
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class FileHandler implements HttpHandler {
        private String response;

        private FileHandler(String response) {
            this.response = response;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}