package ru.nsu.dyuganov.automatalab1;

import com.google.common.collect.Multimap;
import java.util.*;

public class Tree {
    private HashSet<Character> N ;
    private HashSet<Character> T;
    private static Multimap<Character, char[]> P;
    private Character S;
    private HashSet<Node> treeLevel;
    private boolean result;

    public Tree(HashSet<Character> n, HashSet<Character> t, Multimap<Character, char[]> p, Character s) {
        N = n;
        T = t;
        P = p;
        S = s;
        treeLevel = new HashSet<>();
    }

    private void makeStep() {
        HashSet<Node> newTreeLevel = new HashSet<>();
        for (Node node : treeLevel) {
            boolean flag = false;
            Deque<Character> word = new ArrayDeque<>(node.getWord());
            Deque<Character> stack  = new ArrayDeque<>(node.getStack());

            while (!word.isEmpty() && !stack.isEmpty() && word.peekFirst() == stack.peekFirst()) {
                word.removeFirst();
                stack.removeFirst();
                flag = true;
            }
            if (flag) {
                Node newTreeNode = new Node(word,stack);
                if (node.correct(N)) {
                    newTreeLevel.add(newTreeNode);
                }
                continue;
            }

            if (P.containsKey(stack.peekFirst())) {
                List<char[]> list = (List<char[]>) P.get(stack.peekFirst());
                for (char[] chars : list) {
                    Deque<Character> newStack = new ArrayDeque<>(stack);
                    newStack.removeFirst();
                    if (chars.length == 1 && chars[0] == 'e') {
                        Node newTreeNode = new Node(word, newStack);
                        newTreeLevel.add(newTreeNode);
                        continue;
                    }
                    for (int i = chars.length - 1; i >= 0; i--) {
                        newStack.addFirst(chars[i]);
                    }
                    Node newTreeNode = new Node(word, newStack);
                    if (node.correct(N)) {
                        newTreeLevel.add(newTreeNode);
                    }
                }
            }
        }
        treeLevel = newTreeLevel;
    }

    private boolean checkTreeLevel() {
        boolean flag = false;
        if (treeLevel.isEmpty()) {
            result = false;
            return true;
        }
        for (Node treeNode : treeLevel) {
            if (treeNode.getWord().isEmpty() && treeNode.getStack().isEmpty()) {
                flag = true;
                result = true;
                break;
            }
        }
        return flag;
    }

/*    private void printTreeLevel() {
        System.out.println("\n____________________________________________");
        for (Node treeNode:treeLevel) {
            System.out.println(treeNode);
        }
    }*/

    public boolean analyzeSyntax(String str) {
        treeLevel.add(new Node(str,this.S));
        while (!checkTreeLevel()) {
            makeStep();
        }
        return result;
    }
}
