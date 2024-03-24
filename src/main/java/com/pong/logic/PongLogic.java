package com.pong.logic;

import com.pong.api.PongGameData;
import java.util.Random;

public class PongLogic {


    private static PongGameData gameData;

    public static final int IDLE = 0;
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    private static final int[] rounds = {5, 5, 3, 3, 2};
    private static final String[] colors = {"#1abc9c", "#2ecc71", "#3498db", "#e74c3c", "#9b59b6"};


    private static PongLogic instance;

    private PongLogic() {}


    public static PongLogic getInstance(){
        if(instance == null){
            instance = new PongLogic();
        }
        return instance;
    }
    public  void initialize(int canvasWidth, int canvasHeight) {
        gameData = PongGameData.getInstance();
        gameData.setCanvasWidth(canvasWidth);
        gameData.setCanvasHeight(canvasHeight);
        gameData.setPlayer(new Paddle(canvasWidth, canvasHeight, "left"));
        gameData.setPaddle(new Paddle(canvasWidth, canvasHeight, "right"));
        gameData.setBall(new Ball(canvasWidth, canvasHeight, 9));
        gameData.setTurn(gameData.getPaddle());
        gameData.setTimer(0);
        gameData.setRound(0);
        gameData.setColor("#2c3e50");
        gameData.setOver(false);
        update();
    }

    public void update() {
        if (!gameData.isOver()) {
            /*
             * Collision de la balle avec les murs
             */
            if (gameData.getBall().getX() <= 0){
                resetTurn(gameData.getPaddle(), gameData.getPlayer());
            }
            if (gameData.getBall().getX() >= gameData.getCanvasWidth() - gameData.getBall().getWidth()){
                resetTurn(gameData.getPlayer(), gameData.getPaddle());
            }
            if (gameData.getBall().getY() <= 0){
                gameData.getBall().setMoveY(DOWN);
            }
            if (gameData.getBall().getY() >= gameData.getCanvasHeight() - gameData.getBall().getHeight()) {
                gameData.getBall().setMoveY(UP);
            }
            //Mouvement du joueur
            if (gameData.getPlayer().getMove() == UP) {
                gameData.getPlayer().setY(gameData.getPlayer().getY() - gameData.getPlayer().getSpeed());
            }
            else if (gameData.getPlayer().getMove() == DOWN) {
                gameData.getPlayer().setY(gameData.getPlayer().getY() + gameData.getPlayer().getSpeed());
            }

            //Au service, lancement de la balle
            if (gameData.getTurn() != null && turnDelayIsOver()) {
                gameData.getBall().setMoveX(gameData.getTurn() == gameData.getPlayer() ? LEFT : RIGHT);
                gameData.getBall().setMoveY(new Random().nextBoolean() ? UP : DOWN);
                gameData.getBall().setY(new Random().nextInt(gameData.getCanvasHeight() - 200) + 200);
                gameData.setTurn(null);
            }

            //Collisions du joueur avec les murs
            if (gameData.getPlayer().getY() <= 0){
                gameData.getPlayer().setY(0);
            }
            else if (gameData.getPlayer().getY() >= gameData.getCanvasHeight() - gameData.getPlayer().getHeight()){
                gameData.getPlayer().setY(gameData.getCanvasHeight() - gameData.getPlayer().getHeight());
            }

            //Mouvement de la balle
            if (gameData.getBall().getMoveY() == UP){
                gameData.getBall().setY(gameData.getBall().getY() - (gameData.getBall().getSpeed() / 1.5));
            }
            else if (gameData.getBall().getMoveY() == DOWN) {
                gameData.getBall().setY(gameData.getBall().getY() + (gameData.getBall().getSpeed() / 1.5));
            }
            if (gameData.getBall().getMoveX() == LEFT) {
                gameData.getBall().setX(gameData.getBall().getX() - gameData.getBall().getSpeed());
            }
            else if (gameData.getBall().getMoveX() == RIGHT) {
                gameData.getBall().setX(gameData.getBall().getX() + gameData.getBall().getSpeed());
            }

            //AI mouvement
            if (gameData.getPaddle().getY() > gameData.getBall().getY() - ((double) gameData.getPaddle().getHeight() / 2)) {
                if (gameData.getBall().getMoveX() == RIGHT)
                    gameData.getPaddle().setY(gameData.getPaddle().getY() - (gameData.getPaddle().getSpeed() / 1.5));
                else gameData.getPaddle().setY(gameData.getPaddle().getY() - (gameData.getPaddle().getSpeed() / 4));
            }
            if (gameData.getPaddle().getY() < gameData.getBall().getY() - ((double) gameData.getPaddle().getHeight() / 2)) {
                if (gameData.getBall().getMoveX() == RIGHT)
                    gameData.getPaddle().setY(gameData.getPaddle().getY() + (gameData.getPaddle().getSpeed() / 1.5));
                else gameData.getPaddle().setY(gameData.getPaddle().getY() + (gameData.getPaddle().getSpeed() / 4));
            }

            //Collisions de la raquette AI avec les murs
            if (gameData.getPaddle().getY() >= gameData.getCanvasHeight() - gameData.getPaddle().getHeight()) {
                gameData.getPaddle().setY(gameData.getCanvasHeight() - gameData.getPaddle().getHeight());
            }
            else if (gameData.getPaddle().getY() <= 0) {
                gameData.getPaddle().setY(0);
            }

            //Player collision with ball
            if (gameData.getBall().getX() - gameData.getBall().getWidth() <= gameData.getPlayer().getX() && gameData.getBall().getX() >= gameData.getPlayer().getX() - gameData.getPlayer().getWidth()) {
                if (gameData.getBall().getY() <= gameData.getPlayer().getY() + gameData.getPlayer().getHeight() && gameData.getBall().getY() + gameData.getBall().getHeight() >= gameData.getPaddle().getY()) {
                    gameData.getBall().setX(gameData.getPlayer().getX() + gameData.getBall().getWidth());
                    gameData.getBall().setMoveX(4);
                }
            }

            //Player collision with ball
            if (gameData.getBall().getX() <= gameData.getPlayer().getX() + gameData.getPlayer().getWidth() &&
                    gameData.getBall().getX() >= gameData.getPlayer().getX() &&
                    gameData.getBall().getY() + gameData.getBall().getHeight() >= gameData.getPlayer().getY() &&
                    gameData.getBall().getY() <= gameData.getPlayer().getY() + gameData.getPlayer().getHeight()) {
                gameData.getBall().setX(gameData.getPlayer().getX() + gameData.getPlayer().getWidth());
                gameData.getBall().setMoveX(4);
            }

            //AI collision with ball
            if (gameData.getBall().getX() - gameData.getBall().getWidth() <= gameData.getPaddle().getX() && gameData.getBall().getX() >= gameData.getPaddle().getX() - gameData.getPaddle().getWidth()) {
                if (gameData.getBall().getY() <= gameData.getPaddle().getY() + gameData.getPaddle().getHeight() && gameData.getBall().getY() + gameData.getBall().getHeight() >= gameData.getPaddle().getY()) {
                    gameData.getBall().setX(gameData.getPaddle().getX() - gameData.getBall().getWidth());
                    gameData.getBall().setMoveX(3);
                }
            }

        }

        if (gameData.getPlayer().getScore() == rounds[gameData.getRound()]) {
            if (gameData.getRound() + 1 < rounds.length) {
                gameData.setOver(true);
            } else {
                gameData.setColor(generateRoundColor());
                gameData.getPlayer().setScore(0);
                gameData.getPaddle().setScore(0);
                gameData.getPlayer().setSpeed(gameData.getPlayer().getSpeed() + 0.5);
                gameData.getPaddle().setSpeed(gameData.getPaddle().getSpeed() + 1);
                gameData.getBall().setSpeed(gameData.getBall().getSpeed() + 1);
                gameData.setRound(gameData.getRound()+1);
            }
        } else if (gameData.getPaddle().getScore() == rounds[gameData.getRound()]) {
            gameData.setOver(true);
        }
    }

    private void resetTurn(Paddle victor, Paddle loser) {
        gameData.setBall(new Ball(gameData.getCanvasWidth(), gameData.getCanvasHeight(), gameData.getBall().getSpeed()));
        gameData.setTurn(loser);
        gameData.setTimer((int) System.currentTimeMillis());

        victor.setScore(victor.getScore() + 1);
    }

    private boolean turnDelayIsOver() {
        return (System.currentTimeMillis() - gameData.getTimer() >= 1000);
    }

    private String generateRoundColor() {
        String newColor = colors[new Random().nextInt(colors.length)];
        if (newColor.equals(gameData.getColor())) return generateRoundColor();
        return newColor;
    }
}
