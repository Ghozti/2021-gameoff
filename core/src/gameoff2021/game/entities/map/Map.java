package gameoff2021.game.entities.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Map {

    Texture texture = new Texture(Gdx.files.internal("map.png"));
    final float width = 1920,height = 1080,x = 0,y = 0;

    public void draw(Batch batch){
        batch.draw(texture,x,y,width,height);
    }
}
