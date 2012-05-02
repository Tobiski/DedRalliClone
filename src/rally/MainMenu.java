package rally;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class MainMenu implements State {
	private Text text = new Text();
	private Picture bg = new Picture();
	
	public MainMenu() {
	}
	
	public void init() {
		bg.init("res/images/mainMenu.png");
		bg.setAsBackground();
		bg.writeDataToBuffer();
		
		text.init();
		text.setText("Version 0123456789", 150, 50);
		text.setText("Ded Ralli Clone", 150, 100);
		text.setText("New Game", 200, 200);
		text.setText("Options", 200, 300);
	}
	
	@Override
	public void pollInput() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			Game.endGame();
	}

	@Override
	public void update() {

	}

	@Override
	public void draw() {
		bg.draw();
		text.draw();
	}

	@Override
	public void changeState() {
		
	}

}
