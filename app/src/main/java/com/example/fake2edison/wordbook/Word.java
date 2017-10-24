package com.example.fake2edison.wordbook;

/**
 * Created by fake2edison on 2017/10/17.
 */

public class Word {
    private String listWord1;
    private String listWord2;
    public Word(String word1,String word2){
        listWord1 = word1;
        listWord2 = word2;
    }
    public String getListWord1(){
        return listWord1;
    }
    public String getListWord2(){
        return listWord2;
    }
}
