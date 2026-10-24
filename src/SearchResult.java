public class SearchResult {
    private String title;
    private int pageId;
    private String snippet;

    public SearchResult(String title, int pageId, String snippet) {
        this.title = title;
        this.pageId = pageId;
        this.snippet = snippet;
    }

    public String getTitle() {
        return title;
    }

    public int getPageId() {
        return pageId;
    }

    public String getSnippet() {
        return snippet;
    }

    //ссылка на статью по ее pageid
    public String getUrl() {
        return "https://ru.wikipedia.org/w/index.php?curid=" + pageId;
    }
}
