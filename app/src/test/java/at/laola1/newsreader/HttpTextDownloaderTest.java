package at.laola1.newsreader;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URL;

import at.laola1.newsreader.feed.HttpTextDownloader;

import static org.junit.Assert.assertEquals;

public class HttpTextDownloaderTest {

    private HttpServer fakeServer;

    @Before
    public void startServer() throws IOException {
        fakeServer = HttpServer.create(new InetSocketAddress(8801), 0);
        fakeServer.createContext("/emptyFile", new ServeTextResponse(""));
        fakeServer.createContext("/fileWithText", new ServeTextResponse("text"));
        fakeServer.start();
    }

    @After
    public void stopServer() {
        fakeServer.stop(0);
    }

    @Test
    public void shouldDownloadEmptyFile() throws IOException {
        URL url = new URL("http://127.0.0.1:8801/emptyFile");
        HttpTextDownloader httpTextDownloader = new HttpTextDownloader(url);

        String response = httpTextDownloader.getContent();

        assertEquals(0, response.length());
    }

    @Test
    public void shouldDownloadFileWithText() throws IOException {
        URL url = new URL("http://127.0.0.1:8801/fileWithText");
        HttpTextDownloader httpTextDownloader = new HttpTextDownloader(url);

        String response = httpTextDownloader.getContent();

        assertEquals("text", response);
    }

    static class ServeTextResponse implements HttpHandler {
        private final String response;

        private ServeTextResponse(String response) {
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