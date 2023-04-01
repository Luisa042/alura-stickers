import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // get data from api
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        
        var http = new ClientHttp();
        String json = http.getData(url);

        // parse data
        JsonParser parser = new JsonParser();
        List<Map<String, String>> contentList = parser.parse(json);

        StickerGenerator generator = new StickerGenerator();

        // checks if 'stickers' directory exists and create if doesn't exists
        var directory = new File("stickers/");
        directory.mkdir();

        // show and manipulate data
        for (Map<String, String> content : contentList) {
            String title = content.get("title");
            String imageURL = content.get("image");
            String subtitle = "TESTING";

            // print info on terminal
            System.out.println("\u001b[1mTitle:\u001b[0m \u001b[44m " + title + " \u001b[m");
            System.out.println();

            // generates stickers from url
            InputStream inputStream = new URL(imageURL).openStream();
            String stickerName = directory + "/" + title + ".png";
            generator.create(inputStream, stickerName, subtitle);
        }
    }      
}