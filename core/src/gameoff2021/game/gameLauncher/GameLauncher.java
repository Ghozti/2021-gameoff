package gameoff2021.game.gameLauncher;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import gameoff2021.game.entities.entities.*;
import gameoff2021.game.entities.map.Map;
import gameoff2021.game.entities.map.TileSet;
import gameoff2021.game.ui.GUI;

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
    Key topRightKey, bottomRightKey, topLeftKey, bottomLeftKey;
    Lever topRightCornerLever, bottomRightCornerLever, topLeftCornerLever, bottomLeftCornerLever;

    //font shit
    GUI gui;
    Batch batch2;

    boolean developMode = true;//for when we are developing we can set the viewport to see the entire screen

    //this is the constructor, where we will initialize our fields. (camera, viewport, batch, etc)
    public GameLauncher(){
        batch = new SpriteBatch();
        player = new Player();

        virus = new Virus(53 * 30,19 * 30, 1860, 1590,1.5f, player);//these are the arguments for the virus near the top right corner
        virus1 = new Virus(11 * 30, 30, 31 * 30, 11 * 30,.80f, player);

        //firewalls
        miniFireWall = new FireWall(player,60,570,true, false);//the fire wall at the mid left
        miniFireWall.getSprite().setRotation(90);

        topRightWall = new FireWall(player, 300,870);
        topRightWall.getSprite().setRotation(90);

        bottomRightWall = new FireWall(player,300,180);
        bottomRightWall.getSprite().setRotation(90);

        topLeftWall = new FireWall(player,1590,870);
        topLeftWall.getSprite().setRotation(270);

        bottomLeftWall = new FireWall(player,1590,180);
        bottomLeftWall.getSprite().setRotation(270);

        coreWall1 = new FireWall(player,30 * 32,30 * 12,false ,true);//bottom core wall

        coreWall2 = new FireWall(player,30 * 32,30 * 26, false,true);//top core wall
        coreWall2.getSprite().setRotation(180);

        coreWall3 = new FireWall(player,30 * 23,30 * 19,false, true);//left core wall
        coreWall3.getSprite().setRotation(270);

        coreWall4 = new FireWall(player,30 * 41,30 * 19,false,true);//right core wall
        coreWall4.getSprite().setRotation(90);

        //keys
        topLeftKey = new Key(player, 30 * 5, 30 * 31);
        bottomLeftKey = new Key(player, 30 * 5, 30 * 5);
        topRightKey = new Key(player, 30 * 58, 30 * 31);
        bottomRightKey = new Key(player, 30 * 58, 30 * 5);

        //levers
        topRightCornerLever = new Lever(player,bottomLeftWall, 30 * 1, 30 * 19);
        bottomRightCornerLever = new Lever(player, topLeftWall, 30 * 11, 30 * 1);
        topLeftCornerLever = new Lever(player,bottomRightWall, 30 * 62, 30 * 19);
        bottomLeftCornerLever = new Lever(player, topRightWall, 30 * 62, 30 * 1);

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

        gui = new GUI();
        batch2 = new SpriteBatch();
    }

    //this method will be called once (we won't really touch this a lot)
    @Override
    public void show() {

    }

    /*
    - this is the update method, where we will be updating our sprites and map. This method will be called in the render method since it's called repetitively.
    - we created this method in order to make the render method more read-able
     */

    float delta = 0;

    public void update() {
        //call the sprite update methods here
        delta++;
        camera.position.set(player.getCenter()[0], player.getCenter()[1], 0);//will change the camera x and y with the player's x and y
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if (player.reset){
            player.reset = false;
        }

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

        topLeftKey.update();
        bottomLeftKey.update();
        topRightKey.update();
        bottomRightKey.update();

        topRightCornerLever.update();
        bottomRightCornerLever.update();
        topLeftCornerLever.update();
        bottomLeftCornerLever.update();
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

        batch2.begin();

        if (this.delta  <= 500){
            gui.draw(batch2,"use w,a,s,d to move", 15, 110);
            gui.draw(batch2,"collect all 4 corner keys to unlock the main core, \nbe careful of viruses as they will reset your progress",15, 70);
        }

        gui.draw(batch2, "keys collected: " + player.keyCount, 15, 1030);

        batch2.end();

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

        topLeftKey.draw(batch);
        bottomLeftKey.draw(batch);
        topRightKey.draw(batch);
        bottomRightKey.draw(batch);

        topRightCornerLever.draw(batch);
        bottomRightCornerLever.draw(batch);
        topLeftCornerLever.draw(batch);
        bottomLeftCornerLever.draw(batch);

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
        batch2.dispose();
        renderer.dispose();
        map.dispose();
    }
}
