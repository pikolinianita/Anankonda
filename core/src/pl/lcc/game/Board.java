package pl.lcc.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.lcc.setup.Konsts;

public class Board {
    private final int cols;
    private final int rows;
    private static Texture texture;
    private final Scoring scoring;

    Tile[][] board;

    public Board(Tile[][] board, Scoring scoring) {
        this.board = board;
        this.cols = Konsts.tileColumns;
        this.rows = Konsts.tileRows;
        this.scoring = scoring;
    }

    public static Board lvl1(SpriteBatch batch, Scoring scoring){
       int rows = Konsts.tileRows;
       int cols = Konsts.tileColumns;
       texture = new Texture("bar3.png");
       var loc_board = new Tile[rows][cols];
       int score = 0;
       for (int r = 0;  r<rows; r++){
           for(int c = 0; c<cols; c++){
               score++;
               loc_board[r][c] = new Tile(batch, texture,
                       c*Konsts.tileSizeX + Konsts.boardX0,
                       Konsts.boardY0+ Konsts.boardY - (r+1)*Konsts.tileHeight);
           }
       }
       return new Board(loc_board, scoring.setScore(score));
    }

    public static Board lvl2(SpriteBatch batch, Scoring scoring){
        int rows = Konsts.tileRows;
        int cols = Konsts.tileColumns;
        Texture texture = new Texture("bar3.png");
        var loc_board = new Tile[rows][cols];
        int score = 0;
        for (int r = 0;  r<rows; r++){
            for(int c = 0; c<cols; c++){
                if (c%2==0){
                    score++;
                    loc_board[r][c] = new Tile(batch, texture,
                        c*Konsts.tileSizeX + Konsts.boardX0,
                        Konsts.boardY0+ Konsts.boardY - (r+1)*Konsts.tileHeight);
            }}
        }

        return new Board(loc_board,scoring.setScore(score));
    }

    public void draw() {
        forEachTile((r, c) -> {
            if (board[r][c] != null) {
                board[r][c].draw(Konsts.tileSizeX, Konsts.tileSizeY);
            }
        });
    }

    private void forEachTile(IntBiConsumer consumer) {
        for (int r = 0;  r<rows; r++) {
            for (int c = 0; c < cols; c++){
                consumer.process(r,c);
            }
        }
    }

    public void contact(Ball ball) {
           if (inTargetArea(ball)) {
               if (ball.vY > 0) {
                  checkUp(ball);
               } else {
                   checkDown(ball);
               }
               if (ball.vX > 0) {
                   checkRight(ball);
               } else if (ball.vX < 0) {
                   checkLeft(ball);
               }
           }

    }

    private void checkUp(Ball ball) {
        var coarseX = coarseX(ball.centerX());
        var coarseY = coarseY(ball.top());

        if (board[coarseY][coarseX]!=null){
            ball.vY *= -1;
            processHit(coarseY, coarseX);
        }
    }


    private void checkDown(Ball ball) {
        var coarseX = coarseX(ball.centerX());
        var coarseY = coarseY(ball.bottom());
        if (board[coarseY][coarseX]!=null){
            ball.vY *= -1;
            processHit(coarseY, coarseX);
        }
    }

    private void checkRight(Ball ball) {
        var coarseX = coarseX(ball.right());
        var coarseY = coarseY(ball.centerY());

        if (board[coarseY][coarseX]!=null){
            ball.vX *= -1;
            processHit(coarseY, coarseX);
        }
    }
    private void checkLeft(Ball ball) {
        var coarseX = coarseX(ball.left());
        var coarseY = coarseY(ball.centerY());
        if (board[coarseY][coarseX]!=null){
            ball.vX *= -1;
            processHit(coarseY, coarseX);
        }
    }

    private void processHit(int coarseY, int coarseX) {
        board[coarseY][coarseX]=null;
        scoring.hit();
    }

    private int coarseY(int y) {
        var result =  (Konsts.boardY - y - Konsts.boardY0  )/Konsts.tileHeight;
        if (result < 0) return 0;
        return Math.min(result, Konsts.tileRows - 1);
    }

    private int coarseX(int x) {
        var result =  (x - Konsts.boardX0)/Konsts.tileSizeX;
        if (result < 0) return 0;
        return Math.min(result, Konsts.tileColumns - 1);
    }

    private boolean inTargetArea(Ball ball) {
        return ball.posY > ( Konsts.boardY0 + Konsts.boardY - Konsts.tileRows*Konsts.tileHeight)  ;
    }

    public void dispose(){
        texture.dispose();
        board = null;
    }
}
