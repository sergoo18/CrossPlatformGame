package com.badlogic.scene;

import com.badlogic.base.Object;
import com.badlogic.camera.Camera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;

import net.mgsx.gltf.scene3d.attributes.PBRTextureAttribute;
import net.mgsx.gltf.scene3d.scene.SceneManager;
import net.mgsx.gltf.scene3d.scene.SceneSkybox;

public class GameScene
{
    private Texture brdfLUT;
    private SceneManager sceneManager;
    private SceneSkybox skybox;
    private Light light;
    public GameScene(Light light, Camera camera)
    {
        this.light = light;

        sceneManager = new SceneManager();
        sceneManager.setCamera(camera.get());

        brdfLUT = new Texture(Gdx.files.classpath("net/mgsx/gltf/shaders/brdfLUT.png"));

        buildCubeMaps();
        buildSkyBox();
    }
    void buildCubeMaps()
    {
        sceneManager.environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        sceneManager.environment.set(new PBRTextureAttribute(PBRTextureAttribute.BRDFLUTTexture, brdfLUT));
        sceneManager.environment.add(light.get());
        sceneManager.environment.set(light.getSpecular());
        sceneManager.environment.set(light.getDiffuse());
    }
    void buildSkyBox()
    {
        skybox = new SceneSkybox(light.getEnvironmentCubemap());
        sceneManager.setSkyBox(skybox);
    }
    public void update(float deltaTime)
    {
        sceneManager.update(deltaTime);
    }
    public void render()
    {
        sceneManager.render();
    }
    public void addObject(Object object)
    {
        sceneManager.addScene(object.get());
    }
    public void dispose() {
        light.dispose();
        skybox.dispose();
        sceneManager.dispose();
    }
}
