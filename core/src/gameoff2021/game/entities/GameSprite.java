package gameoff2021.game.entities;

import com.badlogic.gdx.Gdx;
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
    float[] position;//an array to hold an x and y value
    float width, height, scale, unScaledWidth, unscaledHeight;//unscaledWidth and height is the actual image width,height in the actual files (we need this or else the image will be rendered badly)
    float hitBoxOffSetX = 0, hitBoxOffSetY = 0;//we will also draw a rectangle to represent the hitbox of the sprite, sometimes due to scaling we need to move the x and y values, this is where offset comes in
    TextureRegion texture;//the actual texture of the sprite. Texture region is a specific area of a texture atlas (a huge image of a bunch of sprites)
    //we use texture atlases to reduce files, and to optimize the game performance.

    //constants
    public TextureAtlas atlas;// texture atlas instantiation and file path TODO fill this
    public TextureRegion debugTexture;// hitbox texture region TODO fill this

    /*
     *REQUIRED TO FILL IN v, OTHERWISE IT WILL CREATE A NULL EXCEPTION AND WILL CRASH THE PROGRAM
     *THESE ARE JUST SETTERS AND GETTERS
     */

    public void createSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public void createHitbox(Rectangle rectangle) {
        this.hitbox = rectangle;
    }

    public void setPosition(float x, float y){
        this.position[0] = x;
        this.position[1] = y;
    }

    public void setUnScaledWidth(float width){
        unScaledWidth = width;
    }

    public void setUnscaledHeight(float height){
        unscaledHeight = height;
    }

    public void setScale(float scale){
        this.scale = scale;
        /*the drawn (rendered) width and height will be determined by the scale, so a 100 x 100 image that we want to be drawn in a 50 x 50 pixel width/height,
         *we would need to set the scale to .5. This would make the width/height properties 50 x 50.
         */
        this.width = unScaledWidth * scale;
        this.height = unscaledHeight * scale;
    }

    //TODO fill the rest of this shit...tomorrow
}
