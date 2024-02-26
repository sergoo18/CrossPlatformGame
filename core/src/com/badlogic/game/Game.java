package com.badlogic.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.player.Player;
import com.badlogic.camera.Camera;
import com.badlogic.scene.Light;
import com.badlogic.scene.GameScene;

public class Game extends ApplicationAdapter {
	private Player player;
	private Camera camera;
	private GameScene gameScene;
	@Override
	public void create() {
		camera = new Camera();
		Gdx.input.setInputProcessor(camera.getCameraController());
		Light light = new Light(new Vector3(1, -3, 1), Color.WHITE);
		gameScene = new GameScene(light, camera);

		player = new Player("Aboba", "scene.gltf", new Vector3(3, -5, -1), 1);
		player.setAnimation("Start_Liftoff", 10);
		gameScene.addObject(player);
	}
	@Override
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		camera.updateCameraControl();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		player.processInput(deltaTime);
		gameScene.update(deltaTime);
		gameScene.render();
	}
	@Override
	public void dispose() {
		player.dispose();
		gameScene.dispose();
	}
}