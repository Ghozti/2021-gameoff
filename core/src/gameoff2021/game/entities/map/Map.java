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

    Tile[][] mapTiles;//a 2d array list with a width of 64 and a height of 36, 2d arrays are essentially a big array containing arrays

    /*
     * think of 2d arrays as a colum-row chart
     * in the first [] you specify the number of rows, and in the second [] you specify the number of columns
     * ex: float [][] arr = new float[numOfRows][numOfColumns];
     *
     * ex: if you have a 2d array of [2][4] you will have an array that looks like this:
     * [0,0,0,0]
     * [0,0,0,0]
     *
     * remember that array indexes start at 0, therefore to call row 2 and column 4 you type arr[1][3]
     */

    ArrayList<Tile> interactiveTiles;

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
        interactiveTiles = new ArrayList();
        addInteractiveTile(0,1080);
    }

    public void addInteractiveTile(float x, float y){
        interactiveTiles.add(new Tile(x,y));
    }

    public Tile getInteractiveTile(float x, float y){
        for(Tile tile : interactiveTiles){
            if (tile.position[0] == x && tile.position[1] == y){
                return tile;
            }
        }
        return null;
    }
}
