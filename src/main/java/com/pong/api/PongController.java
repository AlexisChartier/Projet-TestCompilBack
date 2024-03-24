package com.pong.api;

import com.pong.logic.PongLogic;
import com.pong.api.PongGameData;

public class PongController {

    public void movePaddle0() {
        PongGameData.getInstance().getPlayer().setMove(1);
        PongLogic.getInstance().update();
    }

    public void movePaddle1() {
        PongGameData.getInstance().getPlayer().setMove(-1);
        PongLogic.getInstance().update();
    }

    public void movePaddle2() {
        PongGameData.getInstance().getPlayer().setMove(1);
        PongLogic.getInstance().update();
    }

    public void movePaddle3() {
        PongGameData.getInstance().getPlayer().setMove(1);
        PongLogic.getInstance().update();
    }

    public void movePaddle4() {
        PongGameData.getInstance().getPlayer().setMove(-1);
        PongLogic.getInstance().update();
    }

}

