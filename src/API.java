public enum API {
    // url and content extractor
    IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ContentExtractorIMDB()),
    IMDB_TOP_SERIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json", new ContentExtractorIMDB()),
    IMDB_POPULAR_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json", new ContentExtractorIMDB()),
    IMDB_POPULAR_SERIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json",
            new ContentExtractorIMDB()),
    
    NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-09-09&end_date=2022-09-20", new ContentExtractorNASA());

    private String url;
    private ContentExtractor extractor;

    API(String url, ContentExtractor extractor) {
        this.url = url;
        this.extractor = extractor;
    }

    public String getUrl() {
        return url;
    }

    public ContentExtractor getExtractor() {
        return extractor;
    }
}
