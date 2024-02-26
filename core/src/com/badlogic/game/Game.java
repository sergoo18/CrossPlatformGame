package com.badlogic.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import com.badlogic.player.Player;
import com.badlogic.camera.Camera;
import com.badlogic.scene.Light;
import com.badlogic.scene.Skybox;

import net.mgsx.gltf.scene3d.scene.Scene;
import net.mgsx.gltf.scene3d.scene.SceneManager;
import net.mgsx.gltf.scene3d.scene.SceneSkybox;

public class Game extends ApplicationAdapter {
	private Player player;
	private Camera camera;
	private Light light;
	private SceneManager sceneManager;
	private SceneSkybox sceneSkybox;
	private final Array<Scene> instances = new Array<>();

	@Override
	public void create() {
		sceneManager = new SceneManager();

		camera = new Camera();
		sceneManager.setCamera(camera.get());
		Gdx.input.setInputProcessor(camera.getCameraController());

		light = new Light(new Vector3(1, -3, 1), Color.WHITE);
		sceneManager.environment.add(light.get());
		sceneManager.environment.set(light.getSpecular());
		sceneManager.environment.set(light.getDiffuse());

		sceneSkybox = new SceneSkybox(light.getEnvironmentCubemap());
		sceneManager.setSkyBox(sceneSkybox);

		player = new Player("Aboba", 9);
		sceneManager.addScene(player.getModel());
		player.setAnimation("Start_Liftoff", 10);
	}

	@Override
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		camera.updateCameraControl();
		sceneManager.update(deltaTime);
		sceneManager.render();
	}

	@Override
	public void dispose() {
		light.dispose();

		player.dispose();
	}
}
