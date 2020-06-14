package com.wordlistpage.db.vo;

import java.util.List;

public class WordBean
{
    private String wordName;
    private List wordMeanList;
    private String hash;
    private int pages;

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    private String jsonData;

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public List getWordMeanList() {
        return wordMeanList;
    }

    public void setWordMeanList(List wordMeanList) {
        this.wordMeanList = wordMeanList;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}

