package pa1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MarkovModel {

    private int k; // order of model
    private String s; // string sequence
    private HashSet<Character> charSet;
    private int S;
    private HashMap<String, Integer> table;

    public MarkovModel(int k, String s) {

        this.k = k;
        this.s = s;
        this.table = new HashMap<String, Integer>();
        this.charSet = new HashSet<Character>();
        // find the size of the alphabet
        for (int i = 0; i < s.length(); i++) {
            this.charSet.add(s.charAt(i));
        }
        this.S = charSet.size();
        // k to k+1 length
        // "aabcabaacaac"
        k--;

        for (int i = 0; i < s.length(); i++) {
            // at beginning, get substring of k length behind
            if (i == 0) {
                StringBuilder output = new StringBuilder(s.substring(s.length() - k+1, s.length())).reverse();

                System.out.println(s.substring(0,1)+ output.toString());
                if (s.length()-k+1 < s.length()) {
                    table.put(s.substring(0,1)+ s.substring(s.length() - k+1, s.length()),1);

                }
               
            } 
            // not end or beginning of string
            if (k + i < s.length() && table.containsKey(s.substring(i, k + i))) {
                System.out.println(s.substring(i, k + i));
                table.put(s.substring(i, k + i), table.get(s.substring(i, k + i)) + 1);
            } else if (k + i < s.length()) {
                System.out.println(s.substring(i, k + i));
                table.put(s.substring(i, k + i), 1);
            } 
            // go to the beginning if over length
            if (i + k > s.length()) {
                String str = s.substring(i) + s.substring(0, k-s.substring(i).length());
                if (table.containsKey(str)) {
                    
                    System.out.println(str);
                    table.put(str,table.get(str)+1);
                } else {

                    
                    System.out.println(str);
                    table.put(str, 1);
                }
            } 
        }
        System.out.println(table.toString());

    }

    public String toString() {
        return charSet.toString();
    }

    // public int getK(){
    // return this.k;

    // }
    public int getS() {
        return this.S;

    }

    // public double laplace(String s){

    // }

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String s = "";

        try {
            Scanner input = new Scanner(new File(args[1]));
            while (input.hasNextLine()) {
                s = input.nextLine();
            }
            // System.out.print(s);

        } catch (FileNotFoundException x) {
            System.out.println(x);
        }

        MarkovModel model = new MarkovModel(k, s);
        System.out.println(model.getS());

    }

}
