package com.wgsoft.game.gravehammer.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class FillParentAction extends Action {
    static {
        Pools.set(FillParentAction.class, new Pool<FillParentAction>() {
            @Override
            protected FillParentAction newObject() {
                return new FillParentAction();
            }
        });
    }

    private boolean fillHorizontally;
    private boolean fillVertically;

    @Override
    public boolean act(float delta) {
        if(getActor() != null && getActor().getParent() != null) {
            final float parentWidth, parentHeight;
            if(getActor().getStage() != null
                    && getActor().getParent()
                    == getActor().getStage().getRoot()) {
                parentWidth = getActor().getStage().getWidth();
                parentHeight = getActor().getStage().getHeight();
            } else {
                parentWidth = getActor().getParent().getWidth();
                parentHeight = getActor().getParent().getHeight();
            }
            if(fillHorizontally) {
                getActor().setX(0f);
                getActor().setWidth(parentWidth);
            }
            if(fillVertically) {
                getActor().setY(0f);
                getActor().setHeight(parentHeight);
            }
        }
        return true;
    }

    @Override
    public void reset() {
        super.reset();

        fillHorizontally = false;
        fillVertically = false;
    }

    public void setFillHorizontally(boolean fillHorizontally) {
        this.fillHorizontally = fillHorizontally;
    }

    public void setFillVertically(boolean fillVertically) {
        this.fillVertically = fillVertically;
    }

    public boolean isFillHorizontally() {
        return fillHorizontally;
    }

    public boolean isFillVertically() {
        return fillVertically;
    }
}
