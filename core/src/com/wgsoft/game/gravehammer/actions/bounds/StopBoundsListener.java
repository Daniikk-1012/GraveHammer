package com.wgsoft.game.gravehammer.actions.bounds;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.wgsoft.game.gravehammer.actions.BoundsAction;
import com.wgsoft.game.gravehammer.actions.VelocityAction;

public class StopBoundsListener extends BoundsAction.Listener {
    private VelocityAction velocityAction;

    @Override
    public void onBounds() {
        final Actor actor = getBoundsAction().getActor();
        final float parentWidth, parentHeight;
        if(actor.getStage() != null
                && actor.getParent() == actor.getStage().getRoot()) {
            parentWidth = actor.getStage().getWidth();
            parentHeight = actor.getStage().getHeight();
        } else {
            parentWidth = actor.getParent().getWidth();
            parentHeight = actor.getParent().getHeight();
        }
        if(velocityAction != null
                && (actor.getX() < 0f || actor.getRight() > parentWidth)) {
            velocityAction.setVelocityX(0f);
        }
        if(actor.getX() < 0f && actor.getRight() > parentWidth) {
            actor.setX(parentWidth / 2f, Align.center);
        } else if(actor.getX() < 0f) {
            actor.setX(0f);
        } else if(actor.getRight() > parentWidth) {
            actor.setX(parentWidth, Align.right);
        }
        if(velocityAction != null
                && (actor.getY() < 0f || actor.getTop() > parentHeight)) {
            velocityAction.setVelocityY(0f);
        }
        if(actor.getY() < 0f && actor.getTop() > parentHeight) {
            actor.setY(parentHeight / 2f, Align.center);
        } else if(actor.getY() < 0f) {
            actor.setY(0f);
        } else if(actor.getTop() > parentHeight) {
            actor.setY(parentHeight, Align.top);
        }
    }

    public void setVelocityAction(VelocityAction velocityAction) {
        this.velocityAction = velocityAction;
    }

    public VelocityAction getVelocityAction() {
        return velocityAction;
    }
}
