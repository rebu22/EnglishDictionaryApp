package main.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
    
    public static void playSound(String filePath) {
        new Thread(() -> {
            try {
                File soundFile = new File(filePath);
                if (soundFile.exists()) {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } else {

                    System.out.println("Sound file not found: " + filePath);
                }
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println("Error playing sound: " + e.getMessage());
            }
        }).start();
    }
}