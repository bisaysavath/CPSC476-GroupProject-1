package edu.fullerton;

public class Url
{
    private String shortURL; 
    private String longURL;
    private Integer clicks;

    public Url() {
        clicks = 0;
        shortURL = "";
        longURL = "";
    }

    public Url(String shortUrl, String longUrl) {
        clicks = 0;
        this.shortURL = shortURL;
        this.longURL = longURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void incrementClick() {
        clicks++;
    }
}