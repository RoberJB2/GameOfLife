/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Robert J.
 */
public class Universe extends JPanel implements MouseListener, MouseMotionListener {
    private final int cellSize = 20;
    private Map<Point, Chunk> chunks = new HashMap<>();

    private int hoverRow = -1;
    private int hoverCol = -1;
    
    public Universe() {

        // Set the layout to a grid
        addMouseListener(this);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(128, 128));
    }
    
    private Chunk getChunk(int chunkX, int chunkY) {
        Point key = new Point(chunkX, chunkY);
        
        Chunk chunk = chunks.get(key);
        if (chunk == null) {
            chunk = new Chunk();
            chunks.put(key, chunk);
        }
        return chunk;
    }
    
    private Color getCellColor(int row, int col) {
        int chunkX = floorDiv(col, Chunk.CHUNK_SIZE);
        int chunkY = floorDiv(row, Chunk.CHUNK_SIZE);
        
        int localCol = mod(col, Chunk.CHUNK_SIZE);
        int localRow = mod(row, Chunk.CHUNK_SIZE);
        
        return getChunk(chunkX, chunkY).cells[localRow][localCol];
    }
    
    private void setCellColor(int row, int col, Color color) {
        int chunkX = floorDiv(col, Chunk.CHUNK_SIZE);
        int chunkY = floorDiv(row, Chunk.CHUNK_SIZE);

        int localCol = mod(col, Chunk.CHUNK_SIZE);
        int localRow = mod(row, Chunk.CHUNK_SIZE);

        getChunk(chunkX, chunkY).cells[localRow][localCol] = color;
    }
    
    private int floorDiv(int a, int b) {
        return (int)Math.floor((float)a / b);
    }
    
    private int mod(int a, int b) {
        int m = a % b;
        return (m < 0) ? m + b : m;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Rectangle view = getVisibleRect();
        
        // Compute visible row/col bounds
        int startCol = view.x / cellSize;
        int endCol   = (view.x + view.width) / cellSize;
        
        int startRow = view.y / cellSize;
        int endRow   = (view.y + view.height) / cellSize;

        Graphics2D g2 = (Graphics2D) g;

        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {

                // Default cell fill
                int x = c * cellSize;
                int y = r * cellSize;
                
                Color base = getCellColor(r, c);
     
                // Draw cell base
                g2.setColor(base);
                g2.drawRect(x, y, cellSize, cellSize);

                // LOWER OPACITY if hovering over this cell
                if (r == hoverRow && c == hoverCol) {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
                    g2.setColor(Color.BLACK);
                    g2.fillRect(x, y, cellSize, cellSize);

                    // Reset composite
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                }
                
                // Draw cell gridlines
                g2.setColor(Color.GRAY);
                g2.drawRect(x, y, cellSize, cellSize);
            }
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        hoverCol = e.getX() / cellSize;
        hoverRow = e.getY() / cellSize;

        // Keep safe bounds
        if (!inBounds(hoverRow, hoverCol)) {
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
        Color base = getCellColor(row, col);
        
        if (!inBounds(row, col)) return;
        
        if (!SwingUtilities.isLeftMouseButton(e)) {
            return;
        }
        
        if (base == Color.BLACK) {
            setCellColor(row, col, Color.WHITE);
        } 
        else {
            setCellColor(row, col, Color.BLACK);
        }
    }
    
    private boolean inBounds(int r, int c) {
        return r >= 0 && c >= 0;
    }
    
    // Unused methods
    @Override public void mouseDragged(MouseEvent e) { mouseMoved(e); }
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
}
