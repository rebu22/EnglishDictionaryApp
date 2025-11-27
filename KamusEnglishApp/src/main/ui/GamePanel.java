package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.service.GameService;
import main.util.Constants;

public class GamePanel extends JPanel {
    private GameService gameService;
    private int secretNumber;
    private int attempts;
    
    public GamePanel() {
        this.gameService = new GameService();
        this.secretNumber = gameService.generateSecretNumber(1, 100);
        this.attempts = 0;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(Constants.BACKGROUND_COLOR);

        JPanel headerPanel = createHeaderPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Fakta Menarik", createFactsPanel());
        tabbedPane.addTab("Tebak Angka", createGuessGamePanel());
        
        add(headerPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(155, 89, 182));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel titleLabel = new JLabel("PERMAINAN & FAKTA MENARIK");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        
        JButton backButton = new JButton("Kembali ke Dashboard");
        backButton.setFont(Constants.BUTTON_FONT);
        backButton.setBackground(Constants.WARNING_COLOR);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window instanceof MainFrame) {
                ((MainFrame) window).showDashboard();
            }
        });
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(backButton, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private JPanel createFactsPanel() {
        JPanel factsPanel = new JPanel(new BorderLayout());
        factsPanel.setBackground(Constants.BACKGROUND_COLOR);
        factsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));
        
        JTextArea factsArea = new JTextArea();
        factsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        factsArea.setEditable(false);
        factsArea.setLineWrap(true);
        factsArea.setWrapStyleWord(true);
        factsArea.setBackground(new Color(240, 248, 255));
        factsArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Constants.PRIMARY_COLOR, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        factsArea.setText("Klik tombol di bawah untuk melihat fakta menarik!\n\n" +
                         "Fakta akan muncul secara acak setiap kali diklik.");
        
        JButton newFactButton = new JButton("Fakta Baru");
        newFactButton.setFont(Constants.BUTTON_FONT);
        newFactButton.setBackground(Constants.PRIMARY_COLOR);
        newFactButton.setForeground(Color.WHITE);
        newFactButton.setFocusPainted(false);
        newFactButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        newFactButton.addActionListener(e -> {
            String fact = gameService.getRandomFact();
            factsArea.setText("📚 FAKTA MENARIK:\n\n" + fact + 
                            "\n\n🔁 Klik tombol 'Fakta Baru' untuk fakta lainnya!");
        });
        
        factsPanel.add(new JScrollPane(factsArea), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Constants.BACKGROUND_COLOR);
        buttonPanel.add(newFactButton);
        
        factsPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return factsPanel;
    }
    
    private JPanel createGuessGamePanel() {
        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.setBackground(Constants.BACKGROUND_COLOR);
        gamePanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));

        JTextArea instructionArea = new JTextArea();
        instructionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        instructionArea.setEditable(false);
        instructionArea.setLineWrap(true);
        instructionArea.setWrapStyleWord(true);
        instructionArea.setBackground(new Color(255, 255, 240));
        instructionArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Constants.WARNING_COLOR, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        instructionArea.setText(
            "🎯 PERMAINAN TEBAK ANGKA\n\n" +
            "Saya telah memilih angka antara 1 sampai 100.\n" +
            "Coba tebak angka tersebut!\n\n" +
            "Petunjuk:\n" +
            "• Anda akan mendapatkan petunjuk 'terlalu tinggi' atau 'terlalu rendah'\n" +
            "• Coba tebak dengan sesedikit mungkin percobaan!\n" +
            "• Tekan 'Game Baru' untuk memulai ulang"
        );

        JPanel gameArea = new JPanel(new GridBagLayout());
        gameArea.setBackground(Constants.BACKGROUND_COLOR);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel guessLabel = new JLabel("Masukkan tebakan (1-100):");
        guessLabel.setFont(Constants.HEADER_FONT);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gameArea.add(guessLabel, gbc);
        
        JTextField guessField = new JTextField();
        guessField.setFont(new Font("Arial", Font.BOLD, 18));
        guessField.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        gameArea.add(guessField, gbc);
        
        JButton guessButton = new JButton("Tebak!");
        guessButton.setFont(Constants.BUTTON_FONT);
        guessButton.setBackground(Constants.SUCCESS_COLOR);
        guessButton.setForeground(Color.WHITE);
        guessButton.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        gameArea.add(guessButton, gbc);
        
        JButton newGameButton = new JButton("Game Baru");
        newGameButton.setFont(Constants.BUTTON_FONT);
        newGameButton.setBackground(Constants.ACCENT_COLOR);
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setFocusPainted(false);
        gbc.gridx = 1; gbc.gridy = 2;
        gameArea.add(newGameButton, gbc);
        
        JTextArea resultArea = new JTextArea();
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setBackground(Color.WHITE);
        resultArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        resultArea.setText("Status: Belum ada tebakan. Percobaan: 0");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gameArea.add(new JScrollPane(resultArea), gbc);

        guessButton.addActionListener(e -> {
            try {
                int guess = Integer.parseInt(guessField.getText());
                if (guess < 1 || guess > 100) {
                    resultArea.setText("Error: Masukkan angka antara 1-100!");
                    return;
                }
                
                attempts++;
                boolean isCorrect = gameService.checkGuess(secretNumber, guess);
                
                if (isCorrect) {
                    resultArea.setText(String.format(
                        "🎉 SELAMAT! Anda benar!\n" +
                        "Angka yang dicari: %d\n" +
                        "Jumlah percobaan: %d\n\n" +
                        "Tekan 'Game Baru' untuk bermain lagi!",
                        secretNumber, attempts
                    ));
                    guessButton.setEnabled(false);
                } else {
                    String hint = gameService.getGuessHint(secretNumber, guess);
                    resultArea.setText(String.format(
                        "Tebakan: %d\n%s\n" +
                        "Percobaan ke: %d\n\n" +
                        "Coba lagi!",
                        guess, hint, attempts
                    ));
                }
                
                guessField.setText("");
                guessField.requestFocus();
                
            } catch (NumberFormatException ex) {
                resultArea.setText("Error: Masukkan angka yang valid!");
            }
        });
        
        newGameButton.addActionListener(e -> {
            secretNumber = gameService.generateSecretNumber(1, 100);
            attempts = 0;
            guessField.setText("");
            resultArea.setText("Game baru dimulai! Tebak angka antara 1-100.\nPercobaan: 0");
            guessButton.setEnabled(true);
            guessField.requestFocus();
        });

        guessField.addActionListener(e -> guessButton.doClick());
        
        gamePanel.add(instructionArea, BorderLayout.NORTH);
        gamePanel.add(gameArea, BorderLayout.CENTER);
        
        return gamePanel;
    }
}