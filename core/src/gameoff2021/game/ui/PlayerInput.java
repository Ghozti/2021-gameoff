package gameoff2021.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import gameoff2021.game.utilities.Constants;

/*
 * Author: wholesomebrit
 * I created a class (PlayerInput) and I also created a method called InputProcessor.
 * PlayerInput displays the inputs the player uses
 * InputProcessor processes the players input and returns an array that represents the x and y change.
 */

public class PlayerInput {
    public static float[] inputProcessor(){
        float xchange = 0, ychange = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            ychange = Constants.Player.MAX_SPEEDY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            ychange = -Constants.Player.MAX_SPEEDY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            xchange = Constants.Player.MAX_SPEEDX;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            xchange = -Constants.Player.MAX_SPEEDX;
        }
        return new float[]{xchange, ychange};
    }
}
