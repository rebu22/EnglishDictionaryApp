package main.service;

import main.model.Dictionary;
import main.model.Word;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Locale;

public class DictionaryService {
    private Dictionary dictionary;
    private static ResourceBundle bundle;
    
    static {
        try {
            bundle = ResourceBundle.getBundle("messages", new Locale("id", "ID"));
        } catch (Exception e) {
            System.err.println("Resource bundle not found, using default messages");
        }
    }
    
    public DictionaryService() {
        this.dictionary = new Dictionary();
    }

    public static String getString(String key) {
        try {
            return bundle.getString(key);
        } catch (Exception e) {

            return null;
        }
    }

    public static void setLanguage(Locale locale) {
        try {
            bundle = ResourceBundle.getBundle("messages", locale);
        } catch (Exception e) {
            System.err.println("Failed to change language: " + e.getMessage());
        }
    }
    
    public List<Word> searchWord(String keyword) {
        return dictionary.searchWord(keyword);
    }
    
    public List<Word> getAllWords() {
        return dictionary.getAllWords();
    }
    
    public String getWordDefinition(String englishWord) {
        List<Word> results = dictionary.searchWord(englishWord);
        if (!results.isEmpty()) {
            Word word = results.get(0);
            return word.getIndonesian() + " (" + word.getCategory() + ")";
        }
        return "Kata tidak ditemukan";
    }
}