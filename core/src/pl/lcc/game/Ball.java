package pl.lcc.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.lcc.setup.Konsts;

public class Ball extends ArkDrawable{
    final int size;
    private final Scoring scoring;
    int vX;
    int vY;

    Texture texture;

    public Ball(SpriteBatch batch, Texture texture, int x, int y, Scoring scoring) {
        super(batch, texture, x, y);
        vX = Konsts.Ball.vX0;
        vY = Konsts.Ball.vY0;
        size = Konsts.Ball.size;
        this.texture = texture;
        this.scoring = scoring;
    }
    public ArkDrawable draw(){
        super.draw(size,size);
        return this;
    }

    public void move() {
        if (!scoring.isFinished()) {
            if (isSideHit()) {
                vX *= -1;
            }
            if (hasBallFallenThrough()) {
                scoring.gameLost();
                System.out.println("This is The End, my friend");
            }
            else if (isCeilingHit()) {
                if (vY > 0) {
                    vY *= -1;
                }
            }
            posX += vX;
            posY += vY;
        }
    }

    public int top(){
        return posY + size;
    }

    public int bottom(){
        return posY;
    }

    public int left(){
        return posX;
    }
    public int right(){
        return posX+size;
    }

    private boolean isCeilingHit() {
        return posY > Konsts.boardY + Konsts.boardY0 - size;
    }

    private boolean hasBallFallenThrough() {
        return posY < Konsts.boardY0;
    }

    private boolean isSideHit() {
        return posX < Konsts.boardX0 || posX > Konsts.boardX + Konsts.boardX0 - size;
    }

    public void dispose(){
        texture.dispose();
    }
}
