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
	Texture background;
	final int amountOfVertices = 4;
	final int vertexSize = 3;
	int vboVertexHandle;
	int vboTextureHandle;
	Text text;
	
	public MainMenu() {
	}
	
	public void init() {
		try {		
			background = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/images/mainMenu.png"));			
		} catch (IOException e) {
			e.printStackTrace();
		}

		FloatBuffer vertexData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize);
		vertexData.put(new float[]{ 0f, 0f, 0,
									Game.winWidth, 0f, 0f,
									Game.winWidth, Game.winHeight, 0f,
									0f, Game.winHeight, 0f });
		vertexData.flip();
		
		FloatBuffer textureData = BufferUtils.createFloatBuffer(8);
		textureData.put(new float[]{0, 0, 1, 0, 1, 1, 0, 1});
		textureData.flip();
		
		vboVertexHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		vboTextureHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboTextureHandle);
		glBufferData(GL_ARRAY_BUFFER, textureData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		text = new Text();
		text.setText("Version 0123456789", 150, 50);
		text.setText("Ded Ralli Clone", 150, 100);
		text.setText("New Game", 200, 200);
		text.setText("Options", 200, 300);
	}
	
	@Override
	public void pollInput() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
<<<<<<< HEAD
			Game.endGame();
=======
			Game.running = false;
>>>>>>> 8e1b7faa29243b96ac2422dfd319416878961403
	}

	@Override
	public void update() {

	}

	@Override
	public void draw() {
		glClear(GL_COLOR_BUFFER_BIT);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		
		background.bind();
		
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glVertexPointer(3, GL_FLOAT, 0, 0L);

		glBindBuffer(GL_ARRAY_BUFFER, vboTextureHandle);
		GL11.glTexCoordPointer(2, GL_FLOAT, 0, 0L);
		
		glDrawArrays(GL11.GL_QUADS, 0, amountOfVertices);

		glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		text.draw();
	}

	@Override
	public void changeState() {
		
	}

}
