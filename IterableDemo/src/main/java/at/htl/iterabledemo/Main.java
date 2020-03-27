package at.htl.iterabledemo;

import java.util.List;

public class Main {

    /**
     * https://www.geeksforgeeks.org/java-implementing-iterator-and-iterable-interface/
     * @param args
     */
    public static void main(String[] args) {
        List<Movie> movies = List.of (
                new Movie("a", 10),
                new Movie("b", 20),
                new Movie("c", 30)
                );

        movies
                .stream()
                .skip(2)
                .map(movie -> movie.getTitle())
                .forEach(System.out::println);

    }

}
