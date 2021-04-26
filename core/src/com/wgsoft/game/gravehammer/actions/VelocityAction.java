package com.wgsoft.game.gravehammer.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class VelocityAction extends Action {
    static {
        Pools.set(VelocityAction.class, new Pool<VelocityAction>() {
            @Override
            protected VelocityAction newObject() {
                return new VelocityAction();
            }
        });
    }

    private float velocityX;
    private float velocityY;

    @Override
    public boolean act(float delta) {
        if(getActor() != null) {
            getActor().moveBy(velocityX * delta, velocityY * delta);
        }
        return true;
    }

    @Override
    public void reset() {
        super.reset();
        velocityX = 0f;
        velocityY = 0f;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }
}
