/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;

/**
 *
 * @author Robert J.
 */
public class Chunk {
    public static final int CHUNK_SIZE = 32;
    
    public Color[][] cells = new Color[CHUNK_SIZE][CHUNK_SIZE];
    
    public Chunk() {
        for (int r = 0; r < CHUNK_SIZE; r++) {
            for (int c = 0; c < CHUNK_SIZE; c++) {
                cells[r][c] = Color.BLACK;
            }
        }
    }
}
