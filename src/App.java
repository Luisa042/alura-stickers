import java.io.File;
import java.io.FileInputStream;
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
        List<Map<String, String>> movieList = parser.parse(json);

        StickerGenerator generator = new StickerGenerator();

        // checks if 'stickers' directory exists and create if doesn't exists
        var directory = new File("stickers/");
        directory.mkdir();

        // show and manipulate data
        for (Map<String, String> movie : movieList) {
            String title = movie.get("title");
            String imageURL = movie.get("image");
            String imDbRating = movie.get("imDbRating");

            // print info on terminal
            System.out.println("\u001b[1mTitle:\u001b[0m \u001b[44m " + title + " \u001b[m");
            System.out.print("\u001b[1mRating:\u001b[0m " + imDbRating + " ");
            
            // print stars according to rating
            double classification = Double.parseDouble(imDbRating);
            int stars = (int) classification;
            for (int n = 1; n <= stars; n++) {
                System.out.print("⭐");
            }
            System.out.println("\n");

            // generate subtitle and add image according to rating
            String subtitle;
            InputStream superposingImage;
            if (classification >= 8) {
                subtitle = "EXCELLENT";
                superposingImage = new FileInputStream(new File("superposition/yippie.png"));
            } else {
                subtitle = "MID";
                superposingImage = new FileInputStream(new File("superposition/empty.jpg"));
            }

            // generates stickers from url
            InputStream inputStream = new URL(imageURL).openStream();
            String stickerName = directory + "/" + title + ".png";
            generator.create(inputStream, stickerName, subtitle, superposingImage);
        }
    }      
}

