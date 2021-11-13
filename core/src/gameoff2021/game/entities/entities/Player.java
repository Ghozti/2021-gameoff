package gameoff2021.game.entities.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.entities.builder.GameSprite;
import gameoff2021.game.ui.PlayerInput;
import gameoff2021.game.utilities.Constants;

public class Player extends GameSprite {

    public Player(){
        setDebug(true);
        createSprite(new Sprite(atlas.findRegion("badlogic")));
        setTexture(atlas.findRegion("badlogic"));
        createHitbox(new Rectangle());
        setPosition(Constants.Player.INIT_X,Constants.Player.INIT_Y);
        setUnScaledWidth(Constants.Player.UNSCALED_WIDTH);
        setUnscaledHeight(Constants.Player.UNSCALED_HEIGHT);
        setScale(Constants.Player.SCALE);
        //setHitboxDimensions();
        setHitboxOffSet(Constants.Player.HITBOXOFFSET_X,Constants.Player.HITBOXOFFSET_Y);
    }

    public float[] getDirectionalChange(){
        return PlayerInput.inputProcessor();
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
    }
}
