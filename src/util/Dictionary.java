package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Dictionary {
    public  HashSet<String> dictionary;
    public Dictionary() throws IOException {
        dictionary = new HashSet<>();
        String path  = System.getProperty("user.dir") + "/../src/dictionary.txt";

        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;

        while ((line = reader.readLine()) != null) {
            String lowerCaseLine = line.toLowerCase();

           
            dictionary.add(lowerCaseLine);

        }

        reader.close();

    }
    public HashSet<String> getDictionary() {
        return dictionary;
    }
    public boolean contains(String word) {
        return dictionary.contains(word);
    }



}
