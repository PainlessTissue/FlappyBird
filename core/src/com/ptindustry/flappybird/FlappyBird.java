package com.ptindustry.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture background;

	private Texture birds[]; //the texture of the birds animation (flapstate)
	private int flapState; //birds current animation
	private float birdY; //birds current position
	private float velocity = 0; //birds current velocity

	private int gameState = 0; //variable that determines whether the game is running (0 means not)

	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		background = new Texture("bg.png"); //this is setting the image up to be rendered

		birds = new Texture[2]; //twp textures for the bird
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
		birdY = (Gdx.graphics.getHeight() / 2) - (birds[0].getHeight() / 2); //center the position in the sprites center
	}

	@Override
	public void render ()
	{
		if(gameState != 0)
		{
			if(Gdx.input.isTouched())
				velocity -= 30; // move up

			if(birdY > 0 || velocity < 0) //to keep it from going off the bottom of the screen
			{
				velocity++; //the speed is increasing every frame
				birdY -= velocity; //and their position is decreasing (gravity implementation)
			}
		}

		else
		{
			if(Gdx.input.isTouched()) // begin game
				gameState = 1;
		}

		if (flapState == 0)
			flapState = 1;

		else
			flapState = 0;

		batch.begin(); //this is telling the app we are going to start rendering images
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //set the image's location (bottom left, bottom right)
		//center the position of the bird in the center of the screen
		batch.draw(birds[flapState], (Gdx.graphics.getWidth() / 2) - (birds[flapState].getWidth() / 2), birdY); //horizonally always the same, vertically changes as code runs
		batch.end();
	}

	@Override
	public void dispose ()
	{
		batch.dispose();
		background.dispose();
	}
}
