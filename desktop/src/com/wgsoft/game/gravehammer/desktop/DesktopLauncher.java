package com.wgsoft.game.gravehammer.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.wgsoft.game.gravehammer.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config
            = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Grave Hammer");
		new Lwjgl3Application(MyGdxGame.getInstance(), config);
	}
}
