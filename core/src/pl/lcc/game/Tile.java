package pl.lcc.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.lcc.setup.Konsts;

public class Tile extends ArkDrawable{

    public Tile(SpriteBatch batch, Texture texture, int x, int y) {
        super(batch, texture, x, y);
    }

    @Override
    public ArkDrawable draw() {
        draw(Konsts.tileSizeX, Konsts.tileSizeY);
        return this;
    }


}
