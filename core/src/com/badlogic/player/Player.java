package com.badlogic.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.sun.org.apache.bcel.internal.generic.PUSH;

import net.mgsx.gltf.loaders.gltf.GLTFLoader;
import net.mgsx.gltf.scene3d.scene.SceneAsset;

public class Player {
    private String name;
    private int lives;
    public float speed = 4f;
    private SceneAsset asset;
    private ModelInstance modelInstance;
    private AnimationController animationController;
    final private String modelFile = "scene.gltf";
    public Player (String name, int lives) {
        this.name = name;
        this.lives = lives;
        loadModel();
    }
    public ModelInstance getModelInstance() {
        return modelInstance;
    }
    public void setAnimation(String id, int loopCount) {
        animationController.setAnimation(id, loopCount);
    }
    public void updateAnimation(float deltaTime) {
        animationController.update(deltaTime);
    }
    public void processInput(float deltaTime) {
        float currentSpeed = speed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            modelInstance.transform.translate(0, 0, currentSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            modelInstance.transform.translate(0, 0, -currentSpeed);
        }
    }
    public void dispose() {
        asset.dispose();
    }
    private void loadModel() {
        asset = new GLTFLoader().load(Gdx.files.internal(modelFile));
        modelInstance = new ModelInstance(asset.scene.model);
        animationController = new AnimationController(modelInstance);
    }
}
