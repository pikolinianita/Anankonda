package pl.lcc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.lcc.game.*;
import pl.lcc.setup.Konsts;

public class Anaconda extends ApplicationAdapter {
	SpriteBatch batch;
	private Paddle paddle;
	private Ball ball;
	private Board board	;
	private Surround surround;

	private Scoring scoring;

	@Override
	public void create () {
		batch = new SpriteBatch();
		scoring = new Scoring();
		this.surround = new Surround(batch, scoring);
		this.paddle = new Paddle(batch, new Texture("bar3.png"), 0, 0);
		this.ball = new Ball(batch, new Texture("ball.png"), Konsts.Ball.startX, Konsts.Ball.startY, scoring);
		this.board = Board.lvl2(batch, scoring);
	}

	@Override
	public void render () {

		ScreenUtils.clear(0, 0, 0, 1);
		paddle.setX(Gdx.input.getX());

		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			processLeftButton();
		}
		if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
			processRightButton();
		}

		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			//throw new RuntimeException("Bye!");
			Gdx.app.exit();
		}

		ball.move();
		paddle.contact(ball);
		board.contact(ball);

		batch.begin();
		paddle.draw();
		board.draw();
		ball.draw();
		surround.draw();
		batch.end();
	}

	private void processLeftButton() {

		System.out.println("X: " + Gdx.input.getX() + " Y: " + Gdx.input.getY());

	}

	private void processRightButton() {
		System.out.println("Debug Point");
	}

	@Override
	public void dispose () {
		batch.dispose();
		board.dispose();
		paddle.dispose();
		ball.dispose();
		surround.dispose();
	}
}
