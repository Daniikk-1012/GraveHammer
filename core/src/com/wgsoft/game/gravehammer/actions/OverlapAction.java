package com.wgsoft.game.gravehammer.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class OverlapAction extends Action {
    static {
        Pools.set(OverlapAction.class, new Pool<OverlapAction>() {
            @Override
            protected OverlapAction newObject() {
                return new OverlapAction();
            }
        });
    }

    public abstract static class Listener {
        private OverlapAction overlapAction;

        public abstract boolean onOverlap(Actor actor);

        public void setOverlapAction(OverlapAction overlapAction) {
            this.overlapAction = overlapAction;
        }

        public OverlapAction getOverlapAction() {
            return overlapAction;
        }
    }

    private Listener listener;

    @Override
    public boolean act(float delta) {
        if(listener != null
                && getActor() != null
                && getActor().getParent() != null) {
            for(int i = 0; i < getActor().getParent().getChildren().size; i++) {
                final Actor actor = getActor().getParent().getChild(i);
                if(getActor().getX() < actor.getRight()
                        && getActor().getRight() > actor.getX()
                        && getActor().getY() < actor.getTop()
                        && getActor().getTop() > actor.getY()
                        && listener.onOverlap(actor)) {
                    break;
                }
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
            this.listener.setOverlapAction(null);
        }
        this.listener = listener;
        if(listener != null) {
            listener.setOverlapAction(this);
        }
    }

    public Listener getListener() {
        return listener;
    }
}
