/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author flame
 */
/**
 * NOTES:
 *  Rules:
 *      1. Any live cell with fewer than two live neighbors dies, as if by under-population.
 *      2. Any live cell with two or three live neighbors lives on to the next generation.
 *      3. Any live cell with more than three live neighbors dies, as if by overpopulation.
 *      4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 */
public class GameOfLife {
    public double positiveINF = Double.POSITIVE_INFINITY;
    public double negativeINF = Double.NEGATIVE_INFINITY;
    public int generation = 0;
    public int population = 0;
    /**
     * @param args the command line arguments
     */
    
    boolean click() {
        // if clicked, alive, if not default to dead
        
        // Add one to pop
        ++population;
        return false;
    }
    
    static void play() {
        
    }
    
    static void reset() {
        
    }
    
    static void pause() {
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
