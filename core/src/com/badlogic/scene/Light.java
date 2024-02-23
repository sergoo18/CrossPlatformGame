package com.badlogic.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.math.Vector3;

import net.mgsx.gltf.scene3d.attributes.PBRCubemapAttribute;
import net.mgsx.gltf.scene3d.lights.DirectionalLightEx;
import net.mgsx.gltf.scene3d.utils.IBLBuilder;

public class Light {
    private DirectionalLightEx light;
    private Cubemap diffuseCubemap;
    private Cubemap environmentCubemap;
    private Cubemap specularCubemap;
    public Light (Vector3 direction, Color color) {
        light = new DirectionalLightEx();
        light.direction.set(direction).nor();
        light.color.set(color);
        buildCubeMaps();
    }
    public DirectionalLightEx get() {
        return light;
    }
    public Cubemap getEnvironmentCubemap() {
        return environmentCubemap;
    }
    public Attribute getDiffuse() {
        return PBRCubemapAttribute.createDiffuseEnv(diffuseCubemap);
    }
    public Attribute getSpecular() {
        return PBRCubemapAttribute.createSpecularEnv(specularCubemap);
    }
    private void buildCubeMaps() {
        IBLBuilder iblBuilder = IBLBuilder.createOutdoor(light);
        environmentCubemap = iblBuilder.buildEnvMap(1024);
        diffuseCubemap = iblBuilder.buildIrradianceMap(256);
        specularCubemap = iblBuilder.buildRadianceMap(1);
        iblBuilder.dispose();
    }
    public void dispose() {
        environmentCubemap.dispose();
        specularCubemap.dispose();
        diffuseCubemap.dispose();
    }
}
