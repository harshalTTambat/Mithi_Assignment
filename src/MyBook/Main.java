package MyBook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File f1 = new File("D:\\Study_work\\Projects\\wordsOfBook\\src\\MyBook\\Page1.txt");
        File f2 = new File("D:\\Study_work\\Projects\\wordsOfBook\\src\\MyBook\\Page2.txt");
        File f3 = new File("D:\\Study_work\\Projects\\wordsOfBook\\src\\MyBook\\Page3.txt");
        File ex = new File("D:\\Study_work\\Projects\\wordsOfBook\\src\\MyBook\\exclude-words.txt");

        ArrayList <File> pagesList = new ArrayList<>();
        pagesList.add(f1);
        pagesList.add(f2);
        pagesList.add(f3);
        ArrayList <String> exclude_words = new ArrayList<>();
        Scanner sc = new Scanner(ex);
        while (sc.hasNextLine())
        {
            exclude_words.add(sc.nextLine());
        }

        Book book = new Book(pagesList,exclude_words);
        Map<String, List<Integer>> map = book.readPages();

        book.wordPageIndexFile(map);

    }
}