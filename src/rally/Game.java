package rally;

import java.util.Vector;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game {
	
	private boolean running;
	public static int winWidth = 800;
	public static int winHeight = 600;
	public static boolean isFullscreen = true;
	public int stateIndex = 0;
	
	private State currentState;
	MainMenu menu = new MainMenu();
	Race race = new Race();
	Hiscore hiscore = new Hiscore();
	Options options = new Options();
	
	public Game() {
		running = true;
		currentState = menu;
		init();
		gameLoop();
	}

	private void init() {
		/* Initialize display */
		try {
				Display.setDisplayMode(new DisplayMode(winWidth, winHeight));
				Display.setFullscreen(isFullscreen);
				Display.create();
			} catch(LWJGLException e) {
				System.exit(0);
			}

			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, winWidth, 0, winHeight, 1, -1);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	private void gameLoop() {
		menu.init();
		while(running) {
			currentState.pollInput();
			currentState.update();
			currentState.draw();
			
			Display.update();
			
			if(Display.isCloseRequested())
				running = false;
		}
		Display.destroy();
	}
}