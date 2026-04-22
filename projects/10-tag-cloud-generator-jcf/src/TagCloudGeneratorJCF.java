import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 *
 *
 * @author Yug Mistry and Aarush Kurupati
 *
 */
public class TagCloudGeneratorJCF {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGeneratorJCF() {

    }

    private static final String SEPARATORS = " \t\n\r,-.!?[]';:/()_";

    public static String nextWordOrSeparator(String text, int position) {
        boolean sep = SEPARATORS.indexOf(text.charAt(position)) >= 0;
        int secondIndex = position + 1;
        while (secondIndex < text.length()
                && (SEPARATORS.indexOf(text.charAt(secondIndex)) >= 0) == sep) {
            secondIndex++;
        }
        return text.substring(position, secondIndex);
    }

    private static boolean isSeparator(char c) {
        return SEPARATORS.indexOf(c) >= 0;
    }

    private static void countWords(BufferedReader in,
            Map<String, Integer> wordCount) throws IOException {
        wordCount.clear();
        String line;
        while ((line = in.readLine()) != null) {
            int pos = 0;
            while (pos < line.length()) {
                String token = nextWordOrSeparator(line, pos);
                if (token.length() > 0) {
                    if (!isSeparator(token.charAt(0))) {
                        String word = token.toLowerCase();
                        if (wordCount.containsKey(word)) {
                            wordCount.put(word, wordCount.get(word) + 1);
                        } else {
                            wordCount.put(word, 1);
                        }
                    }
                }
                pos += token.length();
            }
        }
    }

    private static class DescendCompare
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> a,
                Map.Entry<String, Integer> b) {
            return b.getValue().compareTo(a.getValue());
        }
    }

    private static class AlphabeticalCompare
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> a,
                Map.Entry<String, Integer> b) {
            return a.getKey().compareToIgnoreCase(b.getKey());
        }
    }

    /**
     * Gets the font size values and stores each word and size in wordSize as a
     * List sorted alphabetically.
     *
     * @param wordCount
     *            the given map
     * @param wordSize
     *            the given list
     * @param n
     *            the number of words
     */
    private static void getSize(Map<String, Integer> wordCount,
            List<Map.Entry<String, Integer>> wordSize, int n) {

        List<Map.Entry<String, Integer>> values = new ArrayList<>(
                wordCount.entrySet());

        // Sort by descending count
        values.sort(new DescendCompare());

        // Keep only top n words
        if (values.size() > n) {
            values = new ArrayList<>(values.subList(0, n));
        }

        int max = values.get(0).getValue();
        int min = values.get(values.size() - 1).getValue();

        double scale = 0;
        if (max == min) {
            scale = 1.0;
        } else {
            scale = 100.0 / (max - min);
        }

        List<Map.Entry<String, Integer>> sizedList = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {

            Map.Entry<String, Integer> e = values.get(i);

            int size = 0;

            if (max == min) {
                size = 20;
            } else {
                size = (int) ((e.getValue() - min) * scale) + 10;
            }

            Map<String, Integer> temp = new HashMap<>();
            temp.put(e.getKey(), size);
            Map.Entry<String, Integer> newEntry = temp.entrySet().iterator()
                    .next();

            sizedList.add(newEntry);
        }

        sizedList.sort(new AlphabeticalCompare());

        wordSize.clear();
        wordSize.addAll(sizedList);
    }

    /**
     * Generates a HTML page which contains the tag cloud with the inputs
     *
     * @param inputFileName
     *            the given input file name
     * @param out
     *            the output PrintWriter
     * @param wordCount
     *            the map of word counts
     * @param wordSize
     *            the list of words with calculated sizes
     * @param n
     *            the number of top words
     */
    private static void outputHTML(String inputFileName, PrintWriter out,
            Map<String, Integer> wordCount,
            List<Map.Entry<String, Integer>> wordSize, int n) {

        out.println("<html>");
        out.println("<head>");
        out.println(
                "<title>Top " + n + " words in " + inputFileName + "</title>");
        out.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Top " + n + " words in " + inputFileName + "</h2>");
        out.println("<hr>");
        out.println("<div class=\"cdiv\">");
        out.println("<p class=\"cbox\">");

        for (Map.Entry<String, Integer> e : wordSize) {
            out.println(
                    "<span style=\"cursor:default\" class=\"f" + e.getValue()
                            + "\" title=\"count: " + wordCount.get(e.getKey())
                            + "\">" + e.getKey() + "</span> ");
        }

        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    public static void main(String[] args) {
        try (BufferedReader console = new BufferedReader(
                new java.io.InputStreamReader(System.in))) {

            System.out.println("Enter the input file: ");
            String inputFileName = console.readLine();

            System.out.println("Enter the output file: ");
            String outputFileName = console.readLine();

            System.out.println("Enter the number of words: ");
            int n = Integer.parseInt(console.readLine());

            Map<String, Integer> wordCount = new TreeMap<>();
            List<Map.Entry<String, Integer>> wordSize = new ArrayList<>();

            try (BufferedReader inputFile = new BufferedReader(
                    new FileReader(inputFileName))) {
                countWords(inputFile, wordCount);
            } catch (IOException e) {
                System.err
                        .println("Error reading input file: " + e.getMessage());
                return;
            }

            getSize(wordCount, wordSize, n);

            try (PrintWriter outputFile = new PrintWriter(
                    new BufferedWriter(new FileWriter(outputFileName)))) {
                outputHTML(inputFileName, outputFile, wordCount, wordSize, n);
            } catch (IOException e) {
                System.err.println(
                        "Error writing output file: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err
                    .println("Error reading console input: " + e.getMessage());
        }
    }
}
