package com.badlogic.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;

public class Camera {
    private PerspectiveCamera camera;
    Camera () {
        this(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 120f, .02f/1000f, 20f);
    }
    Camera (float width, float height, float fov, float near, float far) {

    }
}
