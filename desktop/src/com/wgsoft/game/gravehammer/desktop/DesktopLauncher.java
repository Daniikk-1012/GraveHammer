package com.wgsoft.game.gravehammer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.wgsoft.game.gravehammer.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final LwjglApplicationConfiguration config
            = new LwjglApplicationConfiguration();
        config.title = "Grave Hammer";
        config.forceExit = false;
		new LwjglApplication(MyGdxGame.getInstance(), config);
	}
}
