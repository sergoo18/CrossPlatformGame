package com.badlogic.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import net.mgsx.gltf.scene3d.scene.SceneSkybox;

public class Skybox {
    private Texture skyboxTexture;
    private Model skyboxModel;
    private ModelInstance skyboxInstance;
    public Skybox(String texturePath) {
        skyboxTexture = new Texture(Gdx.files.internal(texturePath), true);
        skyboxTexture.setFilter(Texture.TextureFilter.MipMap, Texture.TextureFilter.Linear);

        ModelBuilder modelBuilder = new ModelBuilder();
        skyboxModel = modelBuilder.createBox(200f, 200f, 200f,
                new Material(TextureAttribute.createDiffuse(skyboxTexture)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);

        skyboxInstance = new ModelInstance(skyboxModel);
    }
    public ModelInstance get() {
        return skyboxInstance;
    }
    public void dispose() {
        skyboxModel.dispose();
        skyboxTexture.dispose();
    }
}
