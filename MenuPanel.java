package es.iesRuizGijon.ejercicioInventado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private Image backgroundImage; // Imagen de fondo del menú
    private JButton playButton;

    public MenuPanel(ActionListener startGameListener) {
        // Cargar la imagen de fondo del menú
        this.backgroundImage = new ImageIcon(getClass().getResource("rana_fondo.jpg")).getImage();

        // Configurar el botón de "Jugar"
        playButton = new JButton("Jugar");
        playButton.setFont(new Font("Arial", Font.BOLD, 24));
        playButton.setFocusPainted(false);
        playButton.addActionListener(startGameListener);  // Añadir listener para el botón

        // Configurar el diseño del menú y añadir el botón al centro
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Asegurar que el botón se centra en el panel
        gbc.gridx = 0;  // Columna 0
        gbc.gridy = 0;  // Fila 0
        gbc.weightx = 1.0;  // Peso horizontal (para que ocupe todo el espacio disponible)
        gbc.weighty = 1.0;  // Peso vertical (para que ocupe todo el espacio disponible)
        gbc.anchor = GridBagConstraints.CENTER;  // Centrar el botón
        gbc.insets = new Insets(0, 0, 0, 0);  // Sin márgenes extra
        add(playButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo del menú
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
