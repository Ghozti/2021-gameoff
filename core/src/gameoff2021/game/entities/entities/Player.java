package gameoff2021.game.entities.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.entities.builder.GameSprite;
import gameoff2021.game.ui.PlayerInput;
import gameoff2021.game.utilities.Constants;

public class Player extends GameSprite {

    public Player() {
        setDebug(true);
        createSprite(new Sprite(atlas.findRegion("badlogic")));
        setTexture(atlas.findRegion("badlogic"));
        createHitbox(new Rectangle());
        setPosition(Constants.Player.INIT_X, Constants.Player.INIT_Y);
        setOrigin(0,0);//sets the origin of the player to 0,0 from the sprite width/height
        setUnScaledWidth(Constants.Player.UNSCALED_WIDTH);
        setUnscaledHeight(Constants.Player.UNSCALED_HEIGHT);
        setScale(Constants.Player.SCALE);
    }

    //for the camera to center on the player
    public float[] getCenter(){
        return new float[] {getPositionArr()[0] + getWidth() / 2 , getPositionArr()[1] + getHeight() / 2};
    }

    //makes sure to keep the player inside the map
    public void keepInBorders(){
        if (getPositionArr()[0] < 0){
            setPosition(0, getPositionArr()[1]);
        }else if (getPositionArr()[0] > 1920 - getWidth()){
            setPosition(1920 - getWidth(), getPositionArr()[1]);
        }
        if (getPositionArr()[1] < 0){
            setPosition(getPositionArr()[0], 0);
        }else if (getPositionArr()[1] > 1080 - getHeight()){
            setPosition(getPositionArr()[0], 1080 - getHeight());
        }
    }

    @Override
    public void draw(Batch batch) {
        drawSprite(batch);
        drawHitBox(batch);
    }

    @Override
    public void update() {
        // Called the updatePosition method to call the InputProcessor method to update the position.
        updatePosition(PlayerInput.inputProcessor()[0],PlayerInput.inputProcessor()[1]);
        keepInBorders();
    }
}
