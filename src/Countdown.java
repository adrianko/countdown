import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Countdown {

    public static void main(String[] args) throws IOException {
        List<Character> letters = new LinkedList <Character>();
        for (char l : args[0].toCharArray()) {
            letters.add(l);
        }
        List<String> lines = readFile("words.txt");
        System.out.println(lines.get(5));
    }


    public static List<String> readFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        return lines;
    }

}