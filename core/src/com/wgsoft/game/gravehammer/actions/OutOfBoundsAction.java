package com.wgsoft.game.gravehammer.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class OutOfBoundsAction extends Action {
    static {
        Pools.set(OutOfBoundsAction.class, new Pool<OutOfBoundsAction>() {
            @Override
            protected OutOfBoundsAction newObject() {
                return new OutOfBoundsAction();
            }
        });
    }

    public abstract static class Listener {
        private OutOfBoundsAction outOfBoundsAction;

        public abstract void onOutOfBounds();
        
        public void setOutOfBoundsAction(OutOfBoundsAction outOfBoundsAction) {
            this.outOfBoundsAction = outOfBoundsAction;
        }

        public OutOfBoundsAction getOutOfBoundsAction() {
            return outOfBoundsAction;
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
            if(getActor().getRight() < 0f
                    || getActor().getX() > parentWidth
                    || getActor().getTop() < 0f
                    || getActor().getY() > parentHeight) {
                listener.onOutOfBounds();
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
            this.listener.setOutOfBoundsAction(null);
        }
        this.listener = listener;
        if(listener != null) {
            listener.setOutOfBoundsAction(this);
        }
    }

    public Listener getListener() {
        return listener;
    }
}
