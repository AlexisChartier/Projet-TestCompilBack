package com.pong.logic;

public class Ball {
    private int width;
    private int height;
    private double x;
    private double y;
    private int moveX;
    private int moveY;
    private int speed;

    public Ball(int canvasWidth, int canvasHeight, int incrementedSpeed) {
        this.width = 18;
        this.height = 18;
        this.x = ((double) canvasWidth / 2) - 9;
        this.y = ((double) canvasHeight / 2) - 9;
        this.moveX = 0;
        this.moveY = 0;
        this.speed = incrementedSpeed != 0 ? incrementedSpeed : 9;
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

    public int getMoveX() {
        return moveX;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}