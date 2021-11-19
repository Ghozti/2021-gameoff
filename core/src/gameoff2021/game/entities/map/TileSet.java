package gameoff2021.game.entities.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class TileSet {

    float[] position;
    float width, height;
    Rectangle hitbox;

    TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/sprites.atlas"));// texture atlas instantiation and file path
    TextureRegion debugTexture = atlas.findRegion("hitboxDebug");// hitbox texture region

    public TileSet(float width, float height, float x, float y){
        position = new float[]{0,0};
        this.width = width;
        this.height = height;
        position[0] = x;
        position[1] = y;

        hitbox = new Rectangle();
        hitbox.width = width;
        hitbox.height = height;
        hitbox.x = position[0];
        hitbox.y = position[1] - height;
    }

    public boolean isTouched(Rectangle hitbox){
        return this.hitbox.overlaps(hitbox);
    }

    public void draw(Batch batch){
        batch.draw(debugTexture, hitbox.x,hitbox.y,hitbox.width,hitbox.height);
    }

    public Rectangle getHitbox(){
        return hitbox;
    }
}
