package gameoff2021.game.entities.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.entities.builder.GameSprite;
import gameoff2021.game.ui.PlayerInput;

public class Lever extends GameSprite {

    Player player;
    FireWall respectiveFireWall;

    public Lever(Player player, FireWall respectiveFireWall ,float x, float y){
        this.player = player;
        this.respectiveFireWall = respectiveFireWall;
        setDebug(true);
        createSprite(new Sprite(atlas.findRegion("leftlever")));
        setTexture(atlas.findRegion("leftlever"));
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
            if (PlayerInput.isSpacePressed()){
                respectiveFireWall.unlocked = true;
            }
        }
    }
}
