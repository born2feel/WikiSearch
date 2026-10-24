import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class ResponseParser {

    //ответ выглядит так: {"query": {"search": [{"title": ..., "pageid": ..., "snippet": ...}, ...]}}
    public List<SearchResult> parse(String json) {
        List<SearchResult> results = new ArrayList<>();

        JsonObject root = JsonParser.parseString(json).getAsJsonObject();
        JsonArray search = root.getAsJsonObject("query").getAsJsonArray("search");

        for (JsonElement element : search) {
            JsonObject item = element.getAsJsonObject();
            String title = item.get("title").getAsString();
            int pageId = item.get("pageid").getAsInt();
            String snippet = item.get("snippet").getAsString();
            results.add(new SearchResult(title, pageId, snippet));
        }
        return results;
    }
}
