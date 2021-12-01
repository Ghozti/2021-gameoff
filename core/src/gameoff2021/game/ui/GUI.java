package gameoff2021.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.awt.*;

public class GUI {

    // TODO add fonts and stuff here
    BitmapFont font;
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/tech-headlines-font/TechHeadlines-3Pzp.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

    public GUI(){
        params.size = 25;
        params.color = new Color(1,1,1,1f);
        params.borderWidth = 1;

        font = generator.generateFont(params);
    }

    public void draw(Batch batch, String text, float x, float y){
        font.draw(batch,text,x,y);
    }

}
