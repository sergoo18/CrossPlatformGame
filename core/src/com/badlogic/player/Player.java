package com.badlogic.player;

import com.badlogic.base.Object;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;

public class Player extends Object {
    private int lives;
    public float speed = 4f;
    public Player (String name, String modelFilePath, Vector3 initPos, int lives) {
        super(name, modelFilePath, initPos, 0, 0);
        this.lives = lives;
    }
    public void processInput(float deltaTime) {
        float currentSpeed = speed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            object.modelInstance.transform.translate(0, 0, currentSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            object.modelInstance.transform.translate(0, 0, -currentSpeed);
        }
    }
    public void setAnimation(String name, int loopCount) {
        object.animationController.setAnimation(name, loopCount);
    }
    public int getLives() {
        return lives;
    }
}
