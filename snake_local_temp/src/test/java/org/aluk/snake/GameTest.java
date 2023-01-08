package org.aluk.snake;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    public void appleInBoundsTest() {
        Snake s = new Snake();
        int[] actual = s.generateApple();
        assertTrue(actual[0] >= 0 && actual[0] < 17);
        assertTrue(actual[1] >= 0 && actual[1] < 15);
    }

    @Test
    public void loseOutOfBoundsTest() {
        Snake s = new Snake();
        s.getHead().setDirection(0);
        for (int i = 0; i < 12; i++) {
            s.move();
        }
        assertFalse(s.move());
    }

    @Test
    public void notLoseInBoundsTest() {
        Snake s = new Snake();
        s.getHead().setDirection(0);
        for (int i = 0; i < 11; i++) {
            s.move();
        }
        assertTrue(s.move());
    }

    @Test
    public void increaseLengthOnAppleTest() {
        Snake s = new Snake();
        s.getHead().setDirection(0);
        assertEquals(3, s.getSize());
        for (int i = 0; i < 8; i++) {
            s.move();
        }
        assertEquals(4, s.getSize());
    }

    @Test
    public void scoreIncreaseOnApple() {
        Snake s = new Snake();
        s.getHead().setDirection(0);
        assertEquals(0, s.getScore());
        for (int i = 0; i < 8; i++) {
            s.move();
        }
        assertEquals(1, s.getScore());
    }
}
