package main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RBTNode {
    String key;
    String value;
    RBTNode left, right, parent;
    boolean color;

    public RBTNode(String key, String value) {
        this.key = key;
        this.value = value;
        this.color = true;
        this.left = this.right = this.parent = null;
    }
}

class RBTree {
    private RBTNode root;
    private final RBTNode NIL;

    public RBTree() {
        NIL = new RBTNode("", "");
        NIL.color = false;
        root = NIL;
    }

    public void insert(String key, String value) {
        RBTNode node = new RBTNode(key, value);
        node.left = NIL;
        node.right = NIL;
        
        RBTNode parent = null;
        RBTNode current = root;

        while (current != NIL) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        node.parent = parent;
        if (parent == null) {
            root = node;
        } else if (key.compareTo(parent.key) < 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        if (node.parent == null) {
            node.color = false;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    private void fixInsert(RBTNode node) {
        while (node.parent != null && node.parent.color) {
            if (node.parent == node.parent.parent.right) {
                RBTNode uncle = node.parent.parent.left;
                if (uncle.color) {
                    uncle.color = false;
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    leftRotate(node.parent.parent);
                }
            } else {
                RBTNode uncle = node.parent.parent.right;
                if (uncle.color) {
                    uncle.color = false;
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    rightRotate(node.parent.parent);
                }
            }
            if (node == root) break;
        }
        root.color = false;
    }

    private void leftRotate(RBTNode node) {
        RBTNode rightChild = node.right;
        node.right = rightChild.left;
        
        if (rightChild.left != NIL) {
            rightChild.left.parent = node;
        }
        
        rightChild.parent = node.parent;
        
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        
        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rightRotate(RBTNode node) {
        RBTNode leftChild = node.left;
        node.left = leftChild.right;
        
        if (leftChild.right != NIL) {
            leftChild.right.parent = node;
        }
        
        leftChild.parent = node.parent;
        
        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }
        
        leftChild.right = node;
        node.parent = leftChild;
    }

    public String search(String key) {
        return searchRecursive(root, key);
    }

    private String searchRecursive(RBTNode node, String key) {
        if (node == NIL) return null;
        
        if (key.equals(node.key)) {
            return node.value;
        }
        
        if (key.compareTo(node.key) < 0) {
            return searchRecursive(node.left, key);
        } else {
            return searchRecursive(node.right, key);
        }
    }
}

public class DictionaryPanel extends JPanel {
    private MainFrame mainFrame;
    private JTextArea resultArea;
    private JTextField searchField;
    private RBTree englishToIndonesianTree;
    private RBTree indonesianToEnglishTree;
    private JPanel contentPanel;
    private CardLayout contentCardLayout;

    private JPanel calculatorPanel;
    private JTextField calcDisplay;
    private String currentCalcInput = "";

    private JPanel gamePanel;
    private JLabel gameMessage;
    private JTextField guessField;
    private int targetNumber;
    private int attempts;

    private boolean isEnglishToIndonesian = true;
    private JToggleButton languageToggle;
    
    public DictionaryPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.englishToIndonesianTree = new RBTree();
        this.indonesianToEnglishTree = new RBTree();
        initializeDictionary();
        initializeUI();
    }
    
    private void initializeDictionary() {

        englishToIndonesianTree.insert("hello", "Halo");
        englishToIndonesianTree.insert("world", "Dunia");
        englishToIndonesianTree.insert("java", "Bahasa pemrograman Java");
        englishToIndonesianTree.insert("computer", "Komputer");
        englishToIndonesianTree.insert("mouse", "Tikus");
        englishToIndonesianTree.insert("keyboard", "Papan ketik");
        englishToIndonesianTree.insert("book", "Buku");
        englishToIndonesianTree.insert("water", "Air");
        englishToIndonesianTree.insert("fire", "Api");
        englishToIndonesianTree.insert("earth", "Bumi");
        englishToIndonesianTree.insert("money", "Uang");
        englishToIndonesianTree.insert("project", "Proyek");
        englishToIndonesianTree.insert("tax", "Pajak");
        englishToIndonesianTree.insert("good", "Baik");
        englishToIndonesianTree.insert("bad", "Buruk");
        englishToIndonesianTree.insert("happy", "Senang");
        englishToIndonesianTree.insert("sad", "Sedih");
        englishToIndonesianTree.insert("blue", "Biru");
        englishToIndonesianTree.insert("red", "Merah");
        englishToIndonesianTree.insert("green", "Hijau");

        englishToIndonesianTree.insert("calculator", "Menampilkan kalkulator");
        englishToIndonesianTree.insert("kalkulator", "Menampilkan kalkulator");
        englishToIndonesianTree.insert("game", "Memulai game tebak angka");
        englishToIndonesianTree.insert("permainan", "Memulai game tebak angka");

        indonesianToEnglishTree.insert("halo", "Hello");
        indonesianToEnglishTree.insert("dunia", "World");
        indonesianToEnglishTree.insert("komputer", "Computer");
        indonesianToEnglishTree.insert("tikus", "Mouse");
        indonesianToEnglishTree.insert("papan ketik", "Keyboard");
        indonesianToEnglishTree.insert("buku", "Book");
        indonesianToEnglishTree.insert("air", "Water");
        indonesianToEnglishTree.insert("api", "Fire");
        indonesianToEnglishTree.insert("bumi", "Earth");
        indonesianToEnglishTree.insert("uang", "Money");
        indonesianToEnglishTree.insert("proyek", "Project");
        indonesianToEnglishTree.insert("pajak", "Tax");
        indonesianToEnglishTree.insert("baik", "Good");
        indonesianToEnglishTree.insert("buruk", "Bad");
        indonesianToEnglishTree.insert("senang", "Happy");
        indonesianToEnglishTree.insert("sedih", "Sad");
        indonesianToEnglishTree.insert("biru", "Blue");
        indonesianToEnglishTree.insert("merah", "Red");
        indonesianToEnglishTree.insert("hijau", "Green");
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(18, 18, 18));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        contentCardLayout = new CardLayout();
        contentPanel = new JPanel(contentCardLayout);
        contentPanel.setBackground(new Color(18, 18, 18));

        JPanel dictionaryPanel = createDictionaryPanel();
        calculatorPanel = createCalculatorPanel();
        gamePanel = createGamePanel();
        
        contentPanel.add(dictionaryPanel, "DICTIONARY");
        contentPanel.add(calculatorPanel, "CALCULATOR");
        contentPanel.add(gamePanel, "GAME");
        
        add(contentPanel, BorderLayout.CENTER);
        contentCardLayout.show(contentPanel, "DICTIONARY");
    }
    
    private JPanel createDictionaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(18, 18, 18));
        panel.setBorder(new EmptyBorder(15, 0, 0, 0));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(18, 18, 18));
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel headerLabel = new JLabel("KAMUS BAHASA");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerLabel.setForeground(new Color(52, 152, 219));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel togglePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        togglePanel.setBackground(new Color(18, 18, 18));
        togglePanel.setBorder(new EmptyBorder(5, 0, 0, 0));

        languageToggle = new JToggleButton("ENGLISH → INDONESIA");
        languageToggle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        languageToggle.setBackground(new Color(41, 128, 185));
        languageToggle.setForeground(Color.WHITE);
        languageToggle.setFocusPainted(false);
        languageToggle.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        languageToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        languageToggle.setSelected(true);

        languageToggle.addActionListener(e -> {
            if (languageToggle.isSelected()) {
                languageToggle.setText("ENGLISH → INDONESIA");
                isEnglishToIndonesian = true;
            } else {
                languageToggle.setText("INDONESIA → ENGLISH");
                isEnglishToIndonesian = false;
            }
            updateWelcomeMessage();
        });

        togglePanel.add(languageToggle);

        headerPanel.add(headerLabel, BorderLayout.NORTH);
        headerPanel.add(togglePanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(18, 18, 18));
        centerPanel.setBorder(new EmptyBorder(40, 150, 40, 150));

        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.setBackground(new Color(18, 18, 18));
        searchPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
        searchPanel.setMaximumSize(new Dimension(400, 45));
        
        searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBackground(new Color(40, 40, 40));
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(52, 152, 219), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        JButton searchButton = new JButton("CARI");
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        searchButton.setBackground(new Color(41, 128, 185));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        resultArea.setBackground(new Color(30, 30, 30));
        resultArea.setForeground(Color.WHITE);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(30, 30, 30));
        scrollPane.setPreferredSize(new Dimension(500, 150));

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(18, 18, 18));
        footerPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        JButton backButton = new JButton("← Kembali ke Dashboard");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        backButton.setBackground(new Color(60, 60, 60));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        footerPanel.add(backButton);

        centerPanel.add(searchPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(scrollPane);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(footerPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> performSearch());
        searchField.addActionListener(e -> performSearch());
        backButton.addActionListener(e -> mainFrame.showDashboard());

        updateWelcomeMessage();
        
        return panel;
    }

    private void updateWelcomeMessage() {
        if (isEnglishToIndonesian) {
            resultArea.setText("Ketik kata dalam bahasa Inggris dan tekan ENTER atau klik tombol CARI.\n\n" +
                              "Contoh: hello, world, computer, blue, red, green");
        } else {
            resultArea.setText("Type words in Indonesian and press ENTER or click SEARCH button.\n\n" +
                              "Examples: halo, dunia, komputer, biru, merah, hijau");
        }
    }
    
    private void performSearch() {
        String searchText = searchField.getText().trim().toLowerCase();
        
        if (searchText.isEmpty()) {
            resultArea.setText(isEnglishToIndonesian ? 
                "Masukkan kata yang ingin dicari..." : 
                "Enter the word you want to search...");
            return;
        }

        switch (searchText) {
            case "calculator":
            case "kalkulator":
                contentCardLayout.show(contentPanel, "CALCULATOR");
                resultArea.setText("");
                return;
                
            case "game":
            case "permainan":
                contentCardLayout.show(contentPanel, "GAME");
                resultArea.setText("");
                return;
                
            default:
                String meaning;
                if (isEnglishToIndonesian) {
                    meaning = englishToIndonesianTree.search(searchText);
                } else {
                    meaning = indonesianToEnglishTree.search(searchText);
                }
                
                if (meaning != null) {
                    displaySearchResult(searchText, meaning);
                } else {
                    displayNotFound(searchText);
                }
        }
    }

    private void displaySearchResult(String searchText, String meaning) {
        String word = searchText.toUpperCase();
        String languageFrom = isEnglishToIndonesian ? "English" : "Indonesian";
        String languageTo = isEnglishToIndonesian ? "Indonesian" : "English";

        String result = "KAMUS BAHASA\n\n" +
                       "Word: " + word + "\n" +
                       languageFrom + ": " + searchText + "\n" +
                       languageTo + ": " + meaning + "\n\n" +
                       "Example: \"" + searchText + "\" in a sentence...\n\n" +
                       "---\n" ;
        
        resultArea.setText(result);
    }

    private void displayNotFound(String searchText) {
        if (isEnglishToIndonesian) {
            resultArea.setText("KAMUS BAHASA\n\n" +
                             "Word: " + searchText.toUpperCase() + "\n" +
                             "Status: Tidak ditemukan\n\n" +
                             "Coba kata: hello, world, computer, blue, red, green\n\n" +
                             "---\n");
        } else {
            resultArea.setText("KAMUS BAHASA\n\n" +
                             "Kata: " + searchText.toUpperCase() + "\n" +
                             "Status: Not found\n\n" +
                             "Try: halo, dunia, komputer, biru, merah, hijau\n\n" +
                             "---\n");
        }
    }

    private JPanel createCalculatorPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(18, 18, 18));
        panel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JLabel headerLabel = new JLabel("KALKULATOR");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(new Color(241, 196, 15));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(new EmptyBorder(0, 0, 20, 0));

        calcDisplay = new JTextField("0");
        calcDisplay.setFont(new Font("Segoe UI", Font.BOLD, 20));
        calcDisplay.setBackground(new Color(40, 40, 40));
        calcDisplay.setForeground(Color.WHITE);
        calcDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        calcDisplay.setEditable(false);
        calcDisplay.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(241, 196, 15), 2),
            BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 6, 6));
        buttonPanel.setBackground(new Color(18, 18, 18));
        buttonPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        
        String[] buttons = {
            "C", "⌫", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=", "←"
        };
        
        for (String text : buttons) {
            JButton button = createCalcButton(text);
            buttonPanel.add(button);
        }
        
        panel.add(headerLabel, BorderLayout.NORTH);
        panel.add(calcDisplay, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JButton createCalcButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (text.equals("=")) {
            button.setBackground(new Color(41, 128, 185));
            button.setForeground(Color.WHITE);
        } else if (text.matches("[0-9.]")) {
            button.setBackground(new Color(60, 60, 60));
            button.setForeground(Color.WHITE);
        } else if (text.equals("←")) {
            button.setBackground(new Color(231, 76, 60));
            button.setForeground(Color.WHITE);
        } else if (text.equals("C")) {
            button.setBackground(new Color(230, 126, 34));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(new Color(80, 80, 80));
            button.setForeground(new Color(241, 196, 15));
        }
        
        button.addActionListener(e -> handleCalcButton(text));
        return button;
    }

    private void handleCalcButton(String command) {
        switch (command) {
            case "C":
                currentCalcInput = "";
                calcDisplay.setText("0");
                break;
            case "⌫":
                if (!currentCalcInput.isEmpty()) {
                    currentCalcInput = currentCalcInput.substring(0, currentCalcInput.length() - 1);
                    calcDisplay.setText(currentCalcInput.isEmpty() ? "0" : currentCalcInput);
                }
                break;
            case "=":
                calculateResult();
                break;
            case "←":
                contentCardLayout.show(contentPanel, "DICTIONARY");
                break;
            default:
                if (currentCalcInput.equals("0")) {
                    currentCalcInput = command;
                } else {
                    currentCalcInput += command;
                }
                calcDisplay.setText(currentCalcInput);
        }
    }
    
    private void calculateResult() {
        try {
            String expression = currentCalcInput.replaceAll("×", "*")
                                              .replaceAll("÷", "/")
                                              .replaceAll("%", "/100");

            Object result = new javax.script.ScriptEngineManager()
                .getEngineByName("JavaScript")
                .eval(expression);
            
            currentCalcInput = result.toString();
            calcDisplay.setText(currentCalcInput);
        } catch (Exception ex) {
            calcDisplay.setText("Error");
            currentCalcInput = "";
        }
    }
    
    private JPanel createGamePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(18, 18, 18));
        panel.setBorder(new EmptyBorder(40, 100, 40, 100));

        JLabel headerLabel = new JLabel("GAME TEBAK ANGKA");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(new Color(46, 204, 113));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(new EmptyBorder(0, 0, 30, 0));

        JPanel gameContent = new JPanel(new BorderLayout(15, 15));
        gameContent.setBackground(new Color(18, 18, 18));
        
        gameMessage = new JLabel("Saya memikirkan angka 1-100. Coba tebak!");
        gameMessage.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gameMessage.setForeground(Color.WHITE);
        gameMessage.setHorizontalAlignment(SwingConstants.CENTER);
        
        guessField = new JTextField();
        guessField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        guessField.setHorizontalAlignment(SwingConstants.CENTER);
        guessField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(46, 204, 113), 2),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        
        JButton guessButton = new JButton("TEBAK!");
        guessButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        guessButton.setBackground(new Color(46, 204, 113));
        guessButton.setForeground(Color.WHITE);
        guessButton.setFocusPainted(false);
        guessButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton backButton = new JButton("← Kembali ke Kamus");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        backButton.setBackground(new Color(60, 60, 60));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBackground(new Color(18, 18, 18));
        inputPanel.add(guessField, BorderLayout.CENTER);
        inputPanel.add(guessButton, BorderLayout.EAST);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(18, 18, 18));
        buttonPanel.add(backButton);
        
        gameContent.add(gameMessage, BorderLayout.NORTH);
        gameContent.add(inputPanel, BorderLayout.CENTER);
        gameContent.add(buttonPanel, BorderLayout.SOUTH);
        
        panel.add(headerLabel, BorderLayout.NORTH);
        panel.add(gameContent, BorderLayout.CENTER);

        guessButton.addActionListener(e -> checkGuess());
        guessField.addActionListener(e -> checkGuess());
        backButton.addActionListener(e -> contentCardLayout.show(contentPanel, "DICTIONARY"));

        startNewGame();
        
        return panel;
    }
    
    private void startNewGame() {
        targetNumber = (int) (Math.random() * 100) + 1;
        attempts = 0;
        gameMessage.setText("Saya memikirkan angka 1-100. Coba tebak!");
        guessField.setText("");
        guessField.requestFocus();
    }
    
    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;
            
            if (guess < targetNumber) {
                gameMessage.setText(guess + " terlalu RENDAH! Percobaan: " + attempts);
            } else if (guess > targetNumber) {
                gameMessage.setText(guess + " terlalu TINGGI! Percobaan: " + attempts);
            } else {
                gameMessage.setText("BENAR! Angka " + targetNumber + " ditemukan dalam " + attempts + " percobaan!");
            }
            
            guessField.setText("");
            guessField.requestFocus();
        } catch (NumberFormatException ex) {
            gameMessage.setText("Masukkan angka yang valid!");
            guessField.setText("");
        }
    }
}