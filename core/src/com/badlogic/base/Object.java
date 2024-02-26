package com.badlogic.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

import net.mgsx.gltf.loaders.gltf.GLTFLoader;
import net.mgsx.gltf.scene3d.scene.Scene;
import net.mgsx.gltf.scene3d.scene.SceneAsset;

public class Object {
    public Object(String name, String modelFilePath, Vector3 pos, int width, int height)
    {
        this.name = name;
        this.pos = pos;
        this.modelFilePath = modelFilePath;
        this.width = width;
        this.height = height;

        loadModel();
        move(pos);
    }

    private void loadModel() {
        sceneAsset = new GLTFLoader().load(Gdx.files.internal(modelFilePath));
        object = new Scene(sceneAsset.scene);
    }
    protected void move(Vector3 translation)
    {
        object.modelInstance.transform.translate(translation);
    }

    public void dispose()
    {
        sceneAsset.dispose();
    }
    public Scene get() {
        return object;
    }

    protected String name;
    protected String modelFilePath;
    protected Scene object;
    protected int width;
    protected int height;
    protected Vector3 pos;
    private SceneAsset sceneAsset;
}
