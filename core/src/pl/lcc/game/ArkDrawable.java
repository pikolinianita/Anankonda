package pl.lcc.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ArkDrawable {

    private final SpriteBatch batch;
    private final Texture texture;
    protected int posX;
    protected int posY;

    public ArkDrawable(SpriteBatch batch, Texture texture, int x, int y) {

        this.batch = batch;
        this.texture = texture;
        posX = x;
        posY = y;
    }

    protected ArkDrawable draw(int sizeX, int sizeY){
           batch.draw(texture, posX, posY, sizeX, sizeY);
           return this;
    }

    public abstract ArkDrawable draw();
}
