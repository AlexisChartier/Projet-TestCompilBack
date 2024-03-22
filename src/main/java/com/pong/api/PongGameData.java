package com.pong.api;


public class PongGameData {

    private int playerScore;
    private int paddleScore;
    private boolean over;
    private boolean running;
    private int round;

    public PongGameData(){}

    public PongGameData(int playerScore, int paddleScore, boolean over, boolean running, int round) {
        this.playerScore = playerScore;
        this.paddleScore = paddleScore;
        this.over = over;
        this.running = running;
        this.round = round;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getPaddleScore() {
        return paddleScore;
    }

    public void setPaddleScore(int paddleScore) {
        this.paddleScore = paddleScore;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
