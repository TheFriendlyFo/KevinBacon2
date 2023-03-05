import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SixDegreesToKevinBacon {
    private final ArrayList<SimpleMovie> movies;
    private final HashMap<String, ArrayList<SimpleMovie>> linkedActors;
    private final Scanner scanner;

    public SixDegreesToKevinBacon() {
        this.movies = MovieDatabaseBuilder.getMovieDB("movie_data");
        scanner = new Scanner(System.in);
        linkedActors = linkActors();
    }

    private HashMap<String, ArrayList<SimpleMovie>> linkActors() {
        HashMap<String, ArrayList<SimpleMovie>> linkedActors = new HashMap<>();

        for (SimpleMovie movie : movies) {
            for (String actor : movie.cast()) {
                if (linkedActors.containsKey(actor)) {
                    linkedActors.get(actor).add(movie);
                } else {
                    linkedActors.put(actor, new ArrayList<>(List.of(movie)));
                }
            }
        }

        return linkedActors;
    }

    private String getCastMember() {
        System.out.print("Enter an actor's name: ");
        String searchTerm = scanner.nextLine().toLowerCase();

        ArrayList<String> actors = new ArrayList<>();

        for (String actor : linkedActors.keySet()) {
            if (actor.toLowerCase().contains(searchTerm) && !actors.contains(actor)) {
                actors.add(actor);
            }
        }

        QuickSort.sortStr(actors);

        for (int i = 0; i < actors.size(); i++) {
            System.out.printf("%s: %s\n", i + 1, actors.get(i));
        }

        System.out.println("Select an actor:");
        System.out.print("> ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        return actors.get(choice - 1);
    }

    public void runTheBaconater() {
        String actor = getCastMember();

        for (int i = 0; i < 6; i++) {
            if (runTheBaconater(actor, 0, i)) {
                scanner.nextLine();
                return;
            }
        }

        System.out.println("Baconater failed!");
        scanner.nextLine();
    }

    private boolean runTheBaconater(String actor, int depth, int maxDepth) {
        if (depth == maxDepth) return false;

        for (SimpleMovie movie : linkedActors.get(actor)) {

            if (movie.cast().contains("Kevin Bacon")) {
                System.out.printf("\nAfter linking %s actors, Kevin Bacon was found!\n", depth + 1);
                System.out.printf("In %s he shared a role with %s%s\n", movie.title(), actor, depth == 0 ? "!\n" : "...");
                return true;
            }

            for (String newActor : movie.cast()) {
                if (newActor.equals(actor)) continue;
                if (!runTheBaconater(newActor, depth + 1, maxDepth)) continue;

                System.out.printf("And %s shared a role with %s in %s%s\n", newActor, actor, movie.title(), depth == 0 ? "!\n" : "...");
                return true;
            }
        }

        return false;
    }
}
