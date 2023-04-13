package MyBook;

import java.io.*;
import java.util.*;

public class Book {

    private ArrayList<File> pagesList;
    private ArrayList<String> exclude_words;

    Book(ArrayList<File> pagesList, ArrayList<String> exclude_words) throws IOException {
        this.pagesList = pagesList;
        this.exclude_words = exclude_words;
    }
    Book(){}
    public Map<String, List<Integer>> readPages() throws IOException {

        Map<String, List<Integer>> occurences = new TreeMap<>();
        int pageNo = 1;
        for (int i = 0; i < pagesList.size(); i++) {

            File page = pagesList.get(i);
            Scanner sc = new Scanner(page);

            while (sc.hasNextLine()) {
                String[] words = sc.nextLine().split("\\W+");

                for (String word : words) {
                    boolean flag = true;
                    word = word.toLowerCase();
                    for(int j=0;j<word.length();j++)
                    {
                        char ch = word.charAt(j);
                        if(ch <= '9' && ch >= '1')
                        {
                            flag = false;
                        }
                    }

                    if((!exclude_words.contains(word)) && flag==true)
                    {

                        List<Integer> list = occurences.get(word);

                        if (list == null) {
                            list = new ArrayList<>();
                            occurences.put(word, list);
                        }

                        if(!list.contains(pageNo))
                        {
                            list.add(pageNo);
                        }
                    }
                }

            }
            pageNo++;
        }
        return occurences;
    }

    public void wordPageIndexFile(Map<String, List<Integer>> pageIndexList) throws IOException {

        FileWriter writer = new FileWriter("D:\\Study_work\\Projects\\wordsOfBook\\src\\MyBook\\WordPageIndex.txt");
        writer.write("Word : Page Numbers"+"\n"+"-------------------"+"\n");
        for(Map.Entry<String, List<Integer>> entry : pageIndexList.entrySet())
        {
            writer.write(entry.getKey() +" :"+ entry.getValue()+"\n");
        }
        writer.close();
    }
}
