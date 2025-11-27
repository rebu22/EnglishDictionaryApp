package main.model;

public class Word {
    private String english;
    private String indonesian;
    private String category;
    
    public Word(String english, String indonesian, String category) {
        this.english = english;
        this.indonesian = indonesian;
        this.category = category;
    }

    public String getEnglish() { return english; }
    public String getIndonesian() { return indonesian; }
    public String getCategory() { return category; }
    
    @Override
    public String toString() {
        return english + " - " + indonesian + " (" + category + ")";
    }
}