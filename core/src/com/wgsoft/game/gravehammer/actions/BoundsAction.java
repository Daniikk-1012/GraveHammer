package com.wgsoft.game.gravehammer.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class BoundsAction extends Action {
    static {
        Pools.set(BoundsAction.class, new Pool<BoundsAction>() {
            @Override
            protected BoundsAction newObject() {
                return new BoundsAction();
            }
        });
    }
    
    public abstract static class Listener {
        private BoundsAction boundsAction;

        public abstract void onBounds();

        public void setBoundsAction(BoundsAction boundsAction) {
            this.boundsAction = boundsAction;
        }

        public BoundsAction getBoundsAction() {
            return boundsAction;
        }
    }

    private Listener listener;

    @Override
    public boolean act(float delta) {
        if(listener != null
                && getActor() != null
                && getActor().getParent() != null) {
            final float parentWidth, parentHeight;
            if(getActor().getStage() != null
                    && getActor().getParent()
                    == getActor().getStage().getRoot()) {
                parentWidth = getActor().getStage().getWidth();
                parentHeight = getActor().getStage().getHeight();
            } else {
                parentWidth = getActor().getStage().getWidth();
                parentHeight = getActor().getStage().getHeight();
            }
            if(getActor().getX() < 0f
                    || getActor().getRight() > parentWidth
                    || getActor().getY() < 0f
                    || getActor().getTop() > parentHeight) {
                listener.onBounds();
            }
        }
        return true;
    }

    @Override
    public void reset() {
        super.reset();

        setListener(null);
    }

    public void setListener(Listener listener) {
        if(this.listener != null) {
            this.listener.setBoundsAction(null);
        }
        this.listener = listener;
        if(listener != null) {
            listener.setBoundsAction(this);
        }
    }

    public Listener getListener() {
        return listener;
    }
}
