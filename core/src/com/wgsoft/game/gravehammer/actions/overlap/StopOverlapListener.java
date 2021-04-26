package com.wgsoft.game.gravehammer.actions.overlap;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.wgsoft.game.gravehammer.actions.OverlapAction;
import com.wgsoft.game.gravehammer.actions.VelocityAction;

public class StopOverlapListener extends OverlapAction.Listener {
    public abstract static class Listener {
        public StopOverlapListener stopOverlapListener;

        public abstract boolean onOverlap(Actor actor);

        public void setStopOverlapListener(
                StopOverlapListener stopOverlapListener) {
            this.stopOverlapListener = stopOverlapListener;
        }

        public StopOverlapListener getStopOverlapListener() {
            return stopOverlapListener;
        }
    }

    private Listener listener;
    private VelocityAction velocityAction;

    @Override
    public boolean onOverlap(Actor actor) {
        if(listener != null && listener.onOverlap(actor)) {
            final Actor mainActor = getOverlapAction().getActor();
            final float right = actor.getRight() - mainActor.getX();
            final float left = mainActor.getRight() - actor.getX();
            final float top = actor.getTop() - mainActor.getY();
            final float bottom = mainActor.getTop() - actor.getY();
            if(Math.min(right, left) < Math.min(top, bottom)) {
                if(right < left) {
                    mainActor.setX(actor.getRight());
                    if(velocityAction != null
                            && velocityAction.getVelocityX() < 0f) {
                        velocityAction.setVelocityX(0f);
                    }
                } else {
                    mainActor.setX(actor.getX(), Align.right);
                    if(velocityAction != null
                            && velocityAction.getVelocityX() > 0f) {
                        velocityAction.setVelocityX(0f);
                    }
                }
            } else {
                if(top < bottom) {
                    mainActor.setY(actor.getTop());
                    if(velocityAction != null
                            && velocityAction.getVelocityY() < 0f) {
                        velocityAction.setVelocityY(0f);
                    }
                } else {
                    mainActor.setY(actor.getY(), Align.top);
                    if(velocityAction != null
                            && velocityAction.getVelocityY() > 0f) {
                        velocityAction.setVelocityY(0f);
                    }
                }
            }
        }
        return false;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setVelocityAction(VelocityAction velocityAction) {
        this.velocityAction = velocityAction;
    }

    public Listener getListener() {
        return listener;
    }

    public VelocityAction getVelocityAction() {
        return velocityAction;
    }
}
