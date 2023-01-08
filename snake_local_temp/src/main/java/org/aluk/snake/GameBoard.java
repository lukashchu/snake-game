package org.aluk.snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoard extends JPanel {
    private Snake s; // model for the game
    private JLabel status; // current status text
    private JLabel currScore;
    private JLabel highScore;
    private JLabel timeLabel;
    private boolean playing;
    private boolean timerOn;
    // Game constants
    public static final int BOARD_WIDTH = 865;
    public static final int BOARD_HEIGHT = 850;
    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 200;
    private int time;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit, JLabel currScore, JLabel highScore, JLabel time) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        s = new Snake(); // initializes model for the game
        status = statusInit; // initializes the status JLabel
        this.currScore = currScore;
        this.highScore = highScore;
        timeLabel = time;

        Timer timer = new Timer(INTERVAL, e -> tick());
        timer.start();
        playing = false;

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (s.getHead().getDirection() == 1 || s.getHead().getDirection() == 3) {
                        s.getHead().setDirection(2);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (s.getHead().getDirection() == 4) {
                        timerOn = true;
                        s.getHead().setDirection(0);
                        s.getHead().getNext().setDirection(0);
                        s.getHead().getNext().getNext().setDirection(0);
                    }
                    if (s.getHead().getDirection() == 1 || s.getHead().getDirection() == 3) {
                        s.getHead().setDirection(0);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (s.getHead().getDirection() == 0 || s.getHead().getDirection() == 2) {
                        s.getHead().setDirection(1);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (s.getHead().getDirection() == 0 || s.getHead().getDirection() == 2) {
                        s.getHead().setDirection(3);
                    }
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });
    }

    void tick() {
        if (playing) {
            // add time
            if (timerOn) {
                time += INTERVAL;
            }
            // advance snake in current direction.
            boolean result = s.move();

            // check bounds
            if (!result) {
                playing = false;
                timerOn = false;
                return;
            }

            // update JStatus
            currScore.setText("Score: " + s.getScore());
            highScore.setText("High Score: " + s.getHighScore());
            if (timerOn) {
                status.setText("Running...");
            } else {
                status.setText("Waiting...");
            }

            // update the display
            repaint();
        }

        if (!playing) {
            status.setText("Game Over");
        }

        int ms = (time % 1000) / 100;
        int ss = ((time / 1000) % 60) % 10;
        int ts = (((time / 1000) % 60) / 10) % 10;
        int mm = ((time / 1000) / 60) % 10;
        int tm = (((time / 1000) / 60) / 10) % 10;
        timeLabel.setText(tm + "" + mm + ":" + ts + "" + ss + "." + ms + "00");
    }

    public void reset() {
        s.reset();
        repaint();
        playing = true;
        time = 0;

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[][] state = s.getBoard();
        // Draws X's and O's
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 15; j++) {
                if (state[i][j] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(i * 50, j * 50, 50, 50);
                } else if (state[i][j] == 2) {
                    g.drawOval(i * 50, j * 50, 50, 50);
                }
            }
        }
    }

}
