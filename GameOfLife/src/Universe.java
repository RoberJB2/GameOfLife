/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Robert J.
 */
public class Universe extends JPanel implements MouseListener, MouseMotionListener {
    
    private final int rows = 10;
    private final int cols = 10;
    private final int cellSize = 20;
    
    private boolean[][] alive = new boolean[rows][cols];

    private int hoverRow = -1;
    private int hoverCol = -1;
    
    public Universe() {
        // Set the layout to a grid with 4 rows and 2 columns
        addMouseListener(this);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // Default cell fill
                g2.setColor(alive[r][c] ? Color.GREEN : Color.WHITE);
                g2.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);

                // LOWER OPACITY if hovering over this cell
                if (r == hoverRow && c == hoverCol) {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
                    g2.setColor(Color.BLACK);
                    g2.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);

                    // Reset composite
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                }

                // Draw grid outline
                g2.setColor(Color.GRAY);
                g2.drawRect(c * cellSize, r * cellSize, cellSize, cellSize);
            }
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        hoverCol = e.getX() / cellSize;
        hoverRow = e.getY() / cellSize;

        // Keep safe bounds
        if (hoverRow < 0 || hoverRow >= rows || hoverCol < 0 || hoverCol >= cols) {
            hoverRow = hoverCol = -1;
        }

        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        hoverRow = hoverCol = -1;  // reset hover
        repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;

        // Toggle the state
        alive[row][col] = !alive[row][col];
        repaint();
    }
    
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseDragged(MouseEvent e) {}
}
