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

    public Countdown(String sletters) throws IOException{
        for (char l : sletters.toCharArray()) {
            letters.add(l);
        }

        List<String> lines = readFile("words.txt");
        Map<Integer, ArrayList<String>> lengths = new HashMap<Integer, ArrayList<String>>();
        for (String w : lines) {
            int length = w.length();
            if (!lengths.containsKey(length)) {
                lengths.put(length, new ArrayList<String>());
            }
            lengths.get(length).add(w);
        }
    }

    public List<String> readFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        return lines;
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            if (args[0].length() == 9) {
                Countdown c = new Countdown(args[0]);
            } else {
                System.out.println("Need nine letters.");
            }
        } else {
            System.out.println("Need some letters.");
        }
    }

}