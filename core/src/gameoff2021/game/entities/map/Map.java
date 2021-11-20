package gameoff2021.game.entities.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.utilities.Constants;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Map {

    //the map has 64 tiles on the x axis and 36 tiles on the y

    ArrayList<Tile> interactiveTiles;
    ArrayList<TileSet> interactiveTileSets;
    OrthogonalTiledMapRenderer renderer;
    TiledMap map;

    /*
     * current issue:
     * while this works, as we create more tiles, we will have more objects and a less memory and cpu efficient way or rendering and creating tiles,
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

        //top left square
        addInteractiveTileSets(270,30,1,27);
        addInteractiveTileSets(30,90,10,29);
        addInteractiveTileSets(30,150,10,35);

        //bottom left square
        addInteractiveTileSets(270,30,1,10);
        addInteractiveTileSets(30,90,10,10);
        addInteractiveTileSets(30,150,10,6);

        //top right square
        addInteractiveTileSets(30, 150, 53, 35);
        addInteractiveTileSets(30, 90, 53, 29);
        addInteractiveTileSets(270, 30, 54,27);
        //bottom right square
        addInteractiveTileSets(30, 150, 53, 6);
        addInteractiveTileSets(30, 90, 53, 10);
        addInteractiveTileSets(270, 30, 54, 10);

        //MIDDLE MAP BORDERS: LEFT SIDE
        addInteractiveTileSets(30, 210, 23, 27);
        addInteractiveTileSets(240, 30, 24, 27);
        addInteractiveTileSets(30, 210, 23, 19);
        addInteractiveTileSets(240, 30, 24, 13);

        //MIDDLE MAP BORDERS: RIGHT SIDE
        addInteractiveTileSets(240, 30, 33, 27);
        addInteractiveTileSets(240, 30, 33,13);
        addInteractiveTileSets(30, 210, 41, 27);
        addInteractiveTileSets(30, 210, 41, 19);
    }

    private void addInteractiveTile(float x, float y){
        interactiveTiles.add(new Tile(x*30,y*30));
    }

    private void addInteractiveTileSets(float width, float height, float x, float y){interactiveTileSets.add(new TileSet(width,height,x*30,y*30));}

    public void drawTilesAndSets(Batch batch){
        for ( Tile tile : interactiveTiles) {
            tile.drawBox(batch);
        }

        for(TileSet tileSet : interactiveTileSets){
            tileSet.draw(batch);
        }
    }

    public ArrayList<TileSet> getTileSets(){
        return interactiveTileSets;
    }

    public ArrayList<Tile> getTiles(){
        return interactiveTiles;
    }
}
