package main.service;

import main.model.CorruptOfficer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CorruptionService {
    private List<CorruptOfficer> corruptOfficers;
    private Random random;
    
    public CorruptionService() {
        this.corruptOfficers = new ArrayList<>();
        this.random = new Random();
        initializeCorruptOfficers();
    }
    
    private void initializeCorruptOfficers() {
        corruptOfficers.add(new CorruptOfficer(
            "Setya Novanto", 
            "Ketua DPR RI", 
            "Kasus Korupsi E-KTP - Rp 2,3 Triliun", 
            "src/resources/images/corruptors/setya_novanto.png"
        ));
        
        corruptOfficers.add(new CorruptOfficer(
            "Anas Urbaningrum", 
            "Ketua Umum Partai Demokrat", 
            "Kasus Korupsi Hambalang - Rp 463 Miliar", 
            "src/resources/images/corruptors/anas_urbaningrum.png"
        ));
        
        corruptOfficers.add(new CorruptOfficer(
            "Nazaruddin", 
            "Bendahara Partai Demokrat", 
            "Kasus Korupsi Wisma Atlet - Rp 191 Miliar", 
            "src/resources/images/corruptors/nazaruddin.png"
        ));
        
        corruptOfficers.add(new CorruptOfficer(
            "Joko Soegiarto Tjandra", 
            "Pengusaha", 
            "Kasus Korupsi BLBI - Rp 546 Miliar", 
            "src/resources/images/corruptors/default_corruptor.png"
        ));
        
        corruptOfficers.add(new CorruptOfficer(
            "Sugiharto", 
            "Dirjen Pajak", 
            "Kasus Mafia Pajak - Rp 100 Miliar", 
            "src/resources/images/corruptors/default_corruptor.png"
        ));
    }
    
    public CorruptOfficer getRandomCorruptOfficer() {
        int index = random.nextInt(corruptOfficers.size());
        return corruptOfficers.get(index);
    }

    public CorruptOfficer getCorruptOfficerForWord(String word) {
        String lowerWord = word.toLowerCase();

        if (lowerWord.contains("uang") || lowerWord.contains("money") || lowerWord.contains("cash") || lowerWord.contains("dollar")) {
            return corruptOfficers.get(0); 
        } else if (lowerWord.contains("proyek") || lowerWord.contains("project") || lowerWord.contains("bangun") || lowerWord.contains("konstruksi")) {
            return corruptOfficers.get(1);
        } else if (lowerWord.contains("atlet") || lowerWord.contains("sport") || lowerWord.contains("wisma") || lowerWord.contains("atlet")) {
            return corruptOfficers.get(2);
        } else if (lowerWord.contains("bank") || lowerWord.contains("bailout") || lowerWord.contains("blbi") || lowerWord.contains("kredit")) {
            return corruptOfficers.get(3);
        } else if (lowerWord.contains("pajak") || lowerWord.contains("tax") || lowerWord.contains("mafia") || lowerWord.contains("fiskal")) {
            return corruptOfficers.get(4);
        } else if (lowerWord.contains("korupsi") || lowerWord.contains("corruption") || lowerWord.contains("suap") || lowerWord.contains("bribe")) {

            return getRandomCorruptOfficer();
        }
        
        return null;
    }

    public boolean isCorruptionTrigger(String word) {
        return getCorruptOfficerForWord(word) != null;
    }
    
    public List<CorruptOfficer> getAllCorruptOfficers() {
        return new ArrayList<>(corruptOfficers);
    }
}