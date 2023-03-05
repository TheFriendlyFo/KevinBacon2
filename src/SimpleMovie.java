import java.util.ArrayList;
import java.util.Arrays;

public final class SimpleMovie implements Comparable {
    private final String title;
    private final ArrayList<String> cast;

    public SimpleMovie(String title, String cast) {
        this.title = title;
        this.cast = new ArrayList<>(Arrays.asList(cast.split(":")));
    }

    @Override
    public int compareTo(Comparable compare, int sortType) {
        SimpleMovie movie = (SimpleMovie) compare;
        return title.compareTo(movie.title);
    }

    public String title() {
        return title;
    }

    public ArrayList<String> cast() {
        return cast;
    }
}