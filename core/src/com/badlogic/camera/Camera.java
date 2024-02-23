package com.badlogic.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;

public class Camera {
    private int width;
    private int height;
    private PerspectiveCamera camera;
    private FirstPersonCameraController cameraController;

    public Camera() {
        this(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 120f, .02f/1000f, 20f);
    }
    public Camera (float width, float height, float fov, float near, float far)
    {
        camera = new PerspectiveCamera(120f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        float d = .02f;
        camera.near = d / 1000f;
        camera.far = 20f;

        cameraController = new FirstPersonCameraController(camera);

    }

    public PerspectiveCamera get() { return camera; }
    public FirstPersonCameraController getCameraController() { return cameraController; }

    public void updateCameraControl() { cameraController.update(); }

}
