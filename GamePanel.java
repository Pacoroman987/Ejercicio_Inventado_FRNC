package es.iesRuizGijon.ejercicioInventado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Player player;
    private Ball ball;
    private Enemy enemy;
    private Timer timer;
    private int score;
    private Image backgroundImage;
    private boolean gameOver;
    private boolean isGameStarted; 
    private JButton restartButton;
    private JButton startButton;   
    private JButton menuButton;    
    private JButton helpButton;    

    public GamePanel() {
        this.player = new Player(300, 350);
        this.ball = new Ball(100, 100);
        this.enemy = new Enemy(500, 500);
        this.score = 0;
        this.gameOver = false;
        this.isGameStarted = false; 

        this.backgroundImage = new ImageIcon(getClass().getResource("estanque.png")).getImage();

 
        this.timer = new Timer(20, this);
        this.setFocusable(true);
        this.addKeyListener(this);


        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 


        this.restartButton = new JButton("Reiniciar");
        this.restartButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.restartButton.setFocusPainted(false);
        this.restartButton.setVisible(false);
        this.restartButton.addActionListener(e -> restartGame()); 
        add(restartButton);

        this.startButton = new JButton("Jugar");
        this.startButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.startButton.setFocusPainted(false);
        this.startButton.setVisible(true); 
        this.startButton.addActionListener(e -> startGame());
        add(startButton);  // Añadir al panel


        this.menuButton = new JButton("Volver al Menú");
        this.menuButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.menuButton.setFocusPainted(false);
        this.menuButton.setVisible(false);  // No mostrar inicialmente
        this.menuButton.addActionListener(e -> returnToMenu()); // Llama a returnToMenu al hacer clic
        add(menuButton);  // Añadir al panel

        // Botón de "Ayuda" en el menú principal
        this.helpButton = new JButton("Ayuda");
        this.helpButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.helpButton.setFocusPainted(false);
        this.helpButton.setVisible(true);
        this.helpButton.addActionListener(e -> showHelp()); 
        add(helpButton); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString("Puntaje: " + score, 10, 30);

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.setColor(Color.RED);
            g.drawString("¡Juego Terminado!", getWidth() / 2 - 200, getHeight() / 2);
            restartButton.setVisible(true);  // Mostrar el botón de reinicio si el juego terminó
            menuButton.setVisible(true);    // Mostrar el botón de volver al menú
            helpButton.setVisible(false);   // Ocultar el botón de ayuda cuando el juego ha terminado
            startButton.setVisible(false);  // Ocultar el botón de jugar al finalizar el juego
        } else if (!isGameStarted) {
            // En el menú inicial, solo mostrar el botón de jugar y ayuda
            restartButton.setVisible(false);
            menuButton.setVisible(false);
            startButton.setVisible(true);
            helpButton.setVisible(true);
        } else {
            // Si el juego está en curso, dibuja al jugador, la pelota y el enemigo
            player.draw(g);
            ball.draw(g);
            enemy.draw(g);
            restartButton.setVisible(false);  // Ocultar el botón de reinicio mientras se juega
            startButton.setVisible(false);    // Ocultar el botón de jugar mientras se juega
            menuButton.setVisible(false);     // Ocultar el botón de volver al menú mientras se juega
            helpButton.setVisible(false);     // Ocultar el botón de ayuda mientras se juega
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && isGameStarted) {
            player.move();
            ball.move();
            enemy.move();

            // Colisión entre jugador y pelota
            if (player.getBounds().intersects(ball.getBounds())) {
                score++;
                ball.resetPosition();
            }

            // Colisión entre jugador y enemigo
            if (player.getBounds().intersects(enemy.getBounds())) {
                score -= 5;               // Restar 5 puntos
                enemy.resetPosition();     // Reposicionar el enemigo
                if (score < 0) {
                    gameOver = true;       // Terminar el juego
                    timer.stop();          // Detener el temporizador
                }
            }

            // Comprobar si el jugador ha alcanzado los puntos necesarios para ganar
            if (score >= 50) {
                gameOver = true;           // Terminar el juego
                timer.stop();              // Detener el temporizador
                showWinMessage();          // Mostrar mensaje de victoria
            }

            repaint();
        }
    }

    // Método para iniciar el juego
    public void startGame() {
        this.isGameStarted = true;
        this.gameOver = false;  // Asegurarse de que gameOver sea falso al iniciar
        this.score = 0;         // Reiniciar puntaje al empezar un nuevo juego
        this.timer.start();     // Comenzar el temporizador
        this.requestFocus();    // Foco en el panel de juego para capturar las teclas
        repaint();
    }

    // Método para reiniciar el juego
    public void restartGame() {
        this.isGameStarted = true;
        this.gameOver = false;
        this.score = 0;
        this.player = new Player(300, 350);  // Reposicionar al jugador
        this.ball = new Ball(100, 100);      // Reposicionar la pelota
        this.enemy = new Enemy(500, 500);    // Reposicionar el enemigo
        this.timer.restart();                // Reiniciar el temporizador
        this.requestFocus();                 // Foco en el panel de juego
        repaint();
    }

    // Método para volver al menú principal
    public void returnToMenu() {
        this.isGameStarted = false;
        this.gameOver = false;
        this.score = 0;
        this.player = new Player(300, 350);  // Reposicionar al jugador
        this.ball = new Ball(100, 100);      // Reposicionar la pelota
        this.enemy = new Enemy(500, 500);    // Reposicionar el enemigo
        this.timer.stop();                   // Detener el temporizador
        repaint();
    }

    // Mostrar la ventana de ayuda con instrucciones
    private void showHelp() {
        // Crear un JDialog para mostrar las instrucciones
        JDialog helpDialog = new JDialog((Frame) null, "Ayuda", true);
        helpDialog.setSize(400, 300);
        helpDialog.setLocationRelativeTo(this);

        JPanel helpPanel = new JPanel();
        helpPanel.setLayout(new BorderLayout());

        // Instrucciones del juego
        JTextArea instructions = new JTextArea(
            "Instrucciones:\n" +
            "1. Usa las flechas para mover la rana.\n" +
            "2. Atrapa la pelota para ganar puntos.\n" +
            "3. Evita que el cuervo te toque, ya que te restará 5 puntos.\n" +
            "4. Si tus puntos llegan a -1, pierdes el juego.\n" +
            "5. Haz clic en 'Reiniciar' para jugar de nuevo o en 'Volver al Menú'."
        );
        instructions.setEditable(false);
        helpPanel.add(new JScrollPane(instructions), BorderLayout.CENTER);

        // Botón para cerrar la ventana de ayuda
        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(e -> helpDialog.dispose());
        helpPanel.add(closeButton, BorderLayout.SOUTH);

        helpDialog.add(helpPanel);
        helpDialog.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            player.setDx(-5);
        }
        if (key == KeyEvent.VK_RIGHT) {
            player.setDx(5);
        }
        if (key == KeyEvent.VK_UP) {
            player.setDy(-5);
        }
        if (key == KeyEvent.VK_DOWN) {
            player.setDy(5);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            player.setDx(0);
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            player.setDy(0);
        }
    }
    private void showWinMessage() {
        // Muestra el mensaje de victoria
        JOptionPane.showMessageDialog(this, "¡Felicidades! Has ganado el juego con " + score + " puntos.", "¡Victoria!", JOptionPane.INFORMATION_MESSAGE);
        gameOver = true;
        restartButton.setVisible(true);  // Mostrar el botón de reiniciar
        menuButton.setVisible(true);    // Mostrar el botón de volver al menú
        helpButton.setVisible(false);   // Ocultar el botón de ayuda
        startButton.setVisible(false);  // Ocultar el botón de jugar
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}


