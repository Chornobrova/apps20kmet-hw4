package ua.edu.ucu;

import ua.edu.ucu.autocomplete.PrefixMatches;
import ua.edu.ucu.tries.RWayTrie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PrefixMatches prefixMatches = new PrefixMatches(new RWayTrie());
        try {
            File words = new File("words-333333.txt");
            Scanner scan = new Scanner(words);
            scan.nextLine();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                prefixMatches.load(line.split("\t")[1]);
            }
            scan.close();
            System.out.println(prefixMatches.wordsWithPrefix("go").toString());
            System.out.println(prefixMatches.size());
        }
        catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }
}
