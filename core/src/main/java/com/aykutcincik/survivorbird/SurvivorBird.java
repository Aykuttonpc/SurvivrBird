package com.aykutcincik.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {

    SpriteBatch batch;
    Texture background;
    Texture bird;
    Texture enemy1;
    Texture enemy2;
    Texture enemy3;
    Texture enemy4;
    Texture enemy5;
    float birdX = 0;
    float birdY = 0;
    int gameState = 0;
    float velocity = 0;
    float gravity = 0.5f;
    float enemyVelocity = 8;
    Random random;
    int score = 0;
    int scoredEnemy = 0;
    BitmapFont font;
    BitmapFont font2;

    Circle birdCircle;

    ShapeRenderer shapeRenderer;

    int numberOfEnemies = 6;
    float [] enemyX = new float[numberOfEnemies];
    float [] enemyOffSet = new float[numberOfEnemies];
    float [] enemyOffSet2 = new float[numberOfEnemies];
    float [] enemyOffSet3 = new float[numberOfEnemies];
    float [] enemyOffSet4 = new float[numberOfEnemies];
    float [] enemyOffSet5 = new float[numberOfEnemies];
    float distance = 0;

    Circle[] enemyCircles;
    Circle[] enemyCircles2;
    Circle[] enemyCircles3;
    Circle[] enemyCircles4;
    Circle[] enemyCircles5;


    @Override
    public void create () {
        batch = new SpriteBatch();
        background = new Texture("background.png");
        bird = new Texture("bird.png");
        enemy1 = new Texture("enemy.png");
        enemy2 = new Texture("enemy.png");
        enemy3 = new Texture("enemy.png");
        enemy4 = new Texture("enemy.png");
        enemy5 = new Texture("enemy.png");

        distance = Gdx.graphics.getWidth() / 2;
        random = new Random();


        birdX = Gdx.graphics.getWidth() / 2 - bird.getHeight() / 2;
        birdY = Gdx.graphics.getHeight() / 3;

        shapeRenderer = new ShapeRenderer();


        birdCircle = new Circle();
        enemyCircles = new Circle[numberOfEnemies];
        enemyCircles2 = new Circle[numberOfEnemies];
        enemyCircles3 = new Circle[numberOfEnemies];
        enemyCircles4 = new Circle[numberOfEnemies];
        enemyCircles5 = new Circle[numberOfEnemies];


        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(4);

        font2 = new BitmapFont();
        font2.setColor(Color.WHITE);
        font2.getData().setScale(6);


        for (int i = 0; i<numberOfEnemies; i++) {


            enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
            enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
            enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
            enemyOffSet4[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
            enemyOffSet5[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

            enemyX[i] = Gdx.graphics.getWidth() - enemy1.getWidth() / 2 + i * distance;


            enemyCircles[i] = new Circle();
            enemyCircles2[i] = new Circle();
            enemyCircles3[i] = new Circle();
            enemyCircles4[i] = new Circle();
            enemyCircles5[i] = new Circle();

        }




    }

    @Override
    public void render () {

        batch.begin();
        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        if (gameState ==1) {



            if (enemyX[scoredEnemy] < Gdx.graphics.getWidth() / 2 - bird.getHeight() / 2) {
                score++;

                if (scoredEnemy < numberOfEnemies - 1) {
                    scoredEnemy++;
                } else {
                    scoredEnemy = 0;
                }

            }



            if (Gdx.input.justTouched()) {

                velocity = -7;

            }



            for (int i = 0; i < numberOfEnemies; i++) {

                float minDistance = Gdx.graphics.getWidth()/2;

                if (enemyX[i] < Gdx.graphics.getWidth() / 15) {
                    enemyX[i] = enemyX[i] + numberOfEnemies * distance;
                    enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyOffSet4[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyOffSet5[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                } else {
                    enemyX[i] = enemyX[i] - enemyVelocity;
                }


                batch.draw(enemy1,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet[i], Gdx.graphics.getWidth() / 13,Gdx.graphics.getHeight() / 8);
                batch.draw(enemy2,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet2[i],Gdx.graphics.getWidth() / 13,Gdx.graphics.getHeight() / 8);
                batch.draw(enemy3,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet3[i],Gdx.graphics.getWidth() / 13,Gdx.graphics.getHeight() / 8);
                batch.draw(enemy4,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet4[i],Gdx.graphics.getWidth() / 13,Gdx.graphics.getHeight() / 8);
                batch.draw(enemy5,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet5[i],Gdx.graphics.getWidth() / 13,Gdx.graphics.getHeight() / 8);


                enemyCircles[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet[i] + Gdx.graphics.getHeight() / 20,  Gdx.graphics.getWidth() / 45);
                enemyCircles2[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet2[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 45);
                enemyCircles3[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet3[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 45);
                enemyCircles4[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet4[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 45);
                enemyCircles5[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet5[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 45);


            }



            if (birdY > 0) {
                velocity = velocity + gravity;
                birdY = birdY - velocity;
            } else {
                gameState = 2;
            }


        } else if (gameState == 0) {
            if (Gdx.input.justTouched()) {
                gameState = 1;
            }
        } else if (gameState == 2) {

            font2.draw(batch,"Game Over! Tap To Play Again!",100,Gdx.graphics.getHeight() / 2);

            if (Gdx.input.justTouched()) {
                gameState = 1;

                birdY = Gdx.graphics.getHeight() / 3;


                for (int i = 0; i<numberOfEnemies; i++) {


                    enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyOffSet4[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyOffSet5[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

                    enemyX[i] = Gdx.graphics.getWidth() - enemy1.getWidth() / 2 + i * distance;


                    enemyCircles[i] = new Circle();
                    enemyCircles2[i] = new Circle();
                    enemyCircles3[i] = new Circle();
                    enemyCircles4[i] = new Circle();
                    enemyCircles5   [i] = new Circle();

                }

                velocity = 0;
                scoredEnemy = 0;
                score = 0;

            }
        }


        batch.draw(bird,birdX, birdY, Gdx.graphics.getWidth() / 15,Gdx.graphics.getHeight() / 10);

        font.draw(batch,String.valueOf(score),100,200);

        batch.end();

        birdCircle.set(birdX +Gdx.graphics.getWidth() / 30 ,birdY + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);

        //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(Color.BLACK);
        //shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);




        for ( int i = 0; i < numberOfEnemies; i++) {
            //shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);
            //shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet2[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);
            //shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet3[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);

            if (Intersector.overlaps(birdCircle,enemyCircles[i]) || Intersector.overlaps(birdCircle,enemyCircles2[i]) || Intersector.overlaps(birdCircle,enemyCircles3[i])) {
                gameState = 2;
            }
        }

        //shapeRenderer.end();

    }

    @Override
    public void dispose () {

    }
}
