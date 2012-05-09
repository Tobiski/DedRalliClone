package rally;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game {
	public static int winWidth = 1280;
	public static int winHeight = 1024;
	public static boolean isFullscreen = false;
	public static boolean running;
	public int stateIndex = 0;
	
	private static State currentState;
	static MainMenu menu = new MainMenu();
	static Race race = new Race();
	static Hiscore hiscore = new Hiscore();
	static Options options = new Options();
	
	public Game() {
		running = true;
		currentState = menu;
		init();
		gameLoop();
	}
	
	public static void endGame() {
		running = false;
	}
	
	public static void changeState(int state) {
		if(state == 2)
			currentState = race;
		else if(state == 0)
			currentState = menu;
	}

	private void init() {
		/* Initialize display */
		try {
			if(isFullscreen) {
				Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
				Display.setFullscreen(isFullscreen);
			}
			else {
				Display.setDisplayMode(new DisplayMode(winWidth, winHeight));
			}

			Display.setTitle("DedRalliClone v0.1");
			Display.create();
		} catch(LWJGLException e) {
			System.exit(1);
		}

			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, winWidth, winHeight, 0, 1, -1);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	private void gameLoop() {
		menu.init();
		race.init();
		while(running) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			currentState.pollInput();
			currentState.update();
			currentState.draw();
			
			Display.update();
			
			if(Display.isCloseRequested())
				running = false;
		}
		Display.destroy();
		System.exit(0);
	}
}
