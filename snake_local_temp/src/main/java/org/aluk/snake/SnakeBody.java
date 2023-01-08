package org.aluk.snake;

public class SnakeBody {
    private int x;
    private int y;
    private SnakeBody next;
    private SnakeBody previous;
    private int direction = 4;
    private int[] lastCoords;

    public SnakeBody(int x, int y) {
        this.x = x;
        this.y = y;
        lastCoords = new int[3];
    }

    public SnakeBody(int x, int y, SnakeBody previous) {
        this.x = x;
        this.y = y;
        this.previous = previous;
        previous.setNext(this);
        lastCoords = new int[3];
    }

    public SnakeBody getNext() {
        return this.next;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean hasNext() {
        return !(next == null);
    }

    public void setNext(SnakeBody next) {
        this.next = next;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void move() {
        lastCoords[0] = this.x;
        lastCoords[1] = this.y;
        lastCoords[2] = this.direction;
        if (this.direction == 0) {
            this.x++;
        } else if (this.direction == 1) {
            this.y++;
        } else if (this.direction == 2) {
            this.x--;
        } else if (this.direction == 3) {
            this.y--;
        }

        if (hasNext() && next.getDirection() != this.direction) {
            next.move(this.direction);
        } else if (hasNext()) {
            next.move();
        }
    }

    public void move(int dir) {
        lastCoords[0] = this.x;
        lastCoords[1] = this.y;
        lastCoords[2] = this.direction;
        if (this.direction == 0) {
            this.x++;
        } else if (this.direction == 1) {
            this.y++;
        } else if (this.direction == 2) {
            this.x--;
        } else if (this.direction == 3) {
            this.y--;
        }
        int temp = this.direction;
        this.direction = dir;
        if (hasNext() && next.getDirection() != temp) {
            next.move(temp);
        } else if (hasNext()) {
            next.move();
        }
    }

    public int[] getLastCoords() {
        return this.lastCoords;
    }
}
