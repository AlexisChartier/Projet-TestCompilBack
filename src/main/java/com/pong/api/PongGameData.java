package com.pong.api;
import com.google.gson.Gson;
import com.pong.logic.Ball;
import com.pong.logic.Paddle;
public class PongGameData {

    private static PongGameData instance;


    private static final int[] rounds = {5, 5, 3, 3, 2};
    private Paddle turn;
    private long timer;
    private int playerScore;
    private int paddleScore;
    private boolean over;
    private boolean running;
    private int round;

    private int canvasWidth;
    private int canvasHeight;

    private Paddle player;
    private Paddle paddle;
    private Ball ball;

    private String color;

    private PongGameData() {}

    public static PongGameData getInstance() {
        if (instance == null) {
            instance = new PongGameData();
        }
        return instance;
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

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public Paddle getPlayer() {
        return player;
    }

    public void setPlayer(Paddle player) {
        this.player = player;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Paddle getTurn() {
        return turn;
    }

    public void setTurn(Paddle turn) {
        this.turn = turn;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }

    public void getTimer(long timer) {
        this.timer = timer;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
