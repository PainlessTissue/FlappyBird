package com.ptindustry.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture background;

	private Texture birds[];
	private int flapState;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png"); //this is setting the image up to be rendered
		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");


		//another simple change
	}

	@Override
	public void render () {
		if(flapState == 0)
			flapState = 1;
		else
			flapState = 0;

		batch.begin(); //this is telling the app we are going to start rendering images
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //set the image's location (bottom left, bottom right)
		batch.draw(birds[flapState], (Gdx.graphics.getWidth() / 2) - (birds[flapState].getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (birds[flapState].getHeight() / 2));

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
