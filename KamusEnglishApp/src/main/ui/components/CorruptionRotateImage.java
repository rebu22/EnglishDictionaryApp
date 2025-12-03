package main.ui.components;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.*;

public class CorruptionRotateImage extends JLabel {

        private BufferedImage image;
    private double angle = 0;

    public CorruptionRotateImage(ImageIcon icon) {
        this.image = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.drawImage(icon.getImage(), 0, 0, null);
        g2.dispose();

        new javax.swing.Timer(20, e -> {
            angle += Math.toRadians(10);
            repaint();
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        g2.translate(cx, cy);
        g2.rotate(angle);
        g2.drawImage(image, -image.getWidth() / 2, -image.getHeight() / 2, null);
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }
   
    
}
