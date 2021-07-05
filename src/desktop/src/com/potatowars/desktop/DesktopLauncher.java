package com.potatowars.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.potatowars.PotatoWars;
import com.potatowars.config.GameConfig;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		/*Set Physical measures of the desktop application*/
		config.width = (int) GameConfig.PHYSICAL_WIDTH;
		config.height = (int) GameConfig.PHYSICAL_HEIGHT;

		/*Set the title and disable GL30*/
		config.title = "Potato Wars";
		config.useGL30 = false;

		Application app = new LwjglApplication(PotatoWars.getInstance(), config);
		Gdx.app = app;

		/* Legend of Debug level
			LOG_NONE	-	0	-	No prints
			LOG_DEBUG	-	3	-   Use to print debug messages
			LOG_INFO	-	2	-	Use to print some info
			LOG_ERROR	- 	1	-	Use to print errors
		*/
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}
}
