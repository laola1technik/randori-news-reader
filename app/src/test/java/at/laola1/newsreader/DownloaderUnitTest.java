package at.laola1.newsreader;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URL;

import at.laola1.newsreader.feed.Downloader;

import static org.junit.Assert.assertEquals;

public class DownloaderUnitTest {
    private HttpServer server;

    @Test
    public void shouldDownloadEmptyFile() throws IOException {
        startServer(); // TODO PK start and stop in @Before and @After
        URL url = new URL("http://127.0.0.1:8801/emptyFile");
        Downloader downloader = new Downloader(url);
        String response = downloader.getContent();
        assertEquals(0, response.length());
        stopServer();
    }

    @Test
    public void shouldDownloadFileWithText() throws IOException {
        startServer();

        URL url = new URL("http://127.0.0.1:8801/fileWithText");
        Downloader downloader = new Downloader(url);
        String response = downloader.getContent();
        assertEquals("text", response);
        stopServer();
        // TODO PK format
    }

    private void stopServer() { // TODO PK sort below start
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
    // Todo: Method execute in android.os.AsyncTask not mocked.
}