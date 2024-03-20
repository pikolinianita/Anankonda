package pl.lcc.setup;

public class Konsts {

    public enum GameState {
        WIN, LOST, GAME
    }

    //Initial ball setup: starting point and velocities,
    public static int ballStartX = 200;
    public static int ballStartY = 200;

    //starting ball velocities
    public static int ballVY0 = 3;
    public static int ballVX0 = 3;

    //size of playground
    public static int boardX =  1000;
    public static int boardY = 500;

    //starting place of playground, relative to 0,0
    public static int boardX0 =  100;
    public static int boardY0 = 0;

    //Height of tile (up high, target)
    public static int tileHeight = 20;

    //max amount of tiles
    public static int tileRows = 6;
    public static int tileColumns = 12;

    //size of tiles
    public static int tileSizeX = boardX/tileColumns;
    public static int tileSizeY = tileHeight;

    public static int textX = 400;
    public static int textY = 700;

}
