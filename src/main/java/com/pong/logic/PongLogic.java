package com.pong.logic;

import java.util.Random;

public class PongLogic {
    private static final int IDLE = 0;
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;

    private static final int[] rounds = {5, 5, 3, 3, 2};
    private static final String[] colors = {"#1abc9c", "#2ecc71", "#3498db", "#e74c3c", "#9b59b6"};

    private int canvasWidth = 1400;
    private int canvasHeight = 1000;

    private Paddle player;
    private Paddle paddle;
    private Ball ball;

    private boolean running = false;
    private boolean over = false;
    private Paddle turn;
    private int timer = 0;
    private int round = 0;
    private String color = "#2c3e50";

    public PongLogic() {
        initialize();
    }

    private void initialize() {
        this.ball = new Ball(canvasWidth, canvasHeight,9);
        this.player = new Paddle(canvasWidth, canvasHeight, "left");
        this.paddle = new Paddle(canvasWidth, canvasHeight, "right");

        paddle.setSpeed(8);

        running = over = false;
        turn = paddle;
        timer = round = 0;
        color = "#2c3e50";
    }

    public void update() {
        if (!over) {
            if (ball.getX() <= 0) resetTurn(paddle, player);
            if (ball.getX() >= canvasWidth - ball.getWidth()) resetTurn(player, paddle);
            if (ball.getY() <= 0) ball.setMoveY(DOWN);
            if (ball.getY() >= canvasHeight - ball.getHeight()) ball.setMoveY(UP);

            if (player.getMove() == UP) player.setY(player.getY() - player.getSpeed());
            else if (player.getMove() == DOWN) player.setY(player.getY() + player.getSpeed());

            if (turn != null && turn != player && turn != paddle && turnDelayIsOver()) {
                ball.setMoveX(turn == player ? LEFT : RIGHT);
                ball.setMoveY(new Random().nextBoolean() ? UP : DOWN);
                ball.setY(new Random().nextInt(canvasHeight - 200) + 200);
                turn = null;
            }

            if (player.getY() <= 0) player.setY(0);
            else if (player.getY() >= canvasHeight - player.getHeight()) player.setY(canvasHeight - player.getHeight());

            if (ball.getMoveY() == UP) ball.setY(ball.getY() - (ball.getSpeed() / 1.5));
            else if (ball.getMoveY() == DOWN) ball.setY(ball.getY() + (ball.getSpeed() / 1.5));
            if (ball.getMoveX() == LEFT) ball.setX(ball.getX() - ball.getSpeed());
            else if (ball.getMoveX() == RIGHT) ball.setX(ball.getX() + ball.getSpeed());

            if (paddle.getY() > ball.getY() - ((double) paddle.getHeight() / 2)) {
                if (ball.getMoveX() == RIGHT) paddle.setY(paddle.getY() - (paddle.getSpeed() / 1.5));
                else paddle.setY(paddle.getY() - ((double) paddle.getSpeed() / 4));
            }
            if (paddle.getY() < ball.getY() - ((double) paddle.getHeight() / 2)) {
                if (ball.getMoveX() == RIGHT) paddle.setY(paddle.getY() + (paddle.getSpeed() / 1.5));
                else paddle.setY(paddle.getY() + ((double) paddle.getSpeed() / 4));
            }

            if (paddle.getY() >= canvasHeight - paddle.getHeight()) paddle.setY(canvasHeight - paddle.getHeight());
            else if (paddle.getY() <= 0) paddle.setY(0);
        }

        if (player.getScore() == rounds[round]) {
            if (rounds[round + 1] == 0) {
                over = true;
            } else {
                color = generateRoundColor();
                player.setScore(0);
                paddle.setScore(0);
                player.setSpeed(player.getSpeed() + 0.5);
                paddle.setSpeed(paddle.getSpeed() + 1);
                ball.setSpeed(ball.getSpeed() + 1);
                round++;
            }
        } else if (paddle.getScore() == rounds[round]) {
            over = true;
        }
    }

    private void resetTurn(Paddle victor, Paddle loser) {
        ball = new Ball(0,0,ball.getSpeed());
        turn = loser;
        timer = (int) System.currentTimeMillis();

        victor.setScore(victor.getScore() + 1);
    }

    private boolean turnDelayIsOver() {
        return (System.currentTimeMillis() - timer >= 1000);
    }

    private String generateRoundColor() {
        String newColor = colors[new Random().nextInt(colors.length)];
        if (newColor.equals(color)) return generateRoundColor();
        return newColor;
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

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Paddle getTurn() {
        return turn;
    }

    public void setTurn(Paddle turn) {
        this.turn = turn;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPlayerScore() {
        return player.getScore();
    }

    public int getPaddleScore() {
        return paddle.getScore();
    }


    // Getters and setters for canvasWidth, canvasHeight, player, paddle, ball, running, over, turn, timer, round, color
}
