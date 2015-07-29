package net.kapouet.project.euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pepitooo on 29/07/2015.
 */
public class Problem42 {

    /**
     * The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:
     *
     * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
     *
     * By converting each letter in a word to a number corresponding to its alphabetical position and adding these values
     * we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number
     * then we shall call the word a triangle word.
     *
     * Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common
     * English words, how many are triangle words?
     */

    private static final String WORD_REGEXP = "[- .:,\"]+";

    public long getNmbeTriangleWord(List<String> names) {
        long total = 0;

        List<Long> wordsValue =
                names.parallelStream()
                        .map(name -> getNameValue(name))
                        .collect(Collectors.toList());

        long maxWordValue =
                wordsValue.parallelStream()
                        .max(Comparator.comparing(item -> item.longValue()))
                        .get();

        List<Long> triangleNumbers = getTriangleNumbersUpTo(maxWordValue);

        return names.stream()
                .filter(name -> triangleNumbers.contains(getNameValue(name)))
                .count();
    }

    protected List<Long> getTriangleNumbersUpTo(long maxWordValue) {
        List<Long> triangleNumbers = new ArrayList<>();
        int number = 1;
        long lastTriangleNumber = 1;
        while (!(maxWordValue <= lastTriangleNumber)) {
            lastTriangleNumber = getTriangleNumberOf(number++);
            triangleNumbers.add(lastTriangleNumber);
        }
        return triangleNumbers;
    }

    public List<String> loadFile() throws IOException, URISyntaxException {
        return readNamesFromFile(Paths.get("words.txt"));
    }

    public List<String> readNamesFromFile(Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            return reader.lines()
                    .flatMap(names -> Stream.of(names.split(WORD_REGEXP)))
                    .sorted(Comparator.<String>naturalOrder())
                    .collect(Collectors.toList());
        }
    }

    public static long getNameValue(String name) {
        long nameValue = 0;
        for (char c : name.toUpperCase().toCharArray()) {
            nameValue += 1 + c - 'A';
        }
        return nameValue;
    }

    public static long getTriangleNumberOf(long number) {
        return number * (number + 1) / 2;
    }

    /**
     * Main entry point for application
     *
     * @param args the command line arguments
     * @throws IOException If file access does not work
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        Problem42 pb42 = new Problem42();
        List<String> names = pb42.loadFile();

        long total = pb42.getNmbeTriangleWord(names);

        System.out.println("The total triangle words is: " + total);
    }
}
