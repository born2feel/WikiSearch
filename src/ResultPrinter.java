import java.util.List;

public class ResultPrinter {

    public void printResults(List<SearchResult> results) {
        System.out.println("--- Результаты поиска ---");
        for (int i = 0; i < results.size(); i++) {
            SearchResult result = results.get(i);
            System.out.println((i + 1) + ". " + result.getTitle());
            System.out.println("   " + cleanSnippet(result.getSnippet()));
        }
    }

    //в snippet приходят html теги типа <span class="searchmatch">, убираем их
    private String cleanSnippet(String snippet) {
        String text = snippet.replaceAll("<[^>]*>", "").replace("&quot;", "\"");
        if (text.length() > 100) {
            text = text.substring(0, 100) + "...";
        }
        return text;
    }
}
