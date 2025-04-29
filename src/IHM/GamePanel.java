package IHM;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    private int playerX = 100;
    private int playerY = 100;
    private BufferedImage playerSprite;
    private final GameMap map = new GameMap();
    private final int tileSize = 40;
    private int playerRow = 1;
    private int playerCol = 1;

    public GamePanel() {
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP -> movePlayer(-1, 0);
                    case KeyEvent.VK_DOWN -> movePlayer(1, 0);
                    case KeyEvent.VK_LEFT -> movePlayer(0, -1);
                    case KeyEvent.VK_RIGHT -> movePlayer(0, 1);
                }
            }
        });

        try {
            // à remplacer par le vrai chemin une fois que tu as l’image
            playerSprite = ImageIO.read(new File("resources/player.png"));
        } catch (IOException e) {
            System.out.println("Image non trouvée, on dessine un carré à la place.");
            playerSprite = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Fond
        g.setColor(Color.green);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int row = 0; row < map.getHeight(); row++) {
            for (int col = 0; col < map.getWidth(); col++) {
                int tile = map.getTile(row, col);
                switch (tile) {
                    case 0 -> g.setColor(Color.green);  // herbe
                    case 1 -> g.setColor(Color.gray);   // pierre
                }
                g.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }

        // Puis dessine ton perso par-dessus
        int x = playerCol * tileSize;
        int y = playerRow * tileSize;

        if (playerSprite != null) {
            g.drawImage(playerSprite, playerX, playerY, 40, 40, null);
        } else {
            g.setColor(Color.red);
            g.fillRect(x, y, tileSize, tileSize);
        }
    }


    public void movePlayer(int dRow, int dCol) {
        int newRow = playerRow + dRow;
        int newCol = playerCol + dCol;

        // vérifier les limites de la carte et gère les collisions
        if (newRow >= 0 && newRow < map.getHeight() &&
                newCol >= 0 && newCol < map.getWidth() &&
                map.getTile(newRow, newCol) != 1) {
            playerRow = newRow;
            playerCol = newCol;
            if (playerRow == 2 && playerCol == 4) { new FightWindow(); }
            repaint();
        }
    }
}

