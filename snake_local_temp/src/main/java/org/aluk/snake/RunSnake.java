package org.aluk.snake;

import java.awt.*;
import javax.swing.*;

public class RunSnake implements Runnable {
    public void run() {
        // Top-level frame in which game components live.
        final JFrame frame = new JFrame("SNAKE");
        frame.setLocation(350, 0);
        frame.setSize(865, 824);

        // Instructions
        JOptionPane.showMessageDialog(frame,
                "Use the arrow keys to move the snake. The goal is to collect as many of the\n" +
                        "apples (circles) as possible. Every time you collect one, your score\n" +
                "goes up and your snake increases in length. Be careful as when your snake\n" +
                        " gets long enough you can run into yourself and lose. Also be careful\n" +
                        " to watch for any walls as hitting them will end your game. You can\n" +
                        " see your time running for the game, current score, and previous high\n" +
                        " score on top of your game.");

        // Status panel
        final JLabel status = new JLabel("Waiting...");

        // Control panel
        final JPanel controlPanel = new JPanel();
        frame.add(controlPanel, BorderLayout.NORTH);

        // Timer
        final JLabel time = new JLabel("00:00.000");
        controlPanel.add(time);

        // Current Score
        final JLabel currScore = new JLabel("Score: 0");
        controlPanel.add(currScore);

        // High Score
        final JLabel highScore = new JLabel("High Score: 0");
        controlPanel.add(highScore);

        // Main playing area
        final GameBoard board = new GameBoard(status, currScore, highScore, time);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        controlPanel.add(reset);

        // Add status
        controlPanel.add(status);

        // Put the frame on the screen
        frame.pack();
        frame.setSize(865, 824);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        board.reset();
    }
}
