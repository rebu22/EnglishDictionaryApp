package main.service;

import java.util.Random;

public class GameService {
    private Random random;
    
    public GameService() {
        this.random = new Random();
    }
    
    public String getRandomFact() {
        String[] facts = {
            "Kucing bisa membuat lebih dari 100 suara vokal yang berbeda",
            "Indonesia adalah negara kepulauan terbesar di dunia dengan 17.504 pulau",
            "Otak manusia terdiri dari sekitar 75% air",
            "Lebah madu harus mengunjungi 2 juta bunga untuk membuat 500 gram madu",
            "Pohon tertua di dunia berusia lebih dari 4.800 tahun"
        };
        
        return facts[random.nextInt(facts.length)];
    }
    
    public boolean checkGuess(int secretNumber, int guess) {
        return secretNumber == guess;
    }
    
    public int generateSecretNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
    
    public String getGuessHint(int secretNumber, int guess) {
        if (guess < secretNumber) {
            return "Terlalu rendah! Coba angka yang lebih besar.";
        } else {
            return "Terlalu tinggi! Coba angka yang lebih kecil.";
        }
    }
}