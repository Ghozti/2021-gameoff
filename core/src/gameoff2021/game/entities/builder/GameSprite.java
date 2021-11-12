package gameoff2021.game.entities.builder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameSprite {

    /*
     * @Author - Ghozti
     * This class will be a wrapper/simplified class for the libgdx built in Sprite class.
     * The Sprite class is used for game sprites to hold texture data, position data, width/height...etc
     * This class simplifies the process because the sprite class does have its quirks.
     * We will use this class for any player/npc/object that can be manipulated/modified
     */

    //properties
    Sprite sprite;//we will still use the sprite class, however this class will simplify the process of using it
    com.badlogic.gdx.math.Rectangle hitbox;
    float[] position = {0, 0};//an array to hold an x and y value
    float width, height, scale, unScaledWidth, unscaledHeight;//unscaledWidth and height is the actual image width,height in the actual files (we need this or else the image will be rendered badly)
    float hitBoxOffSetX = 0, hitBoxOffSetY = 0;//we will also draw a rectangle to represent the hitbox of the sprite, sometimes due to scaling we need to move the x and y values, this is where offset comes in
    TextureRegion texture;//the actual texture of the sprite. Texture region is a specific area of a texture atlas (a huge image of a bunch of sprites)
    //we use texture atlases to reduce files, and to optimize the game performance.
    boolean debugMode = true;

    //constants
    public TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/sprites.atlas"));// texture atlas instantiation and file path
    public TextureRegion debugTexture = atlas.findRegion("hitboxDebug");// hitbox texture region

    /*
     *REQUIRED TO FILL IN v, OTHERWISE IT WILL CREATE A NULL EXCEPTION AND WILL CRASH THE PROGRAM
     *THESE ARE JUST SETTERS AND GETTERS
     */

    public void createSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setTexture(TextureRegion region) {
        texture = region;
        sprite.setRegion(texture);
    }

    public void createHitbox(Rectangle rectangle) {
        this.hitbox = rectangle;
    }

    public void setPosition(float x, float y) {
        this.position[0] = x;
        this.position[1] = y;
        sprite.setPosition(this.position[0], this.position[1]);
        hitbox.x = position[0];
        hitbox.y = position[1];
    }

    public void setUnScaledWidth(float width) {
        unScaledWidth = width;
        sprite.setRegionWidth((int) width);
    }

    public void setUnscaledHeight(float height) {
        unscaledHeight = height;
        sprite.setRegionHeight((int) height);
    }

    public void setScale(float scale) {
        this.scale = scale;
        /*the drawn (rendered) width and height will be determined by the scale, so a 100 x 100 image that we want to be drawn in a 50 x 50 pixel width/height,
         *we would need to set the scale to .5. This would make the width/height properties 50 x 50.
         */
        this.width = unScaledWidth * scale;
        this.height = unscaledHeight * scale;
        sprite.setScale(this.scale);

        //by default the hitbox width and height will be the scaled width/height
        hitbox.width = width;
        hitbox.height = height;
    }

    //sometimes the hitbox will be bigger than we want it to be, so we can force it to be a certain width/height here
    public void setHitboxDimensions(float width, float height) {
        hitbox.width = width;
        hitbox.height = height;
    }

    //sometimes the hitbox will be positioned away from where we want it to, so we can force its offset here
    public void setHitboxOffSet(float x, float y) {
        hitBoxOffSetX = x;
        hitBoxOffSetY = y;
    }

    //this method is not the same as the setPosition method, this method will add to the current x and y value instead of setting it to something
    public void updatePosition(float xChange, float yChange) {
        position[0] += xChange;
        position[1] += yChange;
        sprite.setPosition(position[0], position[1]);
        hitbox.x = position[0];
        hitbox.y = position[1];
    }

    public void setDebug(boolean debug) {
        this.debugMode = debug;
    }

    //getters

    public Sprite getSprite() {
        return sprite;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public float[] getPositionArr() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getScale() {
        return scale;
    }

    public float getUnScaledWidth() {
        return unScaledWidth;
    }

    public float getUnscaledHeight() {
        return unscaledHeight;
    }

    public float getHitBoxOffSetX() {
        return hitBoxOffSetX;
    }

    public float getHitBoxOffSetY() {
        return hitBoxOffSetY;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    //DRAWING STUFF
    public abstract void draw(Batch batch);

    //this method will be used to update the sprite for example user input
    public abstract void update();

    public void drawSprite(Batch batch) {
        sprite.draw(batch);
    }

    //will draw the hitbox if the game is in debug mode
    public void drawHitBox(Batch batch) {
        batch.draw(debugTexture, hitbox.x + hitBoxOffSetX, hitbox.y + hitBoxOffSetY, hitbox.width, hitbox.height);
    }
}
