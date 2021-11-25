package gameoff2021.game.entities.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.entities.builder.GameSprite;
import gameoff2021.game.utilities.Constants;

public class Virus extends GameSprite {

    boolean movingRight = true, movingLeft = false;

    public Virus(){
        setDebug(true);
        createSprite(new Sprite(atlas.findRegion("badlogic")));
        setTexture(atlas.findRegion("badlogic"));
        createHitbox(new Rectangle());
        setPosition(53 * 30, 19 * 30);
        setOrigin(0,0);
        setUnScaledWidth(Constants.Player.UNSCALED_WIDTH);
        setUnscaledHeight(Constants.Player.UNSCALED_HEIGHT);
        setScale(Constants.Player.SCALE);
    }

    @Override
    public void draw(Batch batch) {
        drawSprite(batch);
        drawHitBox(batch);
    }

    @Override
    public void update() {

        if (getPositionArr()[0] == 1860){
            movingLeft = true;
            movingRight = false;
        }else if (getPositionArr()[0] == 1590){
            movingRight = true;
            movingLeft = false;
        }

        if (getPositionArr()[0] != 1860 && !movingLeft){
            updatePosition(1.5f,0);
        }else if(getPositionArr()[0] != 1590 && !movingRight){

            updatePosition(-1.5f,0);
        }
    }
}
