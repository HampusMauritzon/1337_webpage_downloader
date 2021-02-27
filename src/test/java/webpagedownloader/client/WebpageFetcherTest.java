package webpagedownloader.client;

import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webpagedownloader.DownloaderExecutorService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WebpageFetcherTest {
	private WebpageFetcher webpageFetcher;

	private DownloaderExecutorService downloaderExecutorService;
	private CloseableHttpAsyncClient client;

	@BeforeEach
	public void setup() {
		downloaderExecutorService = mock(DownloaderExecutorService.class);
		client = mock(CloseableHttpAsyncClient.class);
		webpageFetcher = new WebpageFetcher(downloaderExecutorService, client);
	}

	@Test
	public void testFetchWebpage() throws URISyntaxException {
		URI url = new URI("http://tretton37.com");
		BiConsumer<URI, String> biConsumer = (a, b) -> doNothing();
		webpageFetcher.fetchWebpage(url, biConsumer);

		verify(downloaderExecutorService).addFuture(any(Future.class));
	}
}
