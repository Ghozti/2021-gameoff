package gameoff2021.game.entities.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.entities.builder.GameSprite;

public class Key extends GameSprite {

    Player player;

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
        drawSprite(batch);
    }

    @Override
    public void update() {
        if (getHitbox().overlaps(player.getHitbox())){
            player.isTouchedByKey = true;
        }
    }
}
