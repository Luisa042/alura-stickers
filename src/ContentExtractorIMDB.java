import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorIMDB implements ContentExtractor {
    public List<Content> extractContent(String json) {
        // extract usable data
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);
        
        List<Content> contentList = new ArrayList<>();

        // populate content list
        for (Map<String, String> attributes : attributesList) {
            String title = attributes.get("title");
            String imageURL = attributes.get("image").replaceAll("(@+)(*.).jpg$", "$1.jpg");

            Content content = new Content(title, imageURL);

            contentList.add(content);
        }
        return contentList;
    }
}
