package com.badlogic.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.utils.Array;

import net.mgsx.gltf.loaders.gltf.GLTFLoader;
import net.mgsx.gltf.scene3d.attributes.PBRCubemapAttribute;
import net.mgsx.gltf.scene3d.lights.DirectionalLightEx;
import net.mgsx.gltf.scene3d.scene.SceneAsset;
import net.mgsx.gltf.scene3d.utils.IBLBuilder;


public class Game extends ApplicationAdapter {
	private SceneAsset sceneAsset;
	private PerspectiveCamera camera;
	private Cubemap diffuseCubemap;
	private Cubemap environmentCubemap;
	private Cubemap specularCubemap;
	private DirectionalLightEx light;
	private FirstPersonCameraController cameraController;
	private ModelBatch modelBatch;
	private Environment environment;
	public Array<ModelInstance> instances = new Array<>();
	private Array<AnimationController> animationControllers = new Array<>();

	@Override
	public void create() {
		sceneAsset = new GLTFLoader().load(Gdx.files.internal("scene.gltf"));
		modelBatch = new ModelBatch();
		environment = new Environment();

		for (float x = -200f; x <= 200f; x += 4f) {
			for (float z = -200f; z <= 200f; z += 4f) {
				ModelInstance modelInstance = new ModelInstance(sceneAsset.scene.model);
				AnimationController animationController = new AnimationController(modelInstance);
				modelInstance.transform.setToTranslation(x, 0, z);
				modelInstance.transform.rotate(1,0,0,-90);
				animationController.setAnimation("Start_Liftoff", -1);
				animationControllers.add(animationController);
				instances.add(modelInstance);

			}
		}

		camera = new PerspectiveCamera(120f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.near = .02f / 1000f;
		camera.far = 20f;

		cameraController = new FirstPersonCameraController(camera);
		Gdx.input.setInputProcessor(cameraController);

		light = new DirectionalLightEx();
		light.direction.set(1, -3, 1).nor();
		light.color.set(Color.WHITE);
		environment.add(light);

		IBLBuilder iblBuilder = IBLBuilder.createOutdoor(light);
		environmentCubemap = iblBuilder.buildEnvMap(1024);
		diffuseCubemap = iblBuilder.buildIrradianceMap(256);
		specularCubemap = iblBuilder.buildRadianceMap(1);
		iblBuilder.dispose();

		environment.set(PBRCubemapAttribute.createSpecularEnv(specularCubemap));
		environment.set(PBRCubemapAttribute.createDiffuseEnv(diffuseCubemap));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		float deltaTime = Gdx.graphics.getDeltaTime();
		for (AnimationController animationController : animationControllers) {
			animationController.update(deltaTime);
		}
		cameraController.update();
		modelBatch.begin(camera);
		modelBatch.render(instances, environment);
		modelBatch.end();
	}

	@Override
	public void dispose() {
		sceneAsset.dispose();
		environmentCubemap.dispose();
		diffuseCubemap.dispose();
		specularCubemap.dispose();
	}
}