package com.badlogic.base;

<<<<<<< HEAD
public class Object {
    private String name;
    private String modelFile;
=======

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

import net.mgsx.gltf.loaders.gltf.GLTFLoader;
import net.mgsx.gltf.scene3d.scene.Scene;
import net.mgsx.gltf.scene3d.scene.SceneAsset;

public class Object {

    protected Object(String name, String modelFilePath, Vector3 pos, int width, int height)
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
        SceneAsset asset = new GLTFLoader().load(Gdx.files.internal(modelFilePath));
        object = new Scene(asset.scene);
    }
    protected void move(Vector3 translation)
    {
        object.modelInstance.transform.translate(translation.x, translation.y, translation.z);
    }

    protected void dispose()
    {

    }

    protected String name;
    protected String modelFilePath;
    protected Scene object;
    protected int width;
    protected int height;
    protected Vector3 pos;
>>>>>>> origin/master
}
