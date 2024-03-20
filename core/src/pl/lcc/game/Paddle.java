package pl.lcc.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.lcc.setup.Konsts;

public class Paddle extends ArkDrawable {
    private final int sizeX;
    private final int sizeY;

    Texture texture;

    public Paddle(SpriteBatch batch, Texture texture, int x, int y) {
        super(batch, texture, x, y);
        sizeX = 100;
        sizeY = 20;
        this.texture = texture;
    }

    public ArkDrawable draw(){
        super.draw(sizeX,20);
        return this;
    }

    public void setX(int x) {
        posX = x;
        if(x< Konsts.boardX0){
            posX = Konsts.boardX0;
        } else if (x > Konsts.boardX + Konsts.boardX0 - sizeX){
            posX = Konsts.boardX+Konsts.boardX0 - sizeX;
        }
    }

    public void contact(Ball ball) {
        if(ball.posY < posY + sizeY){
            if(ball.posX + ball.size > posX && ball.posX < posX + sizeX){
                ball.vY = Konsts.Ball.vY0;
                ball.vX = ((ball.posX - posX)-sizeX/2) /5;
            }
        }
    }

    public void dispose(){
        texture.dispose();
    }
}
