package gameoff2021.game.entities.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.entities.builder.GameSprite;
import gameoff2021.game.utilities.Constants;

public class Virus extends GameSprite {

    boolean movingRight = true, movingLeft = false;
    float maxX, minX, speed;
    Rectangle playerHitbox;
    Player player;

    public Virus(float initX, float initY, float maxX, float minX, float speed, Player player){
        this.player = player;
        this.playerHitbox = player.getHitbox();
        this.maxX = maxX;
        this.minX = minX;
        this.speed = speed;
        setDebug(true);
        createSprite(new Sprite(atlas.findRegion("badlogic")));
        setTexture(atlas.findRegion("badlogic"));
        createHitbox(new Rectangle());
        setPosition(initX, initY);
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

        if(getHitbox().overlaps(playerHitbox)){
            player.isTouchedByNPC = true;
        }

        if (getPositionArr()[0] >= maxX){
            movingLeft = true;
            movingRight = false;
        }else if (getPositionArr()[0] <= minX){
            movingRight = true;
            movingLeft = false;
        }

        if (getPositionArr()[0] <= maxX && !movingLeft){
            updatePosition(speed,0);
        }else if(getPositionArr()[0] >= minX && !movingRight){
            updatePosition(-speed,0);
        }
    }
}
