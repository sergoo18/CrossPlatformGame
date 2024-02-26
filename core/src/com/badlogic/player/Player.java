package com.badlogic.player;

import com.badlogic.base.Object;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Vector3;
import com.sun.org.apache.bcel.internal.generic.PUSH;

import net.mgsx.gltf.loaders.gltf.GLTFLoader;
import net.mgsx.gltf.scene3d.scene.Scene;
import net.mgsx.gltf.scene3d.scene.SceneAsset;

public class Player extends Object {
    private int lives;
    public float speed = 4f;
    public Player (String name, String modelFilePath, Vector3 initPos, int lives) {
        super(name, modelFilePath, initPos, 0, 0);
        this.lives = lives;
    }
    public Scene getObject() {
        return object;
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
    public void dispose() {
        dispose();
    }
}
