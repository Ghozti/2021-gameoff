package gameoff2021.game.entities.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import gameoff2021.game.utilities.Constants;

public class Map {

    Texture texture = new Texture(Gdx.files.internal("map.png"));
    final float width = Constants.Map.MAP_WIDTH,height = Constants.Map.MAP_HEIGHT,x = Constants.Map.X,y = Constants.Map.Y;

    public void draw(Batch batch){
        batch.draw(texture,x,y,width,height);
    }
}
