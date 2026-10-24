import java.io.IOException;
import java.util.List;

public class WikiSearchApp {
    private ConsoleInput input;
    private WikiApiClient apiClient;
    private ResponseParser parser;
    private ResultPrinter printer;
    private BrowserOpener browserOpener;

    public WikiSearchApp() {
        this.input = new ConsoleInput();
        this.apiClient = new WikiApiClient();
        this.parser = new ResponseParser();
        this.printer = new ResultPrinter();
        this.browserOpener = new BrowserOpener();
    }

    public void start() {
        System.out.println("--- Поиск по Википедии ---");

        while (true) {
            String query = input.readQuery();

            if (query.equalsIgnoreCase("exit")) {
                System.out.println("Завершение работы программы.");
                break;
            }
            if (query.isEmpty()) {
                System.out.println("Ошибка: запрос не может быть пустым!");
                continue;
            }

            searchAndOpen(query);
        }
    }

    private void searchAndOpen(String query) {
        List<SearchResult> results;
        try {
            String json = apiClient.search(query);
            results = parser.parse(json);
        } catch (IOException | InterruptedException e) {
            System.out.println("Ошибка сети: не удалось получить ответ от Википедии.");
            return;
        } catch (RuntimeException e) {
            //gson кидает runtime исключения если json не такой как ожидали
            System.out.println("Ошибка: не удалось разобрать ответ сервера.");
            return;
        }

        if (results.isEmpty()) {
            System.out.println("По запросу \"" + query + "\" ничего не найдено.");
            return;
        }

        printer.printResults(results);

        //спрашиваем номер пока не введут правильный
        while (true) {
            int number = input.readArticleNumber(results.size());
            if (number == 0) {
                return;
            }
            if (number >= 1 && number <= results.size()) {
                browserOpener.open(results.get(number - 1).getUrl());
                return;
            }
            System.out.println("Ошибка: нет статьи с таким номером!");
        }
    }
}
