package gameoff2021.game.utilities;

public class Constants {

    public static class Player{

        /* stringballnoob: I created 13 constants. Constants are values that remain the same.
        * Constants cannot be modified/changed.
        * "Final" word indicates constants cannot be changed.
        */

        public static final float MAX_SPEEDX = 1, MAX_SPEEDY = 1;
        public static final float UNSCALED_WIDTH = 256, UNSCALED_HEIGHT = 256;
        public static final float INIT_X = 825, INIT_Y= 390;
        public static final float SCALE = 0.1171875f;
        public static final float HITBOXOFFSET_X = 64, HITBOXOFFSET_Y = 64;
    }

    public static class Map{
        public static final float MAP_WIDTH = 1920, MAP_HEIGHT = 1080;
        public static final float X = 0, Y = 0;
    }


    public static class Tile{
        public static final float TILE_WIDTH = 30, TILE_HEIGHT = 30;
    }
}
