package gameoff2021.game.entities.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.entities.builder.GameSprite;
import gameoff2021.game.ui.PlayerInput;

public class Key extends GameSprite {

    Player player;
    boolean hasBeenCollected;

    public Key(Player player, float x, float y){
        this.player = player;
        setDebug(true);
        createSprite(new Sprite(atlas.findRegion("key")));
        setTexture(atlas.findRegion("key"));
        createHitbox(new Rectangle());
        setPosition(x, y);
        setOrigin(15,15);
        setUnScaledWidth(30);
        setUnscaledHeight(30);
        setScale(1);
    }

    @Override
    public void draw(Batch batch) {
        if (!hasBeenCollected) {
            drawSprite(batch);
            drawHitBox(batch);
        }
    }

    @Override
    public void update() {
        if (player.reset){
            hasBeenCollected = false;
        }
        if (!hasBeenCollected) {
            if (getHitbox().overlaps(player.getHitbox()) && PlayerInput.isSpacePressed()) {
                player.isTouchedByKey = true;
                hasBeenCollected = true;
                player.keyCount++;
            }
        }
    }
}
