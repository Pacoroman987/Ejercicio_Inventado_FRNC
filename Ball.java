package es.iesRuizGijon.ejercicioInventado;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Ball {
    private int x, y;
    private int dx = 3, dy = 3;
    private final int WIDTH = 80, HEIGHT = 80; // Tamaño mosca
    private Image ballImage;
    private Random random;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.random = new Random();
        // Cargar la imagen del balón desde un archivo
        this.ballImage = new ImageIcon(getClass().getResource("mosca.png")).getImage();
    }

    public void move() {
        x += dx;
        y += dy;

        if (x < 0 || x > 1120) dx = -dx; // Limitar a la nueva dimensión
        if (y < 0 || y > 720) dy = -dy;
    }

    public void resetPosition() {
        x = random.nextInt(1120); // Limitar a la nueva dimensión
        y = random.nextInt(720);
        increaseSpeed();
    }

    public void increaseSpeed() {
        dx += (dx > 0) ? 1 : -1;
        dy += (dy > 0) ? 1 : -1;
    }

    public void draw(Graphics g) {
        g.drawImage(ballImage, x, y, WIDTH, HEIGHT, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}

