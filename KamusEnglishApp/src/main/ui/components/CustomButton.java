package main.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {
    private Color normalColor;
    private Color hoverColor;
    private Color pressedColor;
    
    public CustomButton(String text, Color normalColor, Color hoverColor) {
        super(text);
        this.normalColor = normalColor;
        this.hoverColor = hoverColor;
        this.pressedColor = hoverColor.darker();
        
        initializeButton();
    }
    
    private void initializeButton() {
        setFont(new Font("Arial", Font.BOLD, 14));
        setForeground(Color.WHITE);
        setBackground(normalColor);
        setFocusPainted(false);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(normalColor.darker(), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(hoverColor.darker(), 2),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(normalColor);
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(normalColor.darker(), 2),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressedColor);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(hoverColor);
            }
        });
    }

    public Color getNormalColor() { return normalColor; }
    public void setNormalColor(Color normalColor) { 
        this.normalColor = normalColor; 
        setBackground(normalColor);
    }
    
    public Color getHoverColor() { return hoverColor; }
    public void setHoverColor(Color hoverColor) { this.hoverColor = hoverColor; }
    
    public Color getPressedColor() { return pressedColor; }
    public void setPressedColor(Color pressedColor) { this.pressedColor = pressedColor; }
}