package gameoff2021.game.entities.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.utilities.Constants;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Map {

    static Texture texture = new Texture(Gdx.files.internal("map.png"));
    static final float width = Constants.Map.MAP_WIDTH, height = Constants.Map.MAP_HEIGHT,x = Constants.Map.X,y = Constants.Map.Y;

    public static void draw(Batch batch){
        batch.draw(texture,x,y,width,height);
    }


    //TESTING BELOW

    //the map has 64 tiles on the x axis and 36 tiles on the y

    ArrayList<Tile> interactiveTiles;
    ArrayList<TileSet> interactiveTileSets;

    /*
     * current issue:
     * while this works, as we create more tiles, we will have m,ore objects and a less memory and cpu efficient way or rendering and creating tiles,
     * this is bad because it physically slows down the game and leads to potential crashes.
     *
     * solutions: (maybe)
     * create a tileSet class that will be able to be the "representative" for a series of tiles (specifically tiles that are next to esach other and in a straight line)
     * while this still uses the tile class method of doing stuff, it significantly decreases the objects created and textures rendered.
     */

    public Map(){
        interactiveTiles = new ArrayList<>();
        interactiveTileSets = new ArrayList<>();

        //MAP BORDERS
        addInteractiveTileSets(1920f,30f,0f,36f);//top border
        addInteractiveTileSets(30f,1080,0f,35);
    }

    public void addInteractiveTile(float x, float y){
        interactiveTiles.add(new Tile(x*30,y*30));
    }

    public void addInteractiveTileSets(float width, float height, float x, float y){interactiveTileSets.add(new TileSet(width,height,x*30,y*30));}

    public void drawTilesAndSets(Batch batch){
        for ( Tile tile : interactiveTiles) {
            tile.drawBox(batch);
        }

        for(TileSet tileSet : interactiveTileSets){
            tileSet.draw(batch);
        }
    }
}
