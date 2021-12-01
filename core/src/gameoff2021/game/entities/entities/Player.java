package gameoff2021.game.entities.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import gameoff2021.game.entities.builder.GameSprite;
import gameoff2021.game.ui.PlayerInput;
import gameoff2021.game.utilities.Constants;

public class Player extends GameSprite {

    public boolean isTouchedByWall, isTouchedByNPC, isTouchedByKey, reset;
    public int keyCount = 60;

    public Player() {
        setDebug(true);
        createSprite(new Sprite(atlas.findRegion("player")));
        setTexture(atlas.findRegion("player"));
        createHitbox(new Rectangle());
        setPosition(Constants.Player.INIT_X, Constants.Player.INIT_Y);
        setOrigin(0,0);//sets the origin of the player to 0,0 from the sprite width
        setUnScaledWidth(Constants.Player.UNSCALED_WIDTH);
        setUnscaledHeight(Constants.Player.UNSCALED_HEIGHT);
        setScale(Constants.Player.SCALE);
    }

    //for the camera to center on the player
    public float[] getCenter(){
        return new float[] {(getPositionArr()[0] + getWidth() / 2), (getPositionArr()[1] + getHeight() / 2)};
    }

    char lastKeyPressed = '*';

    public void handlePositioning(){
        if (isTouchedByNPC){
            setPosition(Constants.Player.INIT_X, Constants.Player.INIT_Y);
            isTouchedByNPC = false;
            reset = true;
            keyCount = 0;
        }

        if (isTouchedByWall){
            switch (lastKeyPressed){
                case 'w':
                    updatePosition(0,-1);
                    break;
                case 's':
                    updatePosition(0,1);
                    break;
                case 'a':
                    updatePosition(1,0);
                    break;
                case 'd':
                    updatePosition(-1,0);
                    break;
            }
            isTouchedByWall = false;
        }

        float xChange = 0, yChange = 0;
        char keyPressed = PlayerInput.inputProcessor();

        if (keyPressed != '*'){
            lastKeyPressed = keyPressed;
        }

        switch (keyPressed){
            case 'w':
                yChange = Constants.Player.MAX_SPEEDY;
                break;
            case 's':
                yChange = -Constants.Player.MAX_SPEEDY;
                break;
            case 'a':
                xChange = -Constants.Player.MAX_SPEEDX;
                break;
            case 'd':
                xChange = Constants.Player.MAX_SPEEDX;
                break;
        }

        updatePosition(xChange,yChange);
    }

    @Override
    public void draw(Batch batch) {
        drawSprite(batch);
        drawHitBox(batch);
    }

    @Override
    public void update() {
        // Called the updatePosition method to call the InputProcessor method to update the position.
        handlePositioning();
    }
}
