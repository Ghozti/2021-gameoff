package gameoff2021.game.entities.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.utilities.Constants;

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

    public Map(){

        mapTiles = new Tile[4][4];
        int currentX = 0, currentY = 1080;

        for(int i = 0; i < mapTiles.length; i++){
            for (int j = 0; j < mapTiles[i].length; j++){
                mapTiles[i][j] = new Tile(currentX,currentY);
                currentX += 30;
            }
            currentY -= 30;
            currentX = 0;
        }

        /*
        mapTiles = new Tile[36][64];//basically creates 36 arrays with a length of 64 in each

        int currentX = 0, currentY = 1080;

        for(int i = 0; i < mapTiles.length; i++){
            for (int j = 0; j < mapTiles[i].length; j++){
                currentX += 30;
                mapTiles[i][j] = new Tile(currentX,currentY);
            }
            currentY -= 30;
            currentX = 0;
        }
         */
    }

    public Tile getTile(int x, int y){
        return mapTiles[x-1][y-1];
    }
}
