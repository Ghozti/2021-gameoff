package gameoff2021.game.gameLauncher;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameLauncher implements Screen {

    /* @AUTHOR - Ghozti
     * This is the GameLauncer class, This is where we will wrap everything up (atleast for the main screen), this means that this is where we will create
     * our player object, map, etc and call in their update and render method.
     * If you notice we have a class called Game. We will not be touching that a lot so no need to worry on that.
     *
     * Now let's go over the basics.
     * - don't worry about Camera and viewport. just think of it as something that allows us to see the game in an advanced way. We will  mainly use it to track the player
     * - SpriteBatch is a class that will take care of rendering our textures for the player or map
     */

    //screen
    Camera camera;
    Viewport viewport;
    SpriteBatch batch;

    //this is the constructor, where we will initialize our fields. (camera, viewport, batch, etc)

    public GameLauncher(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1920,1080,camera);
        batch = new SpriteBatch();
    }

    //this method will be called once (we won't really touch this a lot)
    @Override
    public void show() {

    }

    /*
    - this is the update method, where we will be updated our sprites and map. This method will be called in the render method since it's called repetitively.
    - we created this method in order to make the render method more read-able
     */
    public void update(){

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
        batch.begin();
        //render stuff here
        batch.end();
    }

    // don't worry about this method it will just be used for when the screen resizes so that the rendering don't get messed up
    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);//tells the viewport to update accordingly
        batch.setProjectionMatrix(camera.combined);
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
    }
}
