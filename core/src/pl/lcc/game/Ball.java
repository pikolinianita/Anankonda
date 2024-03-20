package pl.lcc.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.lcc.setup.Konsts;

public class Ball extends ArkDrawable{
    final int size;
    private final Scoring scoring;
    int vX;
    int vY;
    boolean finished = false;

    Texture texture;

    public Ball(SpriteBatch batch, Texture texture, int x, int y, Scoring scoring) {
        super(batch, texture, x, y);
        vX = Konsts.ballVX0;
        vY = Konsts.ballVY0;
        size = 20;
        finished = false;
        this.texture = texture;
        this.scoring = scoring;
    }
    public ArkDrawable draw(){
        super.draw(size,size);
        return this;
    }

    public void move() {
        if (!scoring.isFinished()) {
            if (posX < Konsts.boardX0 || posX > Konsts.boardX + Konsts.boardX0 - size) {
                vX *= -1;
            }
            if (posY < Konsts.boardY0) {
                scoring.gameLost();
                System.out.println("End End End");
            }
            else if (posY > Konsts.boardY + Konsts.boardY0 - size) {
                if (vY > 0) {
                    vY *= -1;
                }
            }
            posX += vX;
            posY += vY;
        }
    }

    public void dispose(){
        texture.dispose();
    }
}
