package gameoff2021.game.entities.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.utilities.Constants;

public class Tile {

    Rectangle hitBox;
    float[] position;
    float width = Constants.Tile.TILE_WIDTH, height = Constants.Tile.TILE_HEIGHT;

    TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/sprites.atlas"));// texture atlas instantiation and file path
    TextureRegion debugTexture = atlas.findRegion("hitboxDebug");// hitbox texture region

    public Tile(float x, float y){
        position = new float[]{x,y};
        hitBox = new Rectangle(width,height,position[0],position[1]);
    }

    public void drawBox(Batch batch){
        //System.out.println(position[0] + "***" + position[1] + "***" + width + "***" + height);
        batch.draw(debugTexture,hitBox.x,hitBox.y,hitBox.width,hitBox.height);
    }

    public boolean isTouched(Rectangle rectangle){
        return hitBox.overlaps(rectangle);
    }

    public Rectangle getHitbox(){
        return hitBox;
    }
}

