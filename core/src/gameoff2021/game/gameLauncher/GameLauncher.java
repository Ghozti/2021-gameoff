package gameoff2021.game.gameLauncher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import gameoff2021.game.entities.entities.FireWall;
import gameoff2021.game.entities.entities.Player;
import gameoff2021.game.entities.entities.Virus;
import gameoff2021.game.entities.map.Map;
import gameoff2021.game.entities.map.Tile;
import gameoff2021.game.entities.map.TileSet;

public class GameLauncher implements Screen {

    /* @AUTHOR - Ghozti
     * This is the GameLauncher class, This is where we will wrap everything up (at least for the main screen), this means that this is where we will create
     * our player object, map, etc and call in their update and render method.
     * If you notice we have a class called Game. We will not be touching that a lot so no need to worry on that.
     *
     * Now let's go over the basics.
     * - don't worry about Camera and viewport. just think of it as something that allows us to see the game in an advanced way. We will  mainly use it to track the player
     * - SpriteBatch is a class that will take care of rendering our textures for the player or map
     */

    //screen
    OrthographicCamera camera;
    SpriteBatch batch;

    //map stuff
    OrthogonalTiledMapRenderer renderer;
    TiledMap map;
    TmxMapLoader loader;
    Map tiledMapBorders;


    //game objects (player, map etc)
    Player player;
    Virus virus, virus1 ;
    FireWall miniFireWall, topRightWall, bottomRightWall, topLeftWall, bottomLeftWall, coreWall1, coreWall2, coreWall3 , coreWall4;

    boolean developMode = true;//for when we are developing we can set the viewport to see the entire screen

    //this is the constructor, where we will initialize our fields. (camera, viewport, batch, etc)
    public GameLauncher(){
        batch = new SpriteBatch();
        player = new Player();
        virus = new Virus(53 * 30,19 * 30, 1860, 1590,1.5f, player);//these are the arguments for the virus near the top right corner
        virus1 = new Virus(11 * 30, 30, 31 * 30, 11 * 30,.80f, player);

        miniFireWall = new FireWall(player,60,570);//the fire wall at the mid left
        miniFireWall.getSprite().setRotation(90);

        topRightWall = new FireWall(player, 300,870);
        topRightWall.getSprite().setRotation(90);

        bottomRightWall = new FireWall(player,300,180);
        bottomRightWall.getSprite().setRotation(90);

        topLeftWall = new FireWall(player,1590,870);
        topLeftWall.getSprite().setRotation(270);

        bottomLeftWall = new FireWall(player,1590,180);
        bottomLeftWall.getSprite().setRotation(270);

        coreWall1 = new FireWall(player,30 * 32,30 * 12);//bottom core wall

        coreWall2 = new FireWall(player,30 * 32,30 * 26);//top core wall
        coreWall2.getSprite().setRotation(180);

        coreWall3 = new FireWall(player,30 * 23,30 * 19);//left core wall
        coreWall3.getSprite().setRotation(270);

        coreWall4 = new FireWall(player,30 * 41,30 * 19);//right core wall
        coreWall4.getSprite().setRotation(90);


        //map and cam
        tiledMapBorders = new Map();
        loader = new TmxMapLoader();
        map = loader.load("untitled.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        if (developMode) {
            camera.viewportWidth = 1920;//144
            camera.viewportHeight = 1080;//120
        }else {
            camera.viewportWidth = 144;//144
            camera.viewportHeight = 120;//120
        }
    }

    //this method will be called once (we won't really touch this a lot)
    @Override
    public void show() {

    }

    /*
    - this is the update method, where we will be updating our sprites and map. This method will be called in the render method since it's called repetitively.
    - we created this method in order to make the render method more read-able
     */
    public void update() {
        //call the sprite update methods here
        camera.position.set(player.getCenter()[0], player.getCenter()[1], 0);//will change the camera x and y with the player's x and y
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        player.update();
        for(TileSet tileSet : tiledMapBorders.getTileSets()){
            if (tileSet.isTouched(player.getHitbox())) {
                player.isTouchedByWall = true;
            }
        }
        virus.update();
        virus1.update();

        miniFireWall.update();

        topRightWall.update();
        bottomRightWall.update();
        topLeftWall.update();
        bottomLeftWall.update();

        coreWall1.update();
        coreWall2.update();
        coreWall3.update();
        coreWall4.update();
    }

    /*
     - this method will be used to render everything. Note: make sure to render things in between batch.begin() and batch.end() in order for this to work
     - this method will also be used to call the update method since it is called repetitively.
     - delta is also knows as delta time. Think of it like a time-lapse of how long the program has been running since that's essentially what it does
     - we can use delta time for a lot of things, such as a timer
     */

    @Override
    public void render(float delta) {
        update();

        ScreenUtils.clear(0, 0, 0, 1);// will reset the screen to black
        renderer.setView(camera);
        renderer.render();

        batch.begin();
        //render stuff here
        tiledMapBorders.drawTilesAndSets(batch);

        virus.draw(batch);
        virus1.draw(batch);

        miniFireWall.draw(batch);

        topRightWall.draw(batch);
        bottomRightWall.draw(batch);
        topLeftWall.draw(batch);
        bottomLeftWall.draw(batch);

        coreWall1.draw(batch);
        coreWall2.draw(batch);
        coreWall3.draw(batch);
        coreWall4.draw(batch);

        player.draw(batch);

        batch.end();
    }

    // don't worry about this method it will just be used for when the screen resizes so that the rendering doesn't get messed up
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    //this method will be used to dispose no longer needed memory in order to minimize gpu usage
    @Override
    public void dispose() {
        batch.dispose();
        renderer.dispose();
        map.dispose();
    }
}
