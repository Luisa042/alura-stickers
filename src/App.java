import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // get data from api
        API api = API.NASA_APOD;
        String url = api.getUrl();
        ContentExtractor extractor = api.getExtractor();

        var http = new ClientHttp();
        String json = http.getData(url);

        // parse data
        List<Content> contentList = extractor.extractContent(json);

        StickerGenerator generator = new StickerGenerator();

        // check if 'stickers' directory exists and creates it if doesn't exists
        File directory = new File("stickers/");
        directory.mkdir();

        // show and manipulate data
        for (int i = 0; i < 5; i++) {
            Content content = contentList.get(i);

            String title = content.getTitle();
            String imageURL = content.getImageURL();
            String subtitle = "TESTING";

            InputStream inputStream = new URL(imageURL).openStream();
            String stickerName = directory + "/" + title + ".png";

            // generate stickers
            generator.create(inputStream, stickerName, subtitle);

            // print info on terminal
            System.out.println("\u001b[1mTitle:\u001b[0m \u001b[44m " + title + " \u001b[m");
            System.out.println();
        }
    }      
}