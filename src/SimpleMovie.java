
public record SimpleMovie(String title, String cast) implements Comparable {

    @Override
    public int compareTo(Comparable compare, int sortType) {
        SimpleMovie movie = (SimpleMovie) compare;
        return title.compareTo(movie.title);
    }
}