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
        createSprite(new Sprite(atlas.findRegion("virus")));
        setTexture(atlas.findRegion("virus"));
        createHitbox(new Rectangle());
        setPosition(initX, initY);
        setOrigin(0,0);
        setUnScaledWidth(Constants.Player.UNSCALED_WIDTH);
        setUnscaledHeight(Constants.Player.UNSCALED_HEIGHT);
        setScale(1);
    }

    float delta = 0;

    @Override
    public void draw(Batch batch) {
        drawSprite(batch);
        drawHitBox(batch);
        delta++;
    }

    @Override
    public void update() {

        if (delta <= 10) {
            setTexture(atlas.findRegion("virus"));
        }else if (delta >= 11 ){
            setTexture(atlas.findRegion("virus2"));
        }if (delta >= 21){
            delta = 0;
        }

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
