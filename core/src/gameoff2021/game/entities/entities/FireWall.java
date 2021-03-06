package gameoff2021.game.entities.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.entities.builder.GameSprite;
import gameoff2021.game.utilities.Constants;

public class FireWall extends GameSprite {

    Player player;
    boolean unlocked, miniwall, corewall;

    public FireWall(Player player, float x, float y){
        this.player = player;
        setDebug(true);
        createSprite(new Sprite(atlas.findRegion("flame1")));
        setTexture(atlas.findRegion("flame1"));
        createHitbox(new Rectangle());
        setPosition(x, y);
        setOrigin(15,15);
        setUnScaledWidth(30);
        setUnscaledHeight(30);
        setScale(1);
    }

    public FireWall(Player player, float x, float y, boolean miniwall,boolean corewall){
        this.miniwall = miniwall;
        this.corewall = corewall;
        this.player = player;
        setDebug(true);
        createSprite(new Sprite(atlas.findRegion("flame1")));
        setTexture(atlas.findRegion("flame1"));
        createHitbox(new Rectangle());
        setPosition(x, y);
        setOrigin(15,15);
        setUnScaledWidth(30);
        setUnscaledHeight(30);
        setScale(1);
    }

    float delta = 0;
    byte currentTextureNum = 1, target = 8;

    @Override
    public void draw(Batch batch) {
        if (!unlocked) {
            delta++;
            drawSprite(batch);
            drawHitBox(batch);
        }
    }

    @Override
    public void update() {
        if (player.reset){
            unlocked = false;
        }
        if (!unlocked) {
            if (delta >= 7.5) {
                delta = 0;
                if (currentTextureNum != target) {
                    if (target == 8) {
                        currentTextureNum++;
                        if (currentTextureNum == 8) {
                            currentTextureNum = 7;
                            target = 0;
                        }
                    } else {
                        currentTextureNum--;
                        if (currentTextureNum == 0) {
                            currentTextureNum = 1;
                            target = 8;
                        }
                    }
                }
                setTexture(atlas.findRegion("flame" + currentTextureNum));
            }

            if (!unlocked) {
                if (player.getHitbox().overlaps(getHitbox())) {
                    player.isTouchedByNPC = true;
                }
            }

            if(corewall){
                unlocked = player.keyCount >= 4;
            }else if(miniwall) {
                unlocked = player.keyCount >= 2;
            }
        }
    }
}
