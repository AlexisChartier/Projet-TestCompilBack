package com.pong.logic;

public class Paddle {
    private int width;
    private int height;
    private double x;
    private double y;
    private int score;
    private int move;
    private double speed;

    public Paddle(int canvasWidth, int canvasHeight, String side) {
        this.width = 18;
        this.height = 70;
        this.x = side.equals("left") ? 150.0 : canvasWidth - 150;
        this.y = (canvasHeight / 2.0) - 35.0;
        this.score = 0;
        this.move = 0;
        this.speed = 10;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
