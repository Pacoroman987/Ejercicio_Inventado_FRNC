package es.iesRuizGijon.ejercicioInventado;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Player {
    private int x, y, dx, dy;
    private final int WIDTH = 150, HEIGHT = 150; // Tamaño aumentado del jugador
    private Image playerImage;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        // Cargar la imagen del jugador desde un archivo
        this.playerImage = new ImageIcon(getClass().getResource("rana.png")).getImage();
    }

    public void move() {
        x += dx;
        y += dy;

        if (x < 0) x = 0;
        if (x > 1080) x = 1080; // Limitar a la nueva dimensión
        if (y < 0) y = 0;
        if (y > 680) y = 680;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void draw(Graphics g) {
        g.drawImage(playerImage, x, y, WIDTH, HEIGHT, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}

