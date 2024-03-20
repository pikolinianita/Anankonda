package pl.lcc.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.lcc.setup.Konsts;

public class Surround {
    private final SpriteBatch batch;
    private final Texture texture;
    private final Sprite left;
    private final Sprite right;
    private final Sprite top;

    private final int thickness = 100;
    private final BitmapFont font;
    private final Scoring scoring;

    public Surround(SpriteBatch batch, Scoring scoring) {
        this.batch = batch;
        this.texture = new Texture("curtain.png");
        this.top = new Sprite(texture, Konsts.boardX, Konsts.boardY, Konsts.boardX + 2*thickness, thickness);
        top.setPosition(Konsts.boardX0-thickness, Konsts.boardY);
        this.left = new Sprite(texture, Konsts.boardX, Konsts.boardY, thickness, Konsts.boardY+thickness);
        left.setPosition(Konsts.boardX0-thickness, Konsts.boardY0);
        this.right = new Sprite(texture, Konsts.boardX, Konsts.boardY, thickness, Konsts.boardY+thickness);
        right.setPosition(Konsts.boardX+Konsts.boardX0, Konsts.boardY0);
        font = new BitmapFont();
        this.scoring = scoring;
    }

    public void draw() {
        top.draw(batch);
        left.draw(batch);
        right.draw(batch);
        font.draw(batch, scoring.getText(), Konsts.textX, Konsts.textY);
    }

    public void dispose(){
        texture.dispose();

    }
}
