package ru.nsu.dyuganov.automatalab1;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static HashSet<Character> N = new HashSet<Character>();
    private static HashSet<Character> T = new HashSet<Character>();
    private static Multimap<Character, char[]> P = ArrayListMultimap.create();
    private static Character S = null;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main (String[] args) {
        System.out.println("Enter (context free) grammar:");

        Parser parser = new Parser();
        parser.parseN(N);
        parser.parseT(N, T);
        parser.parseP(N, T, P);
        S = parser.parseS(N, P);

        Tree storage = new Tree(N,T,P,S);

        System.out.println("\nEnter a string to recognize from the characters of the set ∑");
        String innerStr;
        while (true) {
            boolean flag = false;
            innerStr = scanner.nextLine();
            for (char c:innerStr.toCharArray()) {
                if (!T.contains(c)) {
                    System.out.println("∑ does not contain the specified character");
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
        boolean result = storage.analyzeSyntax(innerStr);
        System.out.println(result ? "yes" : "no");
    }
}
