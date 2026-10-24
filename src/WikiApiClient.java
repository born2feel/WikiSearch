import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class WikiApiClient {
    private static final String API_URL =
            "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=";

    private HttpClient httpClient;

    public WikiApiClient() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    //отправляет запрос и возвращает ответ сервера в виде json строки
    public String search(String query) throws IOException, InterruptedException {
        //убираем недопустимые символы из запроса (url encoding)
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + encodedQuery))
                .header("User-Agent", "WikiSearch student project") //без этого википедия может не ответить
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Сервер вернул код " + response.statusCode());
        }
        return response.body();
    }
}
