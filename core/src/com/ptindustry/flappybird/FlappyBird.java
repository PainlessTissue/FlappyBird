package com.ptindustry.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture background;

	//bird
	private Texture birds[]; //the texture of the birds animation (flapstate)
	private int flapState; //birds current animation
	private float birdY; //birds current position
	private float velocity = 0; //birds current velocity
	private float gap = 430;

	//tubes
	private Texture tubeTop, tubeBottom;
	private float maxTubeOffset;
	float tubeVelocity = 4;
	Random random;
	float distanceBetweenTubes;
	int numberOfTubes = 4;
	float[] tubeX = new float[numberOfTubes];
	float[] tubeOffset = new float[numberOfTubes];

	//extra
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

		tubeBottom = new Texture("bottomtube.png");
		tubeTop = new Texture("toptube.png");
		maxTubeOffset = Gdx.graphics.getHeight() / 2  - gap / 2 - 100;
		random = new Random();
		distanceBetweenTubes = Gdx.graphics.getWidth() * 7/9;

		for(int i = 0; i < numberOfTubes; i++)
		{
			tubeOffset[i] = (random.nextFloat() - .05f) * (Gdx.graphics.getHeight() - gap - 200);

			tubeX[i] = (Gdx.graphics.getWidth() / 2) - (tubeTop.getWidth() / 2) + (i * distanceBetweenTubes);

		}
	}

	@Override
	public void render ()
	{
		batch.begin(); //this is telling the app we are going to start rendering images

		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //set the image's location (bottom left, bottom right)

		if(gameState != 0)
		{
			if(Gdx.input.isTouched())
			{
				velocity = -25; // move up

			}

			for(int i = 0; i < numberOfTubes; i++)
			{
				if(tubeX[i] < -tubeTop.getWidth())
					tubeX[i] += numberOfTubes * distanceBetweenTubes;

				else
					tubeX[i] -= tubeVelocity;

				batch.draw(tubeTop, tubeX[i], (Gdx.graphics.getHeight() / 2) + gap / 2 + tubeOffset[i]);
				batch.draw(tubeBottom, tubeX[i], (Gdx.graphics.getHeight() / 2) - (gap / 2) - tubeBottom.getHeight() + tubeOffset[i]);
			}

			if(birdY > 0 || velocity < 0) //to keep it from going off the bottom of the screen
			{
				velocity += 1.5; //the speed is increasing every frame
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

		drawEverything();
	}

	@Override
	public void dispose ()
	{
		batch.dispose();
		background.dispose();
	}


	private void drawEverything()
	{
		//center the position of the bird in the center of the screen
		batch.draw(birds[flapState], (Gdx.graphics.getWidth() / 2) - (birds[flapState].getWidth() / 2), birdY); //horizonally always the same, vertically changes as code runs

		//draw so its in the center, but still a gap between the two of them
		batch.end();

	}

}
