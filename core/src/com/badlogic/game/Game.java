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

public class Game extends ApplicationAdapter {
	private Player player;
	private Camera camera;
	private Light light;
	private Environment environment;
	private ModelBatch modelBatch;
	private Skybox skybox;
	private final Array<ModelInstance> instances = new Array<>();

	@Override
	public void create() {
		modelBatch = new ModelBatch();
		environment = new Environment();

		camera = new Camera();
		Gdx.input.setInputProcessor(camera.getCameraController());

		light = new Light(new Vector3(1, -3, 1), Color.WHITE);
		environment.add(light.get());
		environment.set(light.getSpecular());
		environment.set(light.getDiffuse());

		skybox = new Skybox("net/mgsx/gltf/shaders/brdfLUT.png");

		player = new Player("Aboba", 9);
		instances.add(player.getModelInstance());
		player.setAnimation("Start_Liftoff", 10);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		float deltaTime = Gdx.graphics.getDeltaTime();

		player.processInput(deltaTime);
		player.updateAnimation(deltaTime);
		camera.updateCameraControl();

		modelBatch.begin(camera.get());
		modelBatch.render(instances, environment);
		modelBatch.render(skybox.get());
		modelBatch.end();
	}

	@Override
	public void dispose() {
		light.dispose();
		skybox.dispose();
		player.dispose();
	}
}