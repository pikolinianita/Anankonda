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

    public Board(Tile[][] board, int rows, int cols, Scoring scoring) {
        this.board = board;
        this.cols = cols;
        this.rows = rows;
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
               loc_board[r][c] = new Tile(batch, texture,
                       c*Konsts.tileSizeX + Konsts.boardX0,
                       Konsts.boardY0+ Konsts.boardY - (r+1)*Konsts.tileHeight);
           }
       }
       return  new Board(loc_board, rows, cols, scoring.setScore(score));
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

        return new Board(loc_board, rows, cols,scoring.setScore(score));
    }

    public void draw(){
        for (int r = 0;  r<rows; r++) {
            for (int c = 0; c < cols; c++) {
                if(board[r][c]!=null){
                    board[r][c].draw(Konsts.tileSizeX,Konsts.tileSizeY);
                }
            }
        }
    }

    public void contact(Ball ball) {
           if (inTargetArea(ball)) {
               int coarseX = coarseX(ball);
               int coarseY = coarseY(ball);
               if (ball.vY > 0) {
                  checkUp(ball, coarseX, coarseY);
               } else {
                   checkDown(ball, coarseX, coarseY);
               }
               if (ball.vX > 0) {
                   checkRight(ball, coarseX, coarseY);
               } else if (ball.vX < 0) {
                   checkLeft(ball, coarseX, coarseY);
               }
           }

    }

    private void checkUp(Ball ball, int coarseX, int coarseY) {
        if (board[coarseY][coarseX]!=null){
            ball.vY *= -1;
            processHit(coarseY, coarseX);
        }
    }


    private void checkDown(Ball ball, int coarseX, int coarseY) {
        if (board[coarseY][coarseX]!=null){
            ball.vY *= -1;
            processHit(coarseY, coarseX);
        }
    }

    private void checkRight(Ball ball, int coarseX, int coarseY) {

        if (board[coarseY][coarseX]!=null){
            ball.vX *= -1;
            processHit(coarseY, coarseX);
        }
    }
    private void checkLeft(Ball ball, int coarseX, int coarseY) {
        if (board[coarseY][coarseX]!=null){
            ball.vX *= -1;
            processHit(coarseY, coarseX);
        }
    }

    private void processHit(int coarseY, int coarseX) {
        board[coarseY][coarseX]=null;
        scoring.hit();
    }

    private int coarseY(Ball ball) {
        return (Konsts.boardY - ball.posY - Konsts.boardY0  )/Konsts.tileHeight;
    }

    private int coarseX(Ball ball) {
        return  (ball.posX - Konsts.boardX0)/Konsts.tileSizeX;
    }

    private boolean inTargetArea(Ball ball) {
        return ball.posY > ( Konsts.boardY0+ Konsts.boardY - Konsts.tileRows*Konsts.tileHeight);
    }

    public void dispose(){
        texture.dispose();
        board = null;
    }
}
