package gameoff2021.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import gameoff2021.game.Game;
import gameoff2021.game.utilities.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.fullscreen = true;
		config.foregroundFPS = 120;
		config.backgroundFPS = 120;
		config.height = (int)Constants.Map.MAP_HEIGHT;
		config.width = (int)Constants.Map.MAP_WIDTH;
		new LwjglApplication(new Game(), config);
	}
}
