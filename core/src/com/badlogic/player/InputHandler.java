package com.badlogic.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {
    private Player player;
    InputHandler(Player player) {
        this.player = player;
    }
    public boolean keyDown(int keycode) {
//        float currentSpeed = player.speed * Gdx.graphics.getDeltaTime();
//        switch (keycode) {
//            case Input.Keys.W:
//                player.getModelInstance().transform.translate(0, 0,currentSpeed);
//                break;
//            case Input.Keys.S:
//                player.getModelInstance().transform.translate(0, 0, -currentSpeed);
//                break;
//            default:
//                return true;
//        }

        return false;
    }

    public boolean keyUp(int keycode) {
        System.out.println(keycode);
        return false;
    }

    public boolean keyTyped(char character) {
        float currentSpeed = player.speed * Gdx.graphics.getDeltaTime();
        switch (character) {
            case 'W':
                player.getModel().modelInstance.transform.translate(0, 0,currentSpeed);
                break;
            case 'S':
                player.getModel().modelInstance.transform.translate(0, 0, -currentSpeed);
                break;
            default:
                return false;
        }

        return true;
    }

    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved(int x, int y) {
        return false;
    }

    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

