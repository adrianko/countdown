import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Countdown {

    /**
     * Count occurrence of each character in input
     */
    Map<Character, Integer> count = new HashMap<Character, Integer>();

    /**
     * For selecting the possible words
     */
    String regex = "";

    /**
     * The generated list of possible words
     */
    List<String> possibles = new ArrayList<String>();

    /**
     * Main code body
     * @param letters the string of characters
     * @throws IOException
     */
    public Countdown(String letters) throws IOException {
        //add to list
        for (char c : letters.toLowerCase().toCharArray()) {

            //if not in regex add
            if (!regex.contains(Character.toString(c))) {
                regex += c;
            }

            //if char not in map
            if (!count.containsKey(c)) {
                count.put(c, 0);
            }

            //increase count of char
            count.put(c, count.get(c) + 1);
        }

        //build regex string
        regex = "^[" + regex + "]+$";

        //read in word list
        List<String> lines = readFile("words.txt");
        Map<Integer, ArrayList<String>> lengths = new HashMap<Integer, ArrayList<String>>();
        for (String w : lines) {

            //if only contains letters in char list then add to word list
            if (w.matches(regex)) {
                //word length
                int length = w.length();
                //check if word length as key exists
                if (!lengths.containsKey(length)) {
                    lengths.put(length, new ArrayList<String>());
                }
                lengths.get(length).add(w);
            }
        }

        //each world length
        for (int i = 9; i > 0; i--) {

            //new list of words working on
            List<String> words = lengths.get(i);

            //escape for empty list
            if (words.isEmpty()) {
                continue;
            }
            //for each word in this set of lengths
            for (String w : words) {

                //map for characters of word
                Map<Character, Integer> char_count = new HashMap<Character, Integer>();
                //count number of occurrences of each character
                for (char c : w.toCharArray()) {

                    //if doesn't contain, add and set to 0
                    if (!char_count.containsKey(c)) {
                        char_count.put(c, 0);
                    }
                    //increment count
                    char_count.put(c, char_count.get(c) + 1);
                }

                //if map matches input then allow else, remove
                boolean match = true;
                for (Map.Entry<Character, Integer> p : char_count.entrySet()) {
                    //if the character count number in the current map equals
                    // the number in the entered list then move on
                    // other wise word cannot be created from given character list
                    if (!p.getValue().equals(count.get(p.getKey()))) {
                        match = false;
                        break;
                    }
                }

                //number of each characters match so add to possibles
                if (match) {
                    possibles.add(w);
                }
            }
        }

        System.out.println(possibles);
    }

    /**
     * main
     * @param args single, string of 9 chars
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //make sure we have some input
        if (args.length > 0) {

            //make sure only 9 letters
            if (args[0].length() == 9) {

                //pass into constructor
                Countdown c = new Countdown(args[0]);
            } else {
                System.out.println("Need nine letters.");
            }
        } else {
            System.out.println("Need some letters.");
        }
    }

    /**
     * Reads in file and adds each line as entry in list
     * @param filename name of the file
     * @return List<String>
     * @throws IOException
     */
    public List<String> readFile(String filename) throws IOException {
        //file reader inside buffered reader
        BufferedReader br = new BufferedReader(new FileReader(filename));

        //line list
        List<String> lines = new ArrayList<String>();
        String line = null;

        //read in all lines
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }

        //close reader and return list of words
        br.close();
        return lines;
    }

}