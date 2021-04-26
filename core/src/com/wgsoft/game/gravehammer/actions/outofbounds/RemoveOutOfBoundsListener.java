package com.wgsoft.game.gravehammer.actions.outofbounds;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.wgsoft.game.gravehammer.actions.OutOfBoundsAction;

public class RemoveOutOfBoundsListener extends OutOfBoundsAction.Listener {
    @Override
    public void onOutOfBounds() {
        final Actor actor = getOutOfBoundsAction().getActor();
        actor.clear();
        actor.remove();
    }
}
