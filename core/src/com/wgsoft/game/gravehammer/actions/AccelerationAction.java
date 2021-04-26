package com.wgsoft.game.gravehammer.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class AccelerationAction extends Action {
    static {
        Pools.set(AccelerationAction.class, new Pool<AccelerationAction>() {
            @Override
            protected AccelerationAction newObject() {
                return new AccelerationAction();
            }
        });
    }

    private float accelerationX;
    private float accelerationY;
    private VelocityAction velocityAction;

    @Override
    public boolean act(float delta) {
        if(velocityAction != null) {
            velocityAction.setVelocityX(
                    velocityAction.getVelocityX() + accelerationX * delta);
            velocityAction.setVelocityY(
                    velocityAction.getVelocityY() + accelerationY * delta);
        }
        return true;
    }

    @Override
    public void reset() {
        super.reset();
        accelerationX = 0f;
        accelerationY = 0f;
        velocityAction = null;
    }

    public void setAccelerationX(float accelerationX) {
        this.accelerationX = accelerationX;
    }

    public void setAccelerationY(float accelerationY) {
        this.accelerationY = accelerationY;
    }

    public void setVelocityAction(VelocityAction velocityAction) {
        this.velocityAction = velocityAction;
    }

    public float getAccelerationX() {
        return accelerationX;
    }

    public float getAccelerationY() {
        return accelerationY;
    }

    public VelocityAction getVelocityAction() {
        return velocityAction;
    }
}
