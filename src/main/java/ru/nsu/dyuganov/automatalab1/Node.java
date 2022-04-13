package ru.nsu.dyuganov.automatalab1;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;

public class Node {
    @Getter
    public Deque<Character> stack = new ArrayDeque<>();
    @Getter
    public Deque<Character> word;

    public Node(String word, Character s) {
        this.stack.addLast(s);
        this.word = str2stack(word);
    }

    public Node(Deque<Character> word, Deque<Character> stack) {
        this.stack = stack;
        this.word = word;
    }

    public Deque<Character> getStack() {
        return stack;
    }

    public Deque<Character> getWord() {
        return word;
    }

    public Deque<Character> str2stack(String str) {
        Deque<Character> newStack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            newStack.addLast(c);
        }
        return newStack;
    }

    public String deq2str(Deque<Character> deque) {
        Iterator i = deque.iterator();
        String str = new String();
        while (i.hasNext()) {
            str += i.next();
        }
        return str;
    }

    public boolean correct(HashSet<Character> N) {
        int is = 0;
        int iw = word.size();
        for (Character character : stack) {
            if (!N.contains(character) && !word.contains(character)) {
                return false;
            }
            if (!N.contains(character)) {
                ++is;
            }
        }
        return (is <= iw);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return stack.equals(node.stack) && word.equals(node.word);
    }

    @Override
    public String toString() {
        return "(" +
                "word = " + deq2str(word) +
                ", stack = " + deq2str(stack) +
                ')';
    }

}
