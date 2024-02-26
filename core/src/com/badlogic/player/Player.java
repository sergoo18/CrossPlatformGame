package com.badlogic.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.sun.org.apache.bcel.internal.generic.PUSH;

import net.mgsx.gltf.loaders.gltf.GLTFLoader;
import net.mgsx.gltf.scene3d.scene.Scene;
import net.mgsx.gltf.scene3d.scene.SceneAsset;

public class Player {
    private String name;
    private int lives;
    public float speed = 4f;

    private Scene model;
    final private String modelFile = "scene.gltf";
    public Player (String name, int lives) {
        this.name = name;
        this.lives = lives;
        loadModel();
    }
    public Scene getModel() {
        return model;
    }
    public void setAnimation(String id, int loopCount) {
        model.animationController.setAnimation(id, loopCount);
    }
    public void updateAnimation(float deltaTime) {
        model.animationController.update(deltaTime);
    }
    public void processInput(float deltaTime) {
        float currentSpeed = speed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            model.modelInstance.transform.translate(0, 0, currentSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            model.modelInstance.transform.translate(0, 0, -currentSpeed);
        }
    }
    public void dispose() {
//        asset.dispose();
    }
    private void loadModel() {
        SceneAsset asset = new GLTFLoader().load(Gdx.files.internal(modelFile));
        model = new Scene(asset.scene);
//        asset.dispose();
    }
}
