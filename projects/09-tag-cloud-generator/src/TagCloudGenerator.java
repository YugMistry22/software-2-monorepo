import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 *
 *
 *
 * @author Yug Mistry and Aarush Kurupati
 *
 */
public final class TagCloudGenerator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGenerator() {
    }

    /**
     * The Separators.
     */
    private static final String SEPARATORS = " \t\n\r,-.!?[]';:/()_";

    /**
     * Returns a comparator that orders Map.Pair<String,Integer> by descending
     * order.
     *
     * @return comparator for descending count
     */

    private static final class DescendCompare
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> p1,
                Map.Pair<String, Integer> p2) {
            return p2.value().compareTo(p1.value());
        }
    }

    /**
     * Returns a comparator that orders Map.Pair<String,Integer> by alphabetical
     * ordering.
     *
     * @return comparator for alphabetical order
     */
    private static final class AlphabeticalCompare
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> p1,
                Map.Pair<String, Integer> p2) {
            return p1.key().toLowerCase().compareTo(p2.key().toLowerCase());
        }
    }

    /**
     * returns the first word or the separator string.
     *
     * @param text
     *            the input string
     * @param position
     *            starting position
     * @return the first word or the separator
     *
     * @requires 0 <= position < the length of the text
     *
     */
    public static String nextWordOrSeparator(String text, int position) {
        boolean sep = SEPARATORS.indexOf(text.charAt(position)) >= 0;
        int secondIndex = position + 1;
        while (secondIndex < text.length() && ((SEPARATORS
                .indexOf(text.charAt(secondIndex)) >= 0) == sep)) {
            secondIndex++;
        }
        return text.substring(position, secondIndex);
    }

    /**
     * Returns true if the character is a separator.
     *
     * @param c
     *            character to check
     * @return true if c is a separator
     * @ensures isSeparator is equal to if c is a separator
     */
    private static boolean isSeparator(char c) {
        return SEPARATORS.indexOf(c) >= 0;
    }

    /**
     * Counts the frequency of each word in the input file.
     *
     * @param in
     *            the input file reader
     * @param wordCount
     *            the map from word to count
     * @replaces wordCount
     * @requires in.isOpen
     * @ensures wordCount = the map of counts for each lowercase word in the
     *          file
     */
    private static void countWords(SimpleReader in,
            Map<String, Integer> wordCount) {
        wordCount.clear();
        while (!in.atEOS()) {
            String line = in.nextLine();
            int pos = 0;
            while (pos < line.length()) {
                String token = nextWordOrSeparator(line, pos);
                if (token.length() > 0 && !isSeparator(token.charAt(0))) {
                    String word = token.toLowerCase();
                    if (wordCount.hasKey(word)) {
                        wordCount.replaceValue(word, wordCount.value(word) + 1);
                    } else {
                        wordCount.add(word, 1);
                    }
                }
                pos += token.length();
            }
        }
    }

    /**
     * Gets the font size value sand stores each word and size in wordSize as a
     * Queue and sorted alphabetically.
     *
     * @param wordCount
     *            the given map
     * @param wordSize
     *            the given queue
     * @param n
     *            the number of words
     * @replaces wordSize
     * @requires n > 0
     */
    private static void getSize(Map<String, Integer> wordCount,
            Queue<Map.Pair<String, Integer>> wordSize, int n) {

        SortingMachine<Map.Pair<String, Integer>> sort = new SortingMachine1L<>(
                new DescendCompare());
        for (Map.Pair<String, Integer> p : wordCount) {
            sort.add(p);
        }
        sort.changeToExtractionMode();

        Map<String, Integer> topMap = wordCount.newInstance();
        int count = 0;
        int max = 0;
        int min = Integer.MAX_VALUE;

        while (sort.size() > 0 && count < n) {
            Map.Pair<String, Integer> p = sort.removeFirst();
            topMap.add(p.key(), p.value());
            if (p.value() > max) {
                max = p.value();
            }
            if (p.value() < min) {
                min = p.value();
            }
            count++;
        }

        double scale = 0;
        if (max == min) {
            scale = 1.0;
        } else {
            scale = 100.0 / (max - min);
        }

        Map<String, Integer> sizedMap = topMap.newInstance();
        for (Map.Pair<String, Integer> p : topMap) {
            int size = 0;
            if (max == min) {
                //arbitrary value
                size = 20;
            } else {
                size = (int) ((p.value() - min) * scale) + 10;
            }
            sizedMap.add(p.key(), size);
        }

        sort = new SortingMachine1L<>(new AlphabeticalCompare());
        for (Map.Pair<String, Integer> p : sizedMap) {
            sort.add(p);
        }
        sort.changeToExtractionMode();

        wordSize.clear();
        while (sort.size() > 0) {
            wordSize.enqueue(sort.removeFirst());
        }
    }

    /**
     * Generates a HTML page which contains the tag cloud with the inputs
     *
     * @param inputFileName
     *            the given input file name
     * @param out
     *            the output file
     * @param wordCount
     *            the given map
     * @param wordSize
     *
     */
    private static void outputHTML(String inputFileName, SimpleWriter out,
            Map<String, Integer> wordCount,
            Queue<Map.Pair<String, Integer>> wordSize, int n) {

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
        for (Map.Pair<String, Integer> p : wordSize) {
            out.println("<span style=\"cursor:default\" class=\"f" + p.value()
                    + "\">" + p.key() + "</span> ");
        }
        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command-line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the input file: ");
        String inputFileName = in.nextLine();
        out.print("Enter the output file: ");
        String outputFileName = in.nextLine();
        out.print("Enter the number of words: ");
        int n = in.nextInteger();

        SimpleReader inputFile = new SimpleReader1L(inputFileName);
        SimpleWriter outputFile = new SimpleWriter1L(outputFileName);

        Map<String, Integer> wordCount = new Map1L<>();
        countWords(inputFile, wordCount);

        Queue<Map.Pair<String, Integer>> wordSize = new Queue1L<>();
        getSize(wordCount, wordSize, n);

        outputHTML(inputFileName, outputFile, wordCount, wordSize, n);

        inputFile.close();
        outputFile.close();
        in.close();
        out.close();
    }
}