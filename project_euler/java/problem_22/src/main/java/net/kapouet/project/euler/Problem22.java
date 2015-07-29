package net.kapouet.project.euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pepitooo on 28/07/2015.
 */
public class Problem22 {

    /**
     * <p>
     * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names,
     * begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply
     * this value by its alphabetical position in the list to obtain a name score.
     * <p>
     * For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53,
     * is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.
     * <p>
     * What is the total of all the name scores in the file?
     */

    private static final String WORD_REGEXP = "[- .:,\"]+";


    public long getNamesScore(List<String> names) {
        long total = 0;
        long position = 0;

        for (String name : names) {
            total += getNameValue(name) * position;
            position++;
        }
        return total;
    }

    public List<String> loadFile() throws IOException, URISyntaxException {
        return readNamesFromFile(Paths.get("names.txt"));
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

    /**
     * Main entry point for application
     *
     * @param args the command line arguments
     * @throws IOException If file access does not work
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        Problem22 pb22 = new Problem22();
        List<String> names = pb22.loadFile();
        long total = pb22.getNamesScore(names);

        System.out.println("The total of all the name scores in the file is: " + total);
    }
}

