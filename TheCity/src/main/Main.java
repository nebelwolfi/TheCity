package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import states.SplashScreen;
import states.MainMenu;
import states.Game;

public class Main extends StateBasedGame {
	// <--- Window specifications
	public static final int FRAME_WIDTH = 1920;
	public static final int FRAME_HEIGHT = 1080;
	public static final int TARGET_FRAME_RATE = 120;
	// --->
	
	// <--- States
    public static final int SPLASHSCREEN = 0;
    public static final int MAINMENU     = 1;
    public static final int GAME         = 2;
	// --->
	
	public static boolean debug = true;
	
	public Main(String name) {
		super("TheCity");
		setDebug(debug);
	}

	public static void setDebug(boolean debuging) {
		debug = debuging;
	}

	public static void main(String[] args) throws SlickException {
		// Set the native library path (depending on the operating system)
		// @formatter:off
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir")
							+ "/lib/lwjgl-2.9.1/native/windows");
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir")
							+ "/lib/lwjgl-2.9.1/native/macosx");
		} else {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + "/lib/lwjgl-2.9.1/native/"
							+ System.getProperty("os.name").toLowerCase());
		}
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL",
				"false");
		System.err.println(System.getProperty("os.name") + ": "
				+ System.getProperty("org.lwjgl.librarypath"));

		// <--- Create appcontainer and set values
		AppGameContainer app = new AppGameContainer(new Main("TheCity"));
		app.setShowFPS(debug);
		app.setDisplayMode(FRAME_WIDTH, FRAME_HEIGHT, false);
		app.setTargetFrameRate(TARGET_FRAME_RATE);
		app.setFullscreen(true);
		app.setVSync(true);
		// ---> 
		app.start();
	}
	
	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		this.addState(new SplashScreen(SPLASHSCREEN));
		this.addState(new MainMenu(MAINMENU));
		this.addState(new Game(GAME));
	}
}
