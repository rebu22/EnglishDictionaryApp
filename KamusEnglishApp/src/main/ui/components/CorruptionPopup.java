package main.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorruptionPopup extends JDialog {
    
    public CorruptionPopup(Frame parent, String name, String position, String corruptionCase) {
        super(parent, "Pejabat Koruptor Terdeteksi!", true);
        initializeUI(name, position, corruptionCase);
    }
    
    private void initializeUI(String name, String position, String corruptionCase) {
        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(231, 76, 60));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new FlowLayout());
        headerPanel.setBackground(new Color(231, 76, 60));
        
        JLabel warningIcon = new JLabel("Warning");
        warningIcon.setFont(new Font("Arial", Font.BOLD, 24));
        warningIcon.setForeground(Color.YELLOW);
        
        JLabel titleLabel = new JLabel("PEJABAT KORUPTOR MUNCUL!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        
        headerPanel.add(warningIcon);
        headerPanel.add(titleLabel);

        JPanel detailsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        detailsPanel.setBackground(new Color(231, 76, 60));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        JLabel nameLabel = new JLabel("Nama: " + name, JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(Color.YELLOW);
        
        JLabel positionLabel = new JLabel("Jabatan: " + position, JLabel.CENTER);
        positionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        positionLabel.setForeground(Color.WHITE);
        
        JLabel caseLabel = new JLabel("<html><center>Kasus: " + corruptionCase + "</center></html>");
        caseLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        caseLabel.setForeground(Color.WHITE);
        
        detailsPanel.add(nameLabel);
        detailsPanel.add(positionLabel);
        detailsPanel.add(caseLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(231, 76, 60));
        
        JButton closeButton = new JButton("Tutup");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBackground(Color.WHITE);
        closeButton.setForeground(new Color(231, 76, 60));
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        closeButton.addActionListener(e -> dispose());
        
        buttonPanel.add(closeButton);
        
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(detailsPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(contentPanel);

        Timer autoCloseTimer = new Timer(5000, e -> dispose());
        autoCloseTimer.setRepeats(false);
        autoCloseTimer.start();
    }
    
    public static void showPopup(Component parent, String name, String position, String corruptionCase) {
        Frame frame = JOptionPane.getFrameForComponent(parent);
        CorruptionPopup popup = new CorruptionPopup(frame, name, position, corruptionCase);
        popup.setVisible(true);
    }
}