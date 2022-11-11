package com.example.myproject.Domain;

public class PopularDomain {
    private String title;
    private String contents;
    private int coin;

    public PopularDomain(String title, String contents, int coin) {
        this.title = title;
        this.contents = contents;
        this.coin = coin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
