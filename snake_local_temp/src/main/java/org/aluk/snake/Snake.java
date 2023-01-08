package org.aluk.snake;

import java.io.*;
import java.util.*;

public class Snake {
    private int[][] board;
    private int score;
    private int highScore;
    private List<SnakeBody> snakeList;
    private SnakeBody snakeHead;

    public Snake() {
        reset();
    }

    public SnakeBody getHead() {
        return this.snakeHead;
    }

    public boolean move() {
        snakeHead.move();
        boolean newApple = false;
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j] == 2) {
                    if (snakeHead.getX() == i && snakeHead.getY() == j) {
                        SnakeBody temp = snakeHead;
                        while (temp.hasNext()) {
                            temp = temp.getNext();
                        }
                        int[] lastCoords = temp.getLastCoords();
                        snakeList.add(new SnakeBody(lastCoords[0], lastCoords[1],
                                snakeList.get(snakeList.size() - 1)));
                        snakeList.get(snakeList.size() - 1).setDirection(lastCoords[2]);
                        board[i][j] = 1;
                        board[lastCoords[0]][lastCoords[1]] = 1;
                        newApple = true;
                        score++;
                        if (score > highScore) {
                            highScore = score;
                            saveHighScore();
                        }
                    }
                } else {
                    board[i][j] = 0;
                    for (SnakeBody s : snakeList) {
                        if (s.getX() == i && s.getY() == j) {
                            board[i][j] = 1;
                        }
                        if (s.getX() > 16 || s.getX() < 0 || s.getY() > 14 || s.getY() < 0) {
                            return false;
                        }
                        if (s.getX() == snakeHead.getX() && s.getY() == snakeHead.getY()
                                && s != snakeHead) {
                            return false;
                        }
                    }
                }
            }
        }
        if (newApple) {
            int[] appleCords = generateApple();
            board[appleCords[0]][appleCords[1]] = 2;
        }
        return true;
    }

    public void reset() {
        snakeList = new ArrayList<SnakeBody>();
        snakeHead = new SnakeBody(4, 7);
        snakeList.add(snakeHead);
        snakeList.add(new SnakeBody(3, 7, snakeHead));
        snakeList.add(new SnakeBody(2, 7, snakeList.get(1)));

        board = new int[17][15];
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = 0;
                for (SnakeBody s : snakeList) {
                    if (s.getX() == i && s.getY() == j) {
                        board[i][j] = 1;
                    }
                }
            }
        }
        board[12][7] = 2;
        score = 0;
        fileHighScore();
    }

    public int[] generateApple() {
        int[] appleCoords = new int[2];
        appleCoords[0] = (int) (Math.random() * 17);
        appleCoords[1] = (int) (Math.random() * 15);
        if (board[appleCoords[0]][appleCoords[1]] == 1) {
            return generateApple();
        }
        return appleCoords;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getScore() {
        return this.score;
    }

    public int getHighScore() {
        return this.highScore;
    }

    public int getSize() {
        return snakeList.size();
    }

    public void fileHighScore() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("files/highscore.txt"));
            int highScore = Integer.parseInt(reader.readLine());
            this.highScore = highScore;
        } catch (IOException e) {
            this.highScore = 0;
        }
    }

    public void saveHighScore() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("files/highscore.txt"));
            writer.write(String.valueOf(highScore));
            writer.close();
        } catch (IOException e) {
        }
    }
}
