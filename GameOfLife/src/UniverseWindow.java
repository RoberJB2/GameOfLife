/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Robert J.
 */
public class UniverseWindow extends JFrame {
    public UniverseWindow() {
        super("The Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Universe uni = new Universe();              // your grid panel
        JScrollPane scrollPane = new JScrollPane(uni);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        // Useful for adding resolution options:
        setSize(800, 600);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UniverseWindow());
    }
}
