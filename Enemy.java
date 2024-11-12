package es.iesRuizGijon.ejercicioInventado;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Enemy {
    private int x, y;
    private int dx = 4, dy = 4;
    private final int WIDTH = 100, HEIGHT = 100; // Tamaño del enemigo
    private Image enemyImage;
    private Random random;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.random = new Random();
        this.enemyImage = new ImageIcon(getClass().getResource("cuervo.png")).getImage();
    }

    public void move() {
        x += dx;
        y += dy;

        // Rebote en los bordes de la ventana
        if (x < 0 || x > 1100) dx = -dx;
        if (y < 0 || y > 700) dy = -dy;
    }

    public void resetPosition() {
        x = random.nextInt(1100);
        y = random.nextInt(700);
    }

    public void draw(Graphics g) {
        g.drawImage(enemyImage, x, y, WIDTH, HEIGHT, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
