package gameoff2021.game.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.ui.PlayerInput;

public class Player extends GameSprite{

    public Player(){
        setDebug(true);
        createSprite(new Sprite(atlas.findRegion("badlogic")));
        setTexture(atlas.findRegion("badlogic"));
        createHitbox(new Rectangle());
        setPosition(500,500);
        setUnScaledWidth(256);
        setUnscaledHeight(256);
        setScale(.5f);
        //setHitboxDimensions();
        setHitboxOffSet(64,64);
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
