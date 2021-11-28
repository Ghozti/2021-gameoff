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
    public static char inputProcessor(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            return 'w';
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            return 's';
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            return 'd';
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            return 'a';
        }
        return '*';
    }

    public static boolean isSpacePressed(){
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }
}
