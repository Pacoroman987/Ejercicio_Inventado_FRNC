package es.iesRuizGijon.ejercicioInventado;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Atrapa la Pelota");
        GamePanel gamePanel = new GamePanel();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800); 
        frame.add(gamePanel);
        frame.setResizable(false); 
        frame.setVisible(true);
        

        gamePanel.requestFocusInWindow();
    }
}
