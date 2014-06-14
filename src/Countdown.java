import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Countdown {

    List<Character> letters = new LinkedList<Character>();
    String regex = "";

    public Countdown(String sletters) throws IOException {
        //add to list

        for (char c : sletters.toLowerCase().toCharArray()) {
            letters.add(c);
            if (!regex.contains(Character.toString(c))) {
                regex += c;
            }
        }

        //build regex string
        regex = "^["+regex+"]+$";

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
    }

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

    public static void main(String[] args) throws IOException {
        //make sure we have some input
        if (args.length > 0) {

            //make sure only 9 letters
            if (args[0].length() == 9) {

                //pass into constructor
                Countdown c = new Countdown(args[0].toString());
            } else {
                System.out.println("Need nine letters.");
            }
        } else {
            System.out.println("Need some letters.");
        }
    }

}